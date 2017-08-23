package mallcollection.joinearn.com.mymall.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import mallcollection.joinearn.com.mymall.App.ApiConstant;
import mallcollection.joinearn.com.mymall.R;
import mallcollection.joinearn.com.mymall.model.bean.Brand;
import mallcollection.joinearn.com.mymall.model.db.BrandDbManager;
import mallcollection.joinearn.com.mymall.ui.activity.BrandDetailActivity;
import mallcollection.joinearn.com.mymall.ui.adapter.BrandAdapter;
import mallcollection.joinearn.com.mymall.utils.SPUtil;
import mallcollection.joinearn.com.mymall.utils.ToastUitl;

/**
 * Created by Tianpeng on 2017-08-15.
 */

public class BrandFragment extends BaseFragment {
    private RecyclerView mRecycler;
    private BrandAdapter mAdapter;
    private List<Brand> mDataList;
    private TwinklingRefreshLayout mRefreshLayout;
    private int mTopIndex = 0;
    private int mBottomIndex = 0;
    private boolean isNoMore = false;
    @Override
    protected String getTitle() {
        return "品牌";
    }

    @Override
    protected int getlayoutId() {
        Log.v("ttttt","getlayoutId BrandFragment");
        return R.layout.fragment_brand;
    }

    @Override
    protected void initView() {
        TextView text = (TextView)getActivity().findViewById(R.id.text);
        text.setText("BrandFragment");

        mAdapter = new BrandAdapter(getActivity());
        mRecycler = (RecyclerView)getActivity().findViewById(R.id.brand_recycler);
        mRecycler.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        mRecycler.setAdapter(mAdapter);

        mRefreshLayout = (TwinklingRefreshLayout)getActivity().findViewById(R.id.brand_refreshLayout);
    }

    @Override
    protected void initData() {
        SPUtil.put("brand_id", 70);
        mTopIndex = SPUtil.getInt("brand_id", 0);
        mBottomIndex = mTopIndex -20;
        mDataList = new ArrayList<Brand>();
        loadTopDataFromDatabase(mTopIndex);
    }

    //从数据库加载最新的20条数据
    private void loadTopDataFromDatabase(int topIndex){
        List<Brand> list = BrandDbManager.getInstance().getBrandList(topIndex);
        for(Brand brand : list){
            mDataList.add(0, brand);
            if((int)brand.getId() < mBottomIndex){
                mBottomIndex = (int)brand.getId();
            }
        }
        if(mBottomIndex <= 0){
            isNoMore = true;
        }
        mAdapter.setData(mDataList);
    }

    //从数据库加载20条新数据
    private void loadDataFromDatabase(){
        if(!isNoMore) {
            if(mBottomIndex <= 0){
                isNoMore = true;
            }
            List<Brand> list = BrandDbManager.getInstance().getBrandList(mBottomIndex -20, mBottomIndex);
            if (list != null) {
                int id = mBottomIndex;
                for (Brand brand : list) {
                    if (brand.getId() < mBottomIndex) {
                        mBottomIndex = (int) brand.getId();
                    }
                    if (mBottomIndex < 0) {
                        mBottomIndex = 0;
                    }
                    mDataList.add(brand);
                }
            }
        }else {
            ToastUitl.showShort("没有更多数据");
        }
        stopRefresh(true);
        mAdapter.setData(mDataList);
    }

    /**
     * 获取服务器上面的 id大于brand_id的20条数据；
     * @param addToBottom  是否追加在结尾，true 上拉的时候调用，结果追加到list结尾； false 下拉的时候调用，结果添加到顶部
     */
    private void requestData(final boolean addToBottom){
        if(addToBottom){
            loadDataFromDatabase();
        }else {
            OkGo.<String>get(ApiConstant.GET_BRAND_LIST + "?brand_id=" + mTopIndex)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            String res = response.body().toString();
                            if (!TextUtils.isEmpty(res)) {
                                JSONObject json = JSON.parseObject(res);
                                if ("OK".equals(json.getString("errmsg"))) {
                                    List<Brand> list = null;
                                    JSONObject info = json.getJSONObject("info");
                                    try {
                                        SPUtil.put("brand_id", info.getIntValue("brand_id"));
                                        mTopIndex = info.getIntValue("brand_id");
                                        list = JSONObject.parseArray(info.getString("list"), Brand.class);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    BrandDbManager.getInstance().insertBrandList(list);
                                    if (mDataList == null) {
                                        mDataList = new ArrayList<Brand>();
                                    }
                                    for (Brand brand : list) {
                                        if (addToBottom) {
                                            mDataList.add(brand);
                                        } else {
                                            mDataList.add(0, brand);
                                        }
                                    }
                                    mAdapter.setData(mDataList);
                                    stopRefresh(addToBottom);
                                } else {
                                    ToastUitl.showShort(json.getString("errmsg"));
                                    stopRefresh(addToBottom);
                                }
                            }
                        }

                        @Override
                        public void onError(Response<String> response) {
                            super.onError(response);
                            ToastUitl.showShort("连接服务器失败");
                            stopRefresh(addToBottom);
                        }
                    });
        }
    }

    private void stopRefresh(boolean addToBottom){

        if(addToBottom){
            mRefreshLayout.finishLoadmore();
        }else{
            mRefreshLayout.finishRefreshing();
        }
    }

    @Override
    protected void initListener() {
        mAdapter.setOnItemClickListener(new BrandAdapter.MyClickListener() {
            @Override
            public void onClick(View view, int index) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("brand", mDataList.get(index));
                Intent intent = new Intent();
                intent.setClass(getActivity(), BrandDetailActivity.class);
                intent.putExtra("brand", bundle);
                getActivity().startActivity(intent);
            }
        });
        mRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                requestData(true);

            }

            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                requestData(false);
            }
        });
    }
}
