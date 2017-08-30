package com.wings.widget;

import java.util.List;

import com.wings.data.storage.DataProvider;
import com.wings.data.storage.MediaInfo;
import com.wings.halfface_beta2.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RefreshAdapter extends BaseAdapter{
	
	private LayoutInflater mInflater;
	private List<MediaInfo> data;
	private Context context;
	
	public RefreshAdapter(Context context,List<MediaInfo> data){
		mInflater=LayoutInflater.from(context);
		this.context=context;
		this.data=data;
	}
	
	public RefreshAdapter(Context context){
		mInflater=LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		return data==null ? 0:data.size();
	}

	@Override
	public MediaInfo getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	//刷新数据
	public void refreshData(){
		this.data=null;
		this.data=DataProvider.getInstance().getData(context);
		notifyDataSetChanged();
	}
	
	//尾部填加数据
	public void addLastData(){
		this.data.addAll(DataProvider.getInstance().addData(context));
		notifyDataSetChanged();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView=mInflater.inflate(R.layout.refresh_item, parent, false);
		}
		TextView vname=(TextView) convertView.findViewById(R.id.refresh_vname);
		TextView vactor=(TextView) convertView.findViewById(R.id.refresh_vactor);
		TextView vdesc=(TextView) convertView.findViewById(R.id.refresh_vdesc);
		TextView vdate=(TextView) convertView.findViewById(R.id.refresh_date);
		vname.setText(getItem(position).getVname());
		vactor.setText(getItem(position).getVactor());
		vdesc.setText(getItem(position).getVdesc());
		vdate.setText(getItem(position).getVdate());
		return convertView;
	}
	
	
}
