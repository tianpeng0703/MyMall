package mallcollection.joinearn.com.mymall.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import mallcollection.joinearn.com.mymall.R;
import mallcollection.joinearn.com.mymall.model.bean.Brand;
import mallcollection.joinearn.com.mymall.utils.MyImageCache;
import mallcollection.joinearn.com.mymall.utils.ToastUitl;

/**
 * Created by Tianpeng on 2017-08-17.
 */
public class BrandDetailActivity extends AppCompatActivity {
    private ImageView mIcon;
    private TextView mPoint;
    private TextView mLocation;
    private TextView mAddress;
    private TextView mDesc;
    private TextView mItemKind;
    private TextView mEngName;
    private TextView mName;
    private Brand mBrand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_detail);
        initView();
        initData();
    }
    private void initData(){
        Bundle bundle = getIntent().getBundleExtra("brand");

        mBrand = (Brand)bundle.getSerializable("brand");
        if(mBrand == null){
            ToastUitl.showShort("未能获取该品牌信息！");
            this.finish();
        }

        MyImageCache.IMAGE_CACHE.get("http://www.jilun.net/"+mBrand.getIcon(), mIcon);
        mPoint.setText(mBrand.getPoint());
        mLocation.setText(mBrand.getLocation());
        mAddress.setText(mBrand.getAddress());
        mItemKind.setText(mBrand.getItem_kind());
        mEngName.setText(mBrand.getEng_name());
        mName.setText(mBrand.getName());
        mDesc.setText(Html.fromHtml(mBrand.getDescription()));

    }
    private void initView(){
        mIcon = (ImageView)findViewById(R.id.brand_detail_icon);
        mPoint = (TextView)findViewById(R.id.brand_detail_point);
        mLocation = (TextView)findViewById(R.id.brand_detail_location);
        mAddress = (TextView)findViewById(R.id.brand_detail_address);
        mDesc = (TextView)findViewById(R.id.brand_detail_desc);
        mItemKind = (TextView)findViewById(R.id.brand_detail_item_kind);
        mEngName = (TextView)findViewById(R.id.brand_detail_eng_name);
        mName = (TextView)findViewById(R.id.brand_detail_name);
    }
}
