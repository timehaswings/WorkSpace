package com.wings.mynet;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.Toast;

public class MediaLodar {
	
	private Context context;
	private ImageView view;
	private String MD5Key;
	
	/**
	 * 加载Bitmap
	 * @param context
	 * @param url
	 * @return
	 */
	public Bitmap loadMedia(Context context,String url){
		this.context=context;
		this.MD5Key=MediaCacheHelper.getInstance().hashKeyForDisk(url);
		 //先到内存里面找
        Bitmap bitmap = new MediaLrucache().getBitmapFromCache(MD5Key);
		if(bitmap != null){
			if(handler != null){
				listener.onCompelet(bitmap);
			}
			return bitmap;
		}
        //否则去硬盘里找
		new FindInDiskTask().execute(url);
		return null;
	}
	
	
	/**
	 * 显示Bitmap
	 * @param context
	 * @param view
	 * @param url
	 */
	public void showBitmap(Context context,ImageView view,String url){
		this.context=context;
		this.view=view;
		this.MD5Key=MediaCacheHelper.getInstance().hashKeyForDisk(url);
		Bitmap bitmap = new MediaLrucache().getBitmapFromCache(MediaCacheHelper.getInstance().hashKeyForDisk(url));
		if(bitmap != null){
			view.setImageBitmap(bitmap);
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
			url=params[0];
			//从硬盘读取Bitmap
			MediaDiskCache cache=new MediaDiskCache(context);
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
					message.arg1=MediaCacheHelper.COMPELET_OK;
					message.obj=result;
					handler.sendMessage(message);
				}
				
                //把它同步到内存
                MediaLrucache lrucache=new MediaLrucache();
                lrucache.saveBitmapToCache(MD5Key, result);
                
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
			url=params[0];
			//下载bitmap
//			return DownloadMedia.downloadBitmap(url);
			return DownloadMedia.downloadMediaBitmap(url);
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
					message.arg1=MediaCacheHelper.COMPELET_OK;
					message.obj=result;
					handler.sendMessage(message);
				}
				
				//保存到硬盘
				MediaDiskCache cache=new MediaDiskCache(context);
				cache.putBitmapToDisk(MD5Key, result);
				
				 //把它同步到内存
                MediaLrucache lrucache=new MediaLrucache();
                lrucache.saveBitmapToCache(MD5Key, result);
				
			}else{
				Toast.makeText(context, "图片走丢了", Toast.LENGTH_SHORT).show();
			}
		}
		
	}
	
	
	
	//接口回调
	private static LoadMediaListener listener;
	private MediaHandler handler;
	
	public interface LoadMediaListener{
		public void onCompelet(Bitmap bitmap);
		
	}
	
	public void setOnLoadMediaListener(Context context,String url,LoadMediaListener listener){
		handler=new MediaHandler();
		this.context=context;
		MediaLodar.listener=listener;
		loadMedia(context, url);
	}
	
	static class MediaHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			int status = msg.arg1;
			if(status == MediaCacheHelper.COMPELET_OK)
				listener.onCompelet((Bitmap)msg.obj);
			else
				listener.onCompelet(null);
		}
	}
}
