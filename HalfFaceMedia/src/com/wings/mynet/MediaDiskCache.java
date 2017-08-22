package com.wings.mynet;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.jakewharton.disklrucache.DiskLruCache;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

public class MediaDiskCache {
	
	private DiskLruCache diskLruCache;
	
	public MediaDiskCache(Context context){
		 try {
			 //第一个参数指定的是数据的缓存地址，第二个参数指定当前应用程序的版本号，
			 //第三个参数指定同一个key可以对应多少个缓存文件，基本都是传1，第四个参数指定最多可以缓存多少字节的数据。
				File cacheDir = getDiskCacheDir(context, "bitmap");
				if (!cacheDir.exists()) {
					cacheDir.mkdirs();
				}
		         diskLruCache=DiskLruCache.open(cacheDir,getSystemVersionCode(context),1,MediaCacheHelper.MAXSIZE);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
	/**
     * 得到系统当前的版本号
     */
    public int getSystemVersionCode(Context context){
        try {
            PackageInfo packageInfo=context.getPackageManager().getPackageInfo(context.getPackageName(),0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;

    }
    /**
    通常都会存放在 /sdcard/Android/data/<application package>/cache 这个路径下面，但同时我们又需要考虑如果这个手机没有SD卡，或者SD正好被移除了的情况，
    */
    public File getDiskCacheDir(Context context, String uniqueName) {
    	String cachePath;
    	if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
    			|| !Environment.isExternalStorageRemovable()) {
    		cachePath = context.getExternalCacheDir().getPath();
    	} else {
    		cachePath = context.getCacheDir().getPath();
    	}
    	return new File(cachePath + File.separator + uniqueName);
    }
	
    /**
     * 关流
     */
    public static void close(Closeable closeable){
        try{
            if(null==closeable){
                return;
            }
            closeable.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * 向硬盘里面写数据
     * @param path
     * @param bitmap
     */
    public boolean putBitmapToDisk(String key,Bitmap bitmap){
    	boolean b=false;
        OutputStream  os=null;
        try{
            DiskLruCache.Editor edit = diskLruCache.edit(key);
            if(edit!=null){
                os=edit.newOutputStream(0);
                //把Bitmap写入硬盘
                boolean compress = bitmap.compress(Bitmap.CompressFormat.PNG, 80, os);
                if(compress){
                    edit.commit();
                }else{
                    edit.abort();
                }
                b=true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(os);
        }
        return b;
    }
    
    
    /**
     * 从硬盘读取Bitmap
     * @param path
     * @return
     */
    public Bitmap getBitmapFromDisk(String key){
    	Bitmap bitmap=null;
    	try {
    		DiskLruCache.Snapshot snapShot = diskLruCache.get(key);
    		if (snapShot != null) {
    			InputStream is = snapShot.getInputStream(0);
    			bitmap = BitmapFactory.decodeStream(is);
    		}
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return bitmap;
    }
}
