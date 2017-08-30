package com.wings.data.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import com.wings.net.OkHttpController;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class DataProvider {
	
	private static DataProvider provider;
	private Context context;
	private List<MediaInfo> data;
	private int step=0;
	
	private DataProvider(){}
	
	public static DataProvider getInstance(){
		if(provider==null){
			synchronized (DataProvider.class) {
				if(provider==null)
					provider=new DataProvider();
			}
		}
		return provider;
	}
	
	/** 
	 * 随机指定范围内N个不重复的数 
	 * 利用HashSet的特征，只能存放不同的值 
	 * @param min 指定范围最小值 
	 * @param max 指定范围最大值 
	 * @param n 随机数个数 
	 * @param HashSet<Integer> set 随机数结果集 
	 */  
	   public void randomSet(int min, int max, int n,HashSet<Integer> set) {
	       if (n > (max - min + 1) || max < min) {  
	           return;  
	       }  
	       for (int i = 0; i < n; i++) {  
	           // 调用Math.random()方法  
	           int num = (int) (Math.random() * (max - min)) + min;  
	           set.add(num);// 将不同的数存入HashSet中  
	       }  
	       int setSize = set.size();  
	       // 如果存入的数小于指定生成的个数，则调用递归再生成剩余个数的随机数，如此循环，直到达到指定大小  
	       if (setSize < n) {  
	        randomSet(min, max, n - setSize,set);// 递归  
	       }
	   }
	   
	   
	   /** 
	    * 随机指定范围内N个不重复的数 
	    * 在初始化的无重复待选数组中随机产生一个数放入结果中， 
	    * 将待选数组被随机到的数，用待选数组(len-1)下标对应的数替换 
	    * 然后从len-2里随机产生下一个随机数，如此类推 
	    * @param max  指定范围最大值 
	    * @param min  指定范围最小值 
	    * @param n  随机数个数 
	    * @return int[] 随机数结果集 
	    */  
	   public int[] randomArray(int min,int max,int n){  
	       int len = max-min+1;  
	       if(max < min || n > len){  
	           return null;  
	       }  
	       //初始化给定范围的待选数组  
	       int[] source = new int[len];  
	          for (int i = min; i < min+len; i++){  
	           source[i-min] = i;  
	          }  
	          int[] result = new int[n];  
	          Random rd = new Random();  
	          int index = 0;  
	          for (int i = 0; i < result.length; i++) {  
	           //待选数组0到(len-2)随机一个下标  
	              index = Math.abs(rd.nextInt() % len--);  
	              //将随机到的数放入结果集  
	              result[i] = source[index];  
	              //将待选数组中被随机到的数，用待选数组(len-1)下标对应的数替换  
	              source[index] = source[len];  
	          }  
	          return result;  
	   }
	   
	   /**
	    * 获取测试数据
	 * @return
	 */
	public List<MediaInfo> getTestData(){
		   List<MediaInfo> data=new ArrayList<MediaInfo>();
		   for(int i=0;i<30;i++){
			   MediaInfo info=new MediaInfo();
			   info.setVid(i+10);
			   info.setVname("变形金刚"+i);
			   info.setVactor("尼古拉斯.凯奇"+i);
			   info.setVdesc("2017奥世卡金像奖入围Top"+i);
			   info.setVdate("217-12-03");
			   info.setVpath("http://172.31.84.75/res/video");
			   info.setVstatus(i/2);
			   data.add(info);
		   }
		   return data;
	   }
	
	/**
	 * 获取数据
	 * @return
	 */
	public List<MediaInfo> getData(Context context){
		this.step=0;
		this.context=context;
		this.data=new ArrayList<MediaInfo>();
		//产生10个随机id
		int[] vids=randomArray(1, DataConstant.REFRESH_STEP, DataConstant.REFRESH_STEP_COUNT);
		DatabaseManager manager=new DatabaseManager(context);
		for(int i=0;i<vids.length;i++){
			MediaInfo info=manager.queryOneInfo(vids[i]);
			if(info != null)
				this.data.add(info);
		}
		return this.data;
	}
	
	/**
	 * 获取数据
	 * @return
	 */
	public List<MediaInfo> addData(Context context){
		this.step+=10;
		this.context=context;
		this.data=new ArrayList<MediaInfo>();
		//产生10个随机id
		int[] vids=randomArray(step, DataConstant.REFRESH_STEP+step, DataConstant.REFRESH_STEP_COUNT);
		DatabaseManager manager=new DatabaseManager(context);
		for(int i=0;i<vids.length;i++){
			MediaInfo info=manager.queryOneInfo(vids[i]);
			if(info != null)
				this.data.add(info);
		}
		return this.data;
	}
	
	//查询数据库线程
	private class QueryTask extends AsyncTask<Void, Void, ArrayList<MediaInfo>>{
		
		private DatabaseManager manager;
		private int[] vids;
		
		public QueryTask(){
			manager=new DatabaseManager(context);
			vids=randomArray(1,20,10);
		}

		@Override
		protected ArrayList<MediaInfo> doInBackground(Void... params) {
			ArrayList<MediaInfo> data=new ArrayList<MediaInfo>();
			for(int i=0;i<vids.length;i++){
				MediaInfo info=manager.queryOneInfo(vids[i]);
				data.add(info);
			}
			return data;
		}
		
		@Override
		protected void onPostExecute(ArrayList<MediaInfo> result) {
			if(result != null){
				Message message=new Message();
				Bundle bundle=new Bundle();
				bundle.putSerializable("media", result);
				message.setData(bundle);
				handler.sendMessage(message);
			}else{
				new DownloadTask().execute();
			}
				
		}
	}
	
	
	/**
	 * @author 301002028
	 *下载json线程
	 */
	private class DownloadTask extends AsyncTask<Void, Void, Boolean>{
		
		OkHttpController controller;
		
		public DownloadTask(){
			this.controller=new OkHttpController();
		}
		
		@Override
		protected Boolean doInBackground(Void... params) {
			return controller.getMediaInfoJson(context);
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			if(result){
				int[] vids=randomArray(1,20,10);;
				ArrayList<MediaInfo> data=new ArrayList<MediaInfo>();
				DatabaseManager manager=new DatabaseManager(context);
				for(int i=0;i<vids.length;i++){
					MediaInfo info=manager.queryOneInfo(vids[i]);
					data.add(info);
				}
				Message message=new Message();
				Bundle bundle=new Bundle();
				bundle.putSerializable("media", data);
				message.setData(bundle);
				handler.sendMessage(message);
			}
		}
	}
	
	/**
	 * 从网络写入数据至数据库
	 * @param context
	 */
	public void writeJson(Context context){
		this.context=context;
		if(context != null)
			new DownloadTask().execute();
	}
	
	//接口回调
	public interface ReceiveJson{
		public void onCompelete(List<MediaInfo> infos);
	}
	
	private static ReceiveJson receive;
	private DataHandler handler;
	
	/**
	 * 设置数据监听
	 * @param context
	 * @param receive
	 */
	public void setOnReceiveJsonListener(Context context,ReceiveJson receive){
		DataProvider.receive=receive;
		this.context=context;
		handler=new DataHandler();
		new QueryTask().execute();
	}
	
	//信息传递
	static class DataHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			Bundle bundle=msg.getData();
			@SuppressWarnings("unchecked")
			ArrayList<MediaInfo> infos=(ArrayList<MediaInfo>) bundle.getSerializable("media");
			receive.onCompelete(infos);
		}
	}
	
}
