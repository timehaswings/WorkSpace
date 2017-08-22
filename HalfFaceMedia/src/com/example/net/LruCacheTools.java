package com.example.net;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

public class LruCacheTools {
	
	private static LruCache<String,Bitmap>   lruCache;
    private static int maxSize=8*1024*1024;

    /**
     * 初始化
     * lruCache
     */
    static {
        lruCache=new LruCache<String,Bitmap>(maxSize){
            //开发过程中般会重写
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }

    /**
     *存放
     */
    public static void putBitmap(String key,Bitmap bitmap){
        lruCache.put(key,bitmap);
    }

    /**
     * 取图片
     */
    public static Bitmap getBitmp(String key){
        return lruCache.get(key);
    }
}
