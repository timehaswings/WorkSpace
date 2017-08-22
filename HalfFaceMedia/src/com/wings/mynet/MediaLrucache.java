package com.wings.mynet;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

public class MediaLrucache {
	
	private LruCache<String, Bitmap> cache;
	
	public MediaLrucache(){
		cache=new LruCache<String, Bitmap>(MediaCacheHelper.MAXSIZE){
			//必须重写此方法，来测量Bitmap的大小  
			@Override
			protected int sizeOf(String key, Bitmap value) {
				return value.getRowBytes() * value.getHeight();
			}
			
		};
	}
	
	
	/**
	 * 从内存获取bitmap
	 * @param key
	 * @return
	 */
	public Bitmap getBitmapFromCache(String key){
		return cache.get(key);
	}
	
	/**
	 * 保存bitmap到内存
	 * @param key
	 * @param bitmap
	 */
	public boolean saveBitmapToCache(String key,Bitmap bitmap){
		boolean b=false;
		if(getBitmapFromCache(key)==null && bitmap != null){
			cache.put(key, bitmap);
			b=true;
		}
		return b;
	}
	
	
}
