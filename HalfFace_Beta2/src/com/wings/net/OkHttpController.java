package com.wings.net;

import java.io.IOException;
import java.util.List;

import com.wings.data.storage.DatabaseManager;
import com.wings.data.storage.GsonHelper;
import com.wings.data.storage.MediaInfo;

import android.content.Context;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpController {
	
	private OkHttpClient client;
	
	public OkHttpController(){
		this.client=OkhttpHelper.getClient();
	}
	
	/**
	 * 获取MediaInfo.json并写入数据库,同步线程
	 * @param url
	 */
	public boolean getMediaInfoJson(final Context context){
		boolean b=false;
		Request request=new Request.Builder().url(NetURL.MEDIA_JSON).build();
		Call call=client.newCall(request);
		final GsonHelper helper=new GsonHelper();
		final DatabaseManager manager=new DatabaseManager(context);
		try {
			Response response=call.execute();
			String json=response.body().string();
			List<MediaInfo> data=helper.fromJsonList(json, MediaInfo.class);//解析json
			manager.add(data);
			manager.close();
			b=true;
		} catch (IOException e) {
			e.printStackTrace();
			b=false;
		}
		return b;
	}
	
}
