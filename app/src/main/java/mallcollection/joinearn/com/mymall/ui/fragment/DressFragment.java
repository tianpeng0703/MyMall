package mallcollection.joinearn.com.mymall.ui.fragment;

import android.util.Log;
import android.widget.TextView;

import mallcollection.joinearn.com.mymall.R;

/**
 * Created by Tianpeng on 2017-08-15.
 */

public class DressFragment extends BaseFragment {
    @Override
    protected String getTitle() {
        return "时尚";
    }
    @Override
    protected int getlayoutId() {
        Log.v("ttttt","getlayoutId BrandFragment");
        return R.layout.fragment_dress;
    }

    @Override
    protected void initView() {
        TextView text = (TextView)getActivity().findViewById(R.id.text);
        text.setText("DressFragment");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
