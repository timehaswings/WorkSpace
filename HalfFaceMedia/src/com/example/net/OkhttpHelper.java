package com.example.net;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public final class OkhttpHelper {
	
	private static OkHttpClient client;
	private static List<Call> calls;
	
	private OkhttpHelper(){};
	
	public static OkHttpClient getClient(){
		if(client==null){
			synchronized(OkhttpHelper.class){
				if(client==null){
					client=new OkHttpClient();
				}
				if(calls==null){
					calls=new ArrayList<Call>();
				}
			}
		}
		return client;
	}
	
	public Call getCall(Request request){
		if(calls==null){
			return null;
		}
		Call call=client.newCall(request);
		calls.add(call);
		return call;
	}
	
	
	public void cancel(){
		if(calls != null && calls.size()>0){
			for(Call call:calls){
				call.cancel();
			}
			calls.clear();
		}
		if(client != null){
			client=null;
		}
	}
	
	
}
