package mallcollection.joinearn.com.mymall.ui.fragment;

import android.util.Log;
import android.widget.TextView;

import mallcollection.joinearn.com.mymall.R;

/**
 * Created by Tianpeng on 2017-08-15.
 */

public class TokenFragment extends BaseFragment {
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
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
