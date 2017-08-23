package mallcollection.joinearn.com.mymall.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mallcollection.joinearn.com.mymall.R;
import mallcollection.joinearn.com.mymall.model.bean.Token;
import mallcollection.joinearn.com.mymall.ui.adapter.TokenAdapter;

/**
 * Created by Tianpeng on 2017-08-15.
 */

public class TokenFragment extends BaseFragment {
    private RecyclerView mRecycler;
    private TokenAdapter mAdapter;
    private List<Token> mDataList;

    @Override
    protected String getTitle() {
        return "超级券";
    }
    @Override
    protected int getlayoutId() {
        Log.v("ttttt","getlayoutId BrandFragment");
        return R.layout.fragment_token;
    }

    @Override
    protected void initView() {
        TextView text = (TextView)getActivity().findViewById(R.id.text);
        text.setText("DiscoverFragment");

        mRecycler = (RecyclerView)getActivity().findViewById(R.id.token_recycler);
        mAdapter = new TokenAdapter(getActivity());

        mRecycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {

        mDataList = new ArrayList<Token>();
        for (int i=0 ; i < 10; i++){
            Token token = new Token();
            token.setDiscount_content("满30减20");
            token.setDiscount_url("https://uland.taobao.com/coupon/edetail?e=FGEP47%2FX5K4N%2BoQUE6FNzCEcUKQ%2FafdLUgF%2FXEeBY4QqTY4%2BJWyIVn7tVGp8iwkR3O2xAsmnOWJDBvrVP5%2BCUh0HgBdG%2FDDL%2F1M%2FBw7Sf%2Fe1DeJtCzgpHgdp1tQbGiunau%2BXcC60USmJtfqMFwKaJw11doIRRKPO&pid=mm_110385786_9922835_33046334&af=1");
            token.setItem_img("http://img.alicdn.com/bao/uploaded/i8/TB1icR_RVXXXXaVXVXXYXGcGpXX_M2.SS2");
            token.setItem_name("记忆棉u型枕头护颈枕颈椎U形旅行脖枕飞机头枕护脖子午睡颈枕");
            token.setItem_price("45");
            token.setItem_url("https://s.click.taobao.com/t?e=m%3D2%26s%3Di2PXU4PKX8kcQipKwQzePOeEDrYVVa64K7Vc7tFgwiG3bLqV5UHdqXJNoMO5ECflPx3RkWSJTux7l3s6UQV5O8bTiktnEiHLgCkDwfuI%2F9wHYvxiStQHgwH7ISSyxoQmvuyVNhlLmUVc3%2FVm%2FiTib54uqTXfTZviDp2Zssvyar1fTcI8RUBiJZs3QNlAP0NN%2FnzNcj6fQr6f389GtiWo0aJn5AyUbPoV");
            token.setItem_sale_count("1344");
            token.setShop_name("耐克官方旗舰店");
            token.setShop_type("淘宝");
            mDataList.add(token);
        }

        mAdapter.setData(mDataList);

    }

    @Override
    protected void initListener() {

    }
}
