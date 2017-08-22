package com.example.net;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.example.utils.HalfFaceUtil;
import com.example.utils.ImageDeal;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public final class MyOkhttp {
	
	public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("application/json; charset=utf-8");
	
	private OkHttpClient client;
	private MyHandler handler;
	private static OnGetNetFileListener listener;
	
	public MyOkhttp(){
		client=OkhttpHelper.getClient();
		handler=new MyHandler();
	}
	
//	public void CacheResponse(Context context) throws Exception {
//	    int cacheSize = 10 * 1024 * 1024; // 10 MiB
//	    String okhttpCachePath = context.getCacheDir().getPath() + File.separator + "okhttp";
//	    File okhttpCache = new File(okhttpCachePath);
//	    if(!okhttpCache.exists()){
//	        okhttpCache.mkdirs();
//	    }
//	    Cache cache = new Cache(okhttpCache, cacheSize);
//	    client = new OkHttpClient.Builder()
//	        .cache(cache)
//	        .build();
//	  }
	
	public interface OnGetNetFileListener{
		
		/**
		 * 获取内容完成
		 * @param content
		 */
		public void onCompelet(String content);
		
	}
	
	/**
	 * GetNetFile监听
	 * @param url
	 * @param listener
	 */
	public void setGetNetFileListener(String url,OnGetNetFileListener listener){
		MyOkhttp.listener=listener;
		try {
			getNetFile(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * postAsync监听
	 * @param url
	 * @param data
	 * @param listener
	 */
	public void setPostListener(String url,Map<String, String> data,OnGetNetFileListener listener){
		MyOkhttp.listener=listener;
		postAsync(url,data);
	}
	
	/**
	 * postAsync监听
	 * @param url
	 * @param data
	 * @param listener
	 */
	public void setGsonListener(String url,OnGetNetFileListener listener){
		MyOkhttp.listener=listener;
		getJson(url);
	}
	
	/**
	 * 异步get请求
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public void getNetFile(String url) throws IOException{
		//创建一个Request
		final Request request = new Request.Builder().url(url).build();
		//new call
		Call call = client.newCall(request); 
		//请求加入调度，enqueue方法不会阻塞当前线程，会新开一个工作线程，让实际的网络请求在工作线程中执行
		call.enqueue(new Callback()
		        {
					@Override
					public void onFailure(Call call, IOException exception) {
						
					}
					@Override
					public void onResponse(Call call, Response response) throws IOException {
						Message msg=new Message();
						msg.obj=response.body().string();
						handler.sendMessage(msg);
					}
		 });
	}
	
	
	/**
	 * post,异步
	 * @param url
	 */
	public void postAsync(String url,Map<String,String> data){
		 FormBody.Builder builder=new FormBody.Builder();
		 Set<Entry<String, String>> set=data.entrySet();
		 Iterator<Entry<String, String>> it=set.iterator();
		 while(it.hasNext()){
			 Entry<String, String> entry=it.next();
			 builder.add(entry.getKey(), entry.getValue());
		 }
		 
		 RequestBody formBody = builder.build();
		 
		 final Request request = new Request.Builder()
				.url(url)
				.post(formBody)
				.build();
		
		 Call call = client.newCall(request); 
		
		//请求加入调度，enqueue方法不会阻塞当前线程，会新开一个工作线程，让实际的网络请求在工作线程中执行
		call.enqueue(new Callback()
		        {
					@Override
					public void onFailure(Call call, IOException exception) {
						
					}
					@Override
					public void onResponse(Call call, Response response) throws IOException {
						Message msg=new Message();
						msg.obj=response.body().string();
						handler.sendMessage(msg);
					}
		 });
	}
	
	/**
	 * 获取json
	 * @param url
	 */
	public void getJson(String url){
		Request request=new Request.Builder().url(url).build();
		Call call=client.newCall(request);
		call.enqueue(new Callback() {
			@Override
			public void onResponse(Call arg0, Response response) throws IOException {
				InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                try {
                    is = response.body().byteStream();
                    long i=response.body().contentLength();
                    File file=new File(HalfFaceUtil.getDataDir()+"MediaInfo.json");
                    if(!file.exists())
                    	file.createNewFile();
                    if(file.length() != i){
                    	fos = new FileOutputStream(file);
                        while ((len = is.read(buf)) != -1) {
                            fos.write(buf, 0, len);
                        }
                        fos.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (is != null) 
                            is.close();
                        if (fos != null) 
                            fos.close();
                    } catch (IOException e) {
                    	e.printStackTrace();
                    }
                }
                Message msg=new Message();
                msg.arg1=1;
                handler.sendMessage(msg);
			}
			
			@Override
			public void onFailure(Call arg0, IOException arg1) {
				
			}
		});
	}
	
	/**
	 * 下载媒体文件
	 * @param url
	 */
	public Bitmap downloadImage(String url,final String path){
		Bitmap bitmap=null;
		Request request=new Request.Builder().url(url).build();
		Call call=client.newCall(request);
		try {
			Response response=call.execute();
			InputStream is = null;
            byte[] buf = new byte[2048];
            int len = 0;
            FileOutputStream fos = null;
            try {
                is = response.body().byteStream();
                File file=new File(path);
                if(!file.exists())
                	file.createNewFile();
                fos = new FileOutputStream(file);
                while ((len = is.read(buf)) != -1) {
                    fos.write(buf, 0, len);
                }
                fos.flush();
                bitmap=ImageDeal.getInstance().dealBitmap(path, 60, 60);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (is != null) 
                        is.close();
                    if (fos != null) 
                        fos.close();
                } catch (IOException e) {
                	e.printStackTrace();
                }
            }
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return bitmap;
	}
	
	/**
	 * 此处使用此方式是为了避免内存泄漏
	 * @author 301002028
	 *
	 */
	static class MyHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			String content=(String) msg.obj;
			listener.onCompelet(content);
		}
	}
	
}
