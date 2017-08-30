package com.wings.net;


import okhttp3.OkHttpClient;

public final class OkhttpHelper {
	
	private static OkHttpClient client;
	
	private OkhttpHelper(){};
	
	public static OkHttpClient getClient(){
		if(client==null){
			synchronized(OkhttpHelper.class){
				if(client==null){
					client=new OkHttpClient();
				}
			}
		}
		return client;
	}
	
}
