package com.wings.utils;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

public class ImageLoader {
	
	private static final String TAG=ImageLoader.class.getSimpleName()+":";
	
	private Context context;
	private ImageView view;
	private String MD5Key;
	
	/**
	 * 加载Bitmap
	 * @param context
	 * @param url
	 * @return
	 */
	public void loadMedia(Context context,String url){
		Log.d("wings", TAG+"开始从缓存中加载图片"+url);
		this.context=context;
		this.MD5Key=ImageCacheHelper.getInstance().hashKeyForDisk(url);
		 //先到内存里面找
        Bitmap bitmap = new ImageLrucache().getBitmapFromCache(MD5Key);
		if(bitmap != null){
			if(handler != null){
				listener.onCompelet(bitmap);
			}
			Log.d("wings", TAG+"成功成内存缓存中找到图片"+url);
			return;
		}
        //否则去硬盘里找
		new FindInDiskTask().execute(url);
		return;
	}
	
	
	/**
	 * 显示Bitmap
	 * @param context
	 * @param view
	 * @param url
	 */
	public void showBitmap(Context context,ImageView view,String url){
		Log.d("wings", TAG+"开始从缓存中加载图片"+url);
		this.context=context;
		this.view=view;
		this.MD5Key=ImageCacheHelper.getInstance().hashKeyForDisk(url);
		Bitmap bitmap = new ImageLrucache().getBitmapFromCache(ImageCacheHelper.getInstance().hashKeyForDisk(url));
		if(bitmap != null){
			view.setImageBitmap(bitmap);
			Log.d("wings", TAG+"成功成内存缓存中找到图片"+url);
			return;
		}
		new FindInDiskTask().execute(url);
	}
	
	
	/**
	 * @author 301002028
	 *从硬盘中读取bitmap异步任务类
	 */
	private class FindInDiskTask extends AsyncTask<String, Void, Bitmap>{
		
		private String url;
		
		@Override
		protected Bitmap doInBackground(String... params) {
			Log.d("wings", TAG+"开始从硬盘中加载图片"+url);
			url=params[0];
			//从硬盘读取Bitmap
			ImageDiskCache cache=new ImageDiskCache(context);
			return cache.getBitmapFromDisk(MD5Key);
		}
		
		@Override
		protected void onPostExecute(Bitmap result) {
			if (result != null) {//如果硬盘里面有，直接显示
				
				//显示
				if(view != null)
					view.setImageBitmap(result);
				if(handler != null){
					Message message=new Message();
					message.arg1=ImageCacheHelper.COMPELET_OK;
					message.obj=result;
					handler.sendMessage(message);
				}
				
                //把它同步到内存
                ImageLrucache lrucache=new ImageLrucache();
                lrucache.saveBitmapToCache(MD5Key, result);
                
                Log.d("wings", TAG+"成功从硬盘缓存中找到图片"+url);
                
            } else{//说明硬盘里面没有,执行下载
            	
                new DwonLoadBitmapTask().execute(url);
            }
			
		}
	}
	
	
	/**
	 * @author 301002028
	 *下载bitmap异步任务类
	 */
	private class DwonLoadBitmapTask extends AsyncTask<String, Void, Bitmap>{
		
		private String url;
		
		@Override
		protected Bitmap doInBackground(String... params) {
			Log.d("wings", TAG+"开始从网络下载图片"+url);
			url=params[0];
			//下载bitmap
//			return DownloadMedia.downloadBitmap(url);
			return DownloadImage.downloadMediaBitmap(url);
		}
		
		@Override
		protected void onPostExecute(Bitmap result) {
			if(result != null){//下载成功，直接显示
				
				//显示
				if(view != null){
					view.setImageBitmap(result);
				}
				if(handler != null){
					Message message=new Message();
					message.arg1=ImageCacheHelper.COMPELET_OK;
					message.obj=result;
					handler.sendMessage(message);
				}
				
				//保存到硬盘
				ImageDiskCache cache=new ImageDiskCache(context);
				cache.putBitmapToDisk(MD5Key, result);
				
				 //把它同步到内存
                ImageLrucache lrucache=new ImageLrucache();
                lrucache.saveBitmapToCache(MD5Key, result);
				
                Log.d("wings", TAG+"成功从网络下载图片"+url);
			}else{
				Log.d("wings", TAG+"从网络下载图片失败"+url);
			}
		}
		
	}
	
	
	
	//接口回调
	private static LoadMediaListener listener;
	private MediaHandler handler;
	
	/**
	 * @author 301002028
	 *回调接口
	 */
	public interface LoadMediaListener{
		public void onCompelet(Bitmap bitmap);
		
	}
	
	/**
	 * 加载图片
	 * @param context
	 * @param url
	 * @param listener
	 */
	public void setOnLoadMediaListener(Context context,String url,LoadMediaListener listener){
		handler=new MediaHandler();
		this.context=context;
		ImageLoader.listener=listener;
		loadMedia(context, url);
	}
	
	/**
	 * @author 301002028
	 *handler处理异步信息
	 */
	static class MediaHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			int status = msg.arg1;
			if(status == ImageCacheHelper.COMPELET_OK)
				listener.onCompelet((Bitmap)msg.obj);
		}
	}
}
