package com.example.modul;


import com.example.adapter.RefreshAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import android.os.AsyncTask;

public class MyAsyncTask extends AsyncTask<Void, Void, Void>{
	
	private RefreshAdapter adapter;
	private static int i=0;
	private PullToRefreshGridView gridView;
	
	public MyAsyncTask(RefreshAdapter adapter,PullToRefreshGridView gridView){
		this.adapter=adapter;
		this.gridView=gridView;
	}
	
	
	@Override
	protected Void doInBackground(Void... params) {
		try  
        {  
            Thread.sleep(2000);  
        } catch (InterruptedException e)  
        {  
        }  
        return null;
	}
	
	@Override
	protected void onProgressUpdate(Void... values) {
		
	}
	
	@Override
	protected void onPostExecute(Void result) {
		adapter.addOneData("ni hao ma?"+i++);
		gridView.onRefreshComplete();
	}
	
}
