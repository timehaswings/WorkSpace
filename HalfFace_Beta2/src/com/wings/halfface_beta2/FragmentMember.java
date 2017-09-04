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
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class FragmentMember extends Fragment{
	
	public static final String TAG=FragmentMember.class.getSimpleName();
	public static final String KEY_ICON = "icon";
	public static final String KEY_COLOR = "color";
	private PullToRefreshView mPullToRefreshView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_list_view2, container, false);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		ListView listView = (ListView) view.findViewById(R.id.member_list_view);
		final SampleAdapter adapter=new SampleAdapter(getActivity(),R.layout.list_item2, getData());
        listView.setAdapter(adapter);
        mPullToRefreshView = (PullToRefreshView) view.findViewById(R.id.member_pull_to_refresh);
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
		
	}
	
	
	private List<Map<String, Integer>> getData(){
		 Map<String, Integer> map;
		 List<Map<String, Integer>>  mSampleList = new ArrayList<Map<String, Integer>>();
        int[] icons = {R.drawable.icon_1,R.drawable.icon_2,R.drawable.icon_3,R.drawable.icon_1,R.drawable.icon_2,R.drawable.icon_3};
        int[] colors = {R.color.darksalmon,R.color.chocolate,R.color.olivedrab,R.color.darkviolet,R.color.brown,R.color.darkkhaki};
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
       
   }
}
