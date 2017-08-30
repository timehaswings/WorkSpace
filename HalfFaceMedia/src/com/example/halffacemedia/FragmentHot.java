package com.example.halffacemedia;




import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import com.example.adapter.RefreshAdapter;
import com.example.modul.MyAsyncTask;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

public class FragmentHot extends Fragment{
	
	public static String TAG=FragmentHot.class.getSimpleName();
	private PullToRefreshGridView listView;
	private RefreshAdapter adapter;
	
	public FragmentHot(){
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_hot, container, false);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		
		listView=(PullToRefreshGridView) view.findViewById(R.id.hot_pull_refresh_grid);
		listView.setMode(Mode.BOTH);
		adapter=new RefreshAdapter(getActivity(), getData());
		listView.setAdapter(adapter);
		listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {

			@Override
			public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
				new MyAsyncTask(adapter,listView).execute();
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
				new MyAsyncTask(adapter,listView).execute();
			}
		});
		listView.getRefreshableView().setSelector(R.color.transparent);
	}
	
	public List<String> getData(){
		List<String> data=new ArrayList<String>();
		for(int i=0;i<10;i++){
			data.add("hello"+i);
		}
		return data;
	}
	
	
	
	
}
