package mallcollection.joinearn.com.mymall.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import mallcollection.joinearn.com.mymall.R;
import mallcollection.joinearn.com.mymall.ui.fragment.BaseFragment;
import mallcollection.joinearn.com.mymall.ui.fragment.BrandFragment;
import mallcollection.joinearn.com.mymall.ui.fragment.DiscoverFragment;
import mallcollection.joinearn.com.mymall.ui.fragment.DressFragment;
import mallcollection.joinearn.com.mymall.ui.fragment.MineFragment;

/**
 * Created by Tianpeng on 2017-08-15.
 */

public class MainActivity extends AppCompatActivity {

    private DressFragment mDressFragment;
    private DiscoverFragment mDiscoverFragment;
    private BrandFragment mBrandFragment;
    private MineFragment mMineFragment;
    private FrameLayout mContainer;

    private FragmentManager mFm;
    private FragmentTransaction mFt;

    private BaseFragment mCurrentFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFm = getSupportFragmentManager();

        initView();
    }

    private void initView(){
        mContainer = (FrameLayout)findViewById(R.id.contentContainer);
        mDressFragment = new DressFragment();
        showFragment(mDressFragment);

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId){
                    case R.id.tab_brand:
                        if(mBrandFragment == null){
                            mBrandFragment = new BrandFragment();
                        }
                        showFragment(mBrandFragment);
                        break;
                    case R.id.tab_discover:
                        if(mDiscoverFragment == null) {
                            mDiscoverFragment = new DiscoverFragment();
                        }
                        showFragment(mDiscoverFragment);
                        break;
                    case R.id.tab_dress:
                        if(mDressFragment == null) {
                            mDressFragment = new DressFragment();
                        }
                        showFragment(mDressFragment);
                        break;
                    case R.id.tab_mine:
                        if(mMineFragment == null){
                            mMineFragment = new MineFragment();
                        }
                        showFragment(mMineFragment);
                        break;
                }
            }
        });
    }
    private void showFragment(BaseFragment fragment){
        if(fragment != null && fragment != mCurrentFragment){
            mFt = mFm.beginTransaction();
            if(mCurrentFragment != null && mCurrentFragment.isAdded()) {
                mFt.hide(mCurrentFragment);
            }
            mCurrentFragment = fragment;
            if(!fragment.isAdded()){
                mFt.add(R.id.contentContainer, fragment);
            }
            mFt.show(fragment).commit();
        }
    }
}
