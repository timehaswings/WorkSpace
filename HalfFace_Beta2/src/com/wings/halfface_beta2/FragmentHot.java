package com.wings.halfface_beta2;


import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.wings.data.storage.DataProvider;
import com.wings.widget.RefreshAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FragmentHot extends Fragment{
	
	public static final String TAG=FragmentHot.class.getSimpleName();
	private PullToRefreshListView list;
	private DataProvider provider;
	private RefreshAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_hot, container, false);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		//初始化
		provider=DataProvider.getInstance();
		//初始化数据
		adapter=new RefreshAdapter(getActivity(), provider.getData(getActivity()));
//		adapter=new RefreshAdapter(getActivity(), provider.getTestData());
//		DataProvider.getInstance().setOnReceiveJsonListener(getActivity(), new DataProvider.ReceiveJson() {
//			
//			@Override
//			public void onCompelete(List<MediaInfo> infos) {
//				adapter=new RefreshAdapter(getActivity(), infos);
//			}
//		});
		
		//寻找控件
		list=(PullToRefreshListView) view.findViewById(R.id.hot_pull_refresh_list);
		list.setPullLabel("下拉刷新...");// 刚下拉时，显示的提示    
		list.setRefreshingLabel("正在载入...");// 刷新时    
		list.setReleaseLabel("放开刷新...");// 下来达到一定距离时，显示的提示   
		list.setPullLabel("上拉刷新...");// 刚下拉时，显示的提示    
		list.setRefreshingLabel("正在载入...");// 刷新时    
		list.setReleaseLabel("放开刷新...");// 下来达到一定距离时，显示的提示    
		list.setMode(Mode.BOTH);
		list.setAdapter(adapter);
		list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
				new RefreshTask().execute();
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				new AddTask().execute();
			}

		});
		list.getRefreshableView().setSelector(R.color.transparent);
	}
	
	
	private class RefreshTask extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			adapter.refreshData();
			list.onRefreshComplete();
		}
	}
	
	
	private class AddTask extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			adapter.addLastData();
			list.onRefreshComplete();
		}
	}
	
}
