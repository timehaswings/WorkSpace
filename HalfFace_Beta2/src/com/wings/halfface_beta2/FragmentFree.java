package com.wings.halfface_beta2;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yalantis.phoenix.PullToRefreshView;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class FragmentFree extends Fragment{
	
	public static final String TAG=FragmentFree.class.getSimpleName();
	public static final String KEY_ICON = "icon";
	public static final String KEY_COLOR = "color";
	
	private PullToRefreshView mPullToRefreshView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//		return inflater.inflate(R.layout.fragment_free, container, false);
		
		ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_list_view, container, false);
		
		ListView listView = (ListView) rootView.findViewById(R.id.list_view);
		final SampleAdapter adapter=new SampleAdapter(getActivity(),R.layout.list_item, getData());
        listView.setAdapter(adapter);

        mPullToRefreshView = (PullToRefreshView) rootView.findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        
        listView.setOnScrollListener(new ListView.OnScrollListener() {
        	boolean isLastRow = false;
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				//正在滚动时回调，回调2-3次，手指没抛则回调2次。scrollState = 2的这次不回调    
	            //回调顺序如下    
	            //第1次：scrollState = SCROLL_STATE_TOUCH_SCROLL(1) 正在滚动    
	            //第2次：scrollState = SCROLL_STATE_FLING(2) 手指做了抛的动作（手指离开屏幕前，用力滑了一下）    
	            //第3次：scrollState = SCROLL_STATE_IDLE(0) 停止滚动             
	            //当屏幕停止滚动时为0；当屏幕滚动且用户使用的触碰或手指还在屏幕上时为1；  
	            //由于用户的操作，屏幕产生惯性滑动时为2  
	            //当滚到最后一行且停止滚动时，执行加载    
	            if (isLastRow && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {    
	                //加载元素    
	            	adapter.addData(getData());
	                isLastRow = false;    
	            }    
			}
			//滚动时一直回调，直到停止滚动时才停止回调。单击时回调一次。
            //firstVisibleItem：当前能看见的第一个列表项ID（从0开始）    
            //visibleItemCount：当前能看见的列表项个数（小半个也算）    
            //totalItemCount：列表项共数   
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				//判断是否滚到最后一行    
	            if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount > 0) { 
	            	isLastRow=true;
	            }    
			}
		});
        
        return rootView;
		
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
	}
	
	private List<Map<String, Integer>> getData(){
		 Map<String, Integer> map;
		 List<Map<String, Integer>>  mSampleList = new ArrayList<Map<String, Integer>>();
         int[] icons = {R.drawable.icon_1,R.drawable.icon_2,R.drawable.icon_3,R.drawable.icon_1,R.drawable.icon_2,R.drawable.icon_3};
         int[] colors = {R.color.saffron,R.color.eggplant,R.color.sienna,R.color.saffron,R.color.eggplant,R.color.sienna};
         for (int i = 0; i < icons.length; i++) {
            map = new HashMap<String, Integer>();
            map.put(KEY_ICON, icons[i]);
            map.put(KEY_COLOR, colors[i]);
            mSampleList.add(map);
         }
         return mSampleList;
	}
	
	private class SampleAdapter extends ArrayAdapter<Map<String, Integer>> {

        public static final String KEY_ICON = "icon";
        public static final String KEY_COLOR = "color";

        private final LayoutInflater mInflater;
        private final List<Map<String, Integer>> mData;

        public SampleAdapter(Context context, int layoutResourceId, List<Map<String, Integer>> data) {
            super(context, layoutResourceId, data);
            mData = data;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.list_item, parent, false);
                viewHolder.imageViewIcon = (ImageView) convertView.findViewById(R.id.image_view_icon);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.imageViewIcon.setImageResource(mData.get(position).get(KEY_ICON));
            convertView.setBackgroundResource(mData.get(position).get(KEY_COLOR));

            return convertView;
        }

        class ViewHolder {
            ImageView imageViewIcon;
        }
        
        
        public void addData(List<Map<String, Integer>> mData){
        	this.mData.addAll(mData);
        	notifyDataSetChanged();
        }
    }
}
