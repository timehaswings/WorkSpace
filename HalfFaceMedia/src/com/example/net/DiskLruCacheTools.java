package com.example.net;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;

import com.jakewharton.disklrucache.DiskLruCache;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class DiskLruCacheTools {
	private static DiskLruCache diskLruCache;
    private static int maxSize=8*1024*1024;

    /**
     * 初始化硬盘缓存对象
     * @param context
     */
    public  static  void init(Context context){
        /**
         * Opens the cache in {@code directory}, creating a cache if none exists
         * there.
         * @param directory a writable directory
         * @param appVersion 当前系统的版本号
         * @param valueCount the number of values per cache entry. Must be positive.默认写1
         * @param maxSize the maximum number of bytes this cache should use to store  硬盘缓存区的大小
         * @throws IOException if reading or writing the cache directory fails
         */
        try {
            diskLruCache=DiskLruCache.open(context.getCacheDir(),getSystemVersionCode(context),1,maxSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 取硬盘里面取图片
     * @param path
     * @return
     */
    public static Bitmap getBitmapFromDisk(String path){
        //加密地址
        String key=getCacheKey(path);
        InputStream inputStream=null;
        try {
            //Key的正则为[a-z0-9_-]{1,64}
            DiskLruCache.Snapshot snapshot = diskLruCache.get(key);
            if(snapshot!=null){
                 inputStream = snapshot.getInputStream(0);
                return BitmapFactory.decodeStream(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            close(inputStream);
        }
        return null;
    }

    /**
     * 向硬盘里面写数据
     * @param path
     * @param bitmap
     */
    public  static void putBitmapToDisk(String path,Bitmap bitmap){
        //加密地址
        String key=getCacheKey(path);
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
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(os);
        }
    }

    /**
     * 得到系统当前的版本号
     */
    public static int getSystemVersionCode(Context context){
        try {
            PackageInfo packageInfo=context.getPackageManager().getPackageInfo(context.getPackageName(),0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;

    }

    /**
     * 加密方法
     */
    public static String getCacheKey(String path){
       try{
           MessageDigest md = MessageDigest.getInstance("MD5");
           md.update(path.getBytes());
           byte[] b = md.digest();
           StringBuffer strBuffer = new StringBuffer();
           for (int i = 0; i < b.length; i++) {
               strBuffer.append(Integer.toHexString(Math.abs(b[i])));
           }
           return strBuffer.toString();
       }catch (Exception e){
           e.printStackTrace();
       }
        return String.valueOf(path.hashCode());
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
}
