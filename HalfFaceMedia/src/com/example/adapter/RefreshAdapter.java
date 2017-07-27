package com.example.adapter;

import java.util.List;

import com.example.halffacemedia.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RefreshAdapter extends BaseAdapter{
	private List<String> data;
	private LayoutInflater mInflater;
	
	public RefreshAdapter(Context context,List<String> data){
		this.mInflater=LayoutInflater.from(context);
		this.data=data;
	}

	@Override
	public int getCount() {
		return data==null?0:data.size();
	}

	@Override
	public String getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	public void addData(List<String> addData){
		data.addAll(addData);
		notifyDataSetChanged();
	}
	
	public void addOneData(String oneData){
		data.add(oneData);
		notifyDataSetChanged();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null){ // 检查convertView是否可以重复使用。 是根据item的类型和convertView是否为null判断是否可以重复使用
            convertView = mInflater.inflate(R.layout.item_refresh, parent, false);
        }
        // 找到Item视图里面的文本控件
        TextView textView = (TextView) convertView.findViewById(R.id.refresh_text1);
        textView.setText(data.get(position));
        return convertView;
	}

}
