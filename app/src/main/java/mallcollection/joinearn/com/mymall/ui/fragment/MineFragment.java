package mallcollection.joinearn.com.mymall.ui.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import mallcollection.joinearn.com.mymall.R;
import mallcollection.joinearn.com.mymall.model.bean.Brand;
import mallcollection.joinearn.com.mymall.model.db.BrandDbManager;
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
        String string = "{\"address\":\"马家龙大新路88号雅芳婷中心楼东座602\",\"description\":\"<p>　<strong>　</strong></p><p>　　拥有鲜明的品牌形象，时刻掌握世界潮流，秉承优雅、时尚以及完美品质的现代都市风格，致力打造一个最具成长性和竞争力的都市女性通勤装品牌。</p><p>　　ASANFEGE的品牌核心是&ldquo;时尚无界点&rdquo;，不受时间和场所限制。</p><p>　　她们自信、独立、聪颖，精彩演绎白领女性的生活观念，忙碌而快乐充实而享受， 永恒演绎着属于自己的时尚，彰显个人品味;随意而不失优雅，得体而不失时尚;不断营造女人的梦想。</p><p>　　品牌风格/ABOUT</p><p>　　ASANFEGE将敏锐眼光聚焦于都市女性的时尚生活与美学体验，创立了打破时间和场域的通勤装风格，以适应现代女性快节奏生活的着装需求，并发展成为别具一格的&ldquo;时尚无界点&rdquo;独特的美学理念。它定位于商务与休闲的高度和谐，有浓郁的欧洲时尚气息，又十分贴合中国都市女性的审美需求，时尚而内敛，简约而干练，满足了当代白领对美和时尚的追求。</p><p>　　品牌风格优势：</p><p>　　都市女性</p><p>　　通勤装</p><p>　　欧洲时尚</p>\",\"eng_name\":\"ASANFEGE\",\"icon\":\"images/201503311722227864_120x60.jpg\",\"item_kind\":\"女装,少淑女装,少女装\",\"location\":\"深圳市悦菲服饰有限公司\",\"main_kind\":\"女装品牌\",\"name\":\"雅轩菲格\",\"point\":\"时尚、自信、舒适、品味\"}";
        Brand brand = (Brand)JSON.parseObject(string, Brand.class);
        for(int i=1; i < 50; i++) {
            brand.setId(brand.getId()+1);
            brand.setName(brand.getName()+i);
            BrandDbManager.getInstance().insertBrand(brand);
        }

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
