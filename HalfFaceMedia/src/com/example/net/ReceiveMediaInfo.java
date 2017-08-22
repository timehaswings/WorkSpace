package com.example.net;

import java.io.File;

import android.content.Context;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

public class ReceiveMediaInfo {
	
	//缓存文件名称前缀
	private final String IMAGE_NAME_PRE="half_face_";
	//缓存文件夹
	private File cacheFile;
	//缓存大小为10M
	private int cacheSize = 10 * 1024 * 1024;
	//创建缓存对象
	private	Cache cache; 
	//
	private OkHttpClient client;
	
	public ReceiveMediaInfo(Context context) {
		cacheFile=new File(context.getExternalCacheDir().toString(),"cache");
		cache=new Cache(cacheFile,cacheSize);
		client=new OkHttpClient.Builder().cache(cache).build();
	}
	
	public void receiveData(int mediaId){
		
	}
	
	public void readImage(int mediaId){
		
	}
}
