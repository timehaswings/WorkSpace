package com.example.net;

import com.example.utils.HalfFaceUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;

public class ImageLoader {
	
	 /**
     * 加载图片
     * @param context
     * @param iv
     * @param path
     */
    public static Bitmap loadImage(Context context, String path) {

        //先到内存里面找
        Bitmap bitmap = LruCacheTools.getBitmp(path);
        if (bitmap != null) {//如果内存里面有，就直接显示,即一级缓存
           return bitmap;

        }
        //如果内存里面没有，去硬盘里面找
        new DiskAsyncTask(HalfFaceUtil.URL_IMAGE01).execute(path);
        
    	return null;
    }
	
    
    /**
     * 创建一个从硬盘取图片的类
     */

    private static class DiskAsyncTask extends AsyncTask<String, Void, Bitmap> {
        private String path;
        private String url;
        
        public DiskAsyncTask(String url){
        	this.url=url;
        }
        
        @Override
        protected Bitmap doInBackground(String... params) {
            path = params[0];
            return DiskLruCacheTools.getBitmapFromDisk(path);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {//如果硬盘里面有，直接显示
                //把它同步到内存
                LruCacheTools.putBitmap(path, bitmap);
            } else{
            	//说明硬盘里面没有,执行下载
                new DownLoadImageAsycTask(url).execute(path);
            }
        }
    }
    
    /**
     * 下载图片的异步任务
     */
    private static class DownLoadImageAsycTask extends AsyncTask<String, Void, Bitmap> {
        private String path;
        private String url;
        
        public DownLoadImageAsycTask(String url){
        	this.url=url;
        }
        
        @Override
        protected Bitmap doInBackground(String... params) {
            path = params[0];
            MyOkhttp okhttp=new MyOkhttp();
            return okhttp.downloadImage(url, path);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {//说明下载成功
                //写入硬盘
                DiskLruCacheTools.putBitmapToDisk(path,bitmap);
                //写入内存缓存
                LruCacheTools.putBitmap(path,bitmap);
            } else{//下载失败
                System.out.println("----URL不正确");
            }
        }
    }


}
