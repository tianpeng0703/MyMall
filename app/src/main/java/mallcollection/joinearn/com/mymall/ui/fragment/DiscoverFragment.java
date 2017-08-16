package mallcollection.joinearn.com.mymall.ui.fragment;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.model.Response;

import mallcollection.joinearn.com.mymall.R;

/**
 * Created by Tianpeng on 2017-08-15.
 */

public class DiscoverFragment extends BaseFragment {
    @Override
    protected String getTitle() {
        return "发现";
    }
    @Override
    protected int getlayoutId() {
        Log.v("ttttt","getlayoutId BrandFragment");
        return R.layout.fragment_discover;
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
