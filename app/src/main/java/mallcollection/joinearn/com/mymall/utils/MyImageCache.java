package mallcollection.joinearn.com.mymall.utils;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import cn.trinea.android.common.entity.FailedReason;
import cn.trinea.android.common.service.impl.ImageCache;
import cn.trinea.android.common.service.impl.ImageMemoryCache;
import cn.trinea.android.common.util.CacheManager;

/**
 * Created by Tianpeng on 2017-08-16.
 */

public class MyImageCache {
    private static final String TAG = ImageCache.class.getSimpleName();
    public static final ImageCache IMAGE_CACHE = CacheManager.getImageCache();
    static {
        ImageMemoryCache.OnImageCallbackListener imageCallBack = new ImageMemoryCache.OnImageCallbackListener() {

            private static final long serialVersionUID = 1L;

            // callback function before get image, run on ui thread
            @Override
            public void onPreGet(String imageUrl, View view) {
                // Log.e(TAG_CACHE, "pre get image");
                //TODO 如果tag不为空，并且不等于imageUrl，设图片显示默认图片
            }

            // callback function after get image successfully, run on ui thread
            @Override
            public void onGetSuccess(String imageUrl, Bitmap loadedImage, View view, boolean isInCache) {
                // can be another view child, like textView and so on
                if (view != null && loadedImage != null && view instanceof ImageView) {
                    if(view.getTag() != null && !view.getTag().equals(imageUrl)) return;
                        ImageView imageView = (ImageView) view;
                        imageView.setImageBitmap(loadedImage);
                }
            }

            // callback function after get image failed, run on ui thread
            @Override
            public void onGetFailed(String imageUrl, Bitmap loadedImage, View view, FailedReason failedReason) {
                Log.e(TAG, new StringBuilder(128).append("get image ").append(imageUrl).append(" error")
                        .toString());
                //TODO 设图片显示加载失败图片
            }

            @Override
            public void onGetNotInCache(String imageUrl, View view) {

            }
        };
        IMAGE_CACHE.setOnImageCallbackListener(imageCallBack);
    }
}
