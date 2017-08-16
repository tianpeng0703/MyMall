package mallcollection.joinearn.com.mymall.ui.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import mallcollection.joinearn.com.mymall.R;
import mallcollection.joinearn.com.mymall.utils.ToastUitl;

/**
 * Created by Tianpeng on 2017-08-15.
 */

public class MineFragment extends BaseFragment {
    private ImageView mImage;
    @Override
    protected String getTitle() {
        return "我的";
    }
    @Override
    protected int getlayoutId() {
        Log.v("ttttt","getlayoutId BrandFragment");
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {
        TextView text = (TextView)getActivity().findViewById(R.id.text);
        text.setText("MineFragment");

        mImage = (ImageView)getActivity().findViewById(R.id.image);
    }

    @Override
    protected void initData() {
        OkGo.<String>get("http://192.168.3.47:8888/doctor/index.php/main/getDoctor?docId=1")
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
//                        ToastUitl.showShort("success = "+response.body().toString());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
//                        ToastUitl.showShort("onError = "+response.body().toString());
                    }
                });

        OkGo.<Bitmap>get("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502877954850&di=5610a1070a7ced5e07785c1e1e03eee4&imgtype=0&src=http%3A%2F%2Fimage.tianjimedia.com%2FuploadImages%2F2016%2F276%2F13%2FN28MWNNE3SBY.jpg")
                .tag(this)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .execute(new BitmapCallback() {
                    @Override
                    public void onSuccess(Response<Bitmap> response) {
                        if(response.isFromCache()){
                            ToastUitl.showLong("isFromCache");
                        }else{
                            ToastUitl.showLong("not not not not not");
                        }
                        mImage.setImageBitmap(response.body());
                    }

                    @Override
                    public void onCacheSuccess(Response<Bitmap> response) {
                        super.onCacheSuccess(response);
                        mImage.setImageBitmap(response.body());
                    }
                });
    }

    @Override
    protected void initListener() {

    }

}
