package mallcollection.joinearn.com.mymall.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Tianpeng on 2017-08-15.
 */

public abstract class BaseFragment extends Fragment {
    private final static String TAG = BaseFragment.class.getSimpleName();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.v(TAG,getTitle()+"onCreateView");
        Log.v("ttttt","onCreateView");
        View view = (View)inflater.inflate(getlayoutId(), container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        initListener();
    }

    protected abstract String getTitle();
    protected abstract int getlayoutId();
    protected abstract void initView();
    protected abstract void initData();
    protected abstract void initListener();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.v(TAG, getTitle()+"onAttach");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.v(TAG, getTitle()+"onViewCreated");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, getTitle()+"onCreate");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.v(TAG, getTitle()+"onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.v(TAG, getTitle()+"onResume");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.v(TAG, getTitle()+"onSaveInstanceState");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.v(TAG, getTitle()+"onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.v(TAG, getTitle()+"onStop");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.v(TAG, getTitle()+"onConfigurationChanged");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.v(TAG, getTitle()+"onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v(TAG, getTitle()+"onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.v(TAG, getTitle()+"onDetach");
    }
}
