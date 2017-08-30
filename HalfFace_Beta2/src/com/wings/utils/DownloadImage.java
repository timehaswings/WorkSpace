package com.wings.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import com.wings.net.OkhttpHelper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadImage {
	
	/**
	 * 从网络获取bitmap
	 * @param url
	 * @return
	 */
	public static Bitmap downloadBitmap(String url){
		OkHttpClient client=OkhttpHelper.getClient();
		Bitmap bitmap=null;
		Request request=new Request.Builder().url(url).build();
		Response response;
		try {
			response = client.newCall(request).execute();
			if (response.isSuccessful()){
				InputStream is = response.body().byteStream();
				bitmap=BitmapFactory.decodeStream(is);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}
	
	
	/**
	 * 从网络获取视频截图bitmap
	 * @param url
	 * @return
	 */
	public static Bitmap downloadMediaBitmap(String url){
		MediaMetadataRetriever mmr = new MediaMetadataRetriever();  
		mmr.setDataSource(url, new HashMap<String, String>());
		Bitmap bitmap = mmr.getFrameAtTime(); 
		mmr.release();
		return bitmap;
	}
	
}
