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
        mDataList = BrandDbManager.getInstance().getBrandList(-1);
        mAdapter.setData(mDataList);
    }

    /**
     * 获取服务器上面的 id大于brand_id的20条数据；
     * @param addToBottom  是否追加在结尾，true 上拉的时候调用，结果追加到list结尾； false 下拉的时候调用，结果添加到顶部
     */
    private void requestData(final boolean addToBottom){
        OkGo.<String>get(ApiConstant.GET_BRAND_LIST+"?brand_id="+ SPUtil.getInt("brand_id", 0))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String res = response.body().toString();
                        if(!TextUtils.isEmpty(res)){
                            JSONObject json = JSON.parseObject(res);
                            if("OK".equals(json.getString("errmsg"))){
                                List<Brand> list = null;
                                JSONObject info = json.getJSONObject("info");
                                try {
                                    SPUtil.put("brand_id", info.getIntValue("brand_id"));
                                    list = JSONObject.parseArray(info.getString("list"), Brand.class);
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                                BrandDbManager.getInstance().insertBrandList(list);
                                if(mDataList == null){
                                    mDataList = new ArrayList<Brand>();
                                }
                                for(Brand brand: list){
                                    if(addToBottom){
                                        mDataList.add(brand);
                                    }else{
                                        mDataList.add(0, brand);
                                    }
                                }
                                if(addToBottom){
                                    mRefreshLayout.finishLoadmore();
                                }else{
                                    mRefreshLayout.finishRefreshing();
                                }
                            }else {
                                ToastUitl.showShort(json.getString("errmsg"));
                                if(addToBottom){
                                    mRefreshLayout.finishLoadmore();
                                }else{
                                    mRefreshLayout.finishRefreshing();
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        ToastUitl.showShort("连接服务器失败");
                        if(addToBottom){
                            mRefreshLayout.finishLoadmore();
                        }else{
                            mRefreshLayout.finishRefreshing();
                        }
                    }
                });
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
                requestData(false);
            }

            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                requestData(true);
            }
        });
    }
}
