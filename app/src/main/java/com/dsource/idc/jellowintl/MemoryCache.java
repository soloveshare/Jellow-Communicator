package com.dsource.idc.jellowintl;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

public class MemoryCache {

    private static LruCache<String, Bitmap> mMemoryCache;

    public static void init(int cacheSize){
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.getByteCount() / 1024;
            }
        };
    }

    public static void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            if(mMemoryCache != null)
            mMemoryCache.put(key, bitmap);
        }
    }

    public static Bitmap getBitmapFromMemCache(String key) {
        if(mMemoryCache != null)
        return mMemoryCache.get(key);
        else return null;
    }

}
