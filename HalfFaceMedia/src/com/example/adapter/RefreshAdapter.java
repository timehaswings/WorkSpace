package com.example.adapter;

import java.util.List;

import com.example.halffacemedia.MediaDetalsActivity;
import com.example.halffacemedia.R;
import com.example.utils.HalfFaceUtil;
import com.example.utils.ImageDeal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RefreshAdapter extends BaseAdapter implements View.OnClickListener{
	private List<String> data;
	private LayoutInflater mInflater;
	private Context context;
	
	public RefreshAdapter(Context context,List<String> data){
		this.mInflater=LayoutInflater.from(context);
		this.data=data;
		this.context=context;
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
		ViewHolder holder=null;
		if (convertView == null){ // 检查convertView是否可以重复使用。 是根据item的类型和convertView是否为null判断是否可以重复使用
			holder=new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_refresh, parent, false);
            holder.imageView=(ImageView) convertView.findViewById(R.id.refresh_image);
            holder.textView=(TextView) convertView.findViewById(R.id.refresh_text1);
            convertView.setTag(holder);
        }else{
        	holder=(ViewHolder) convertView.getTag();
        }
        // 找到Item视图里面的文本控件
		holder.textView.setText(data.get(position));
//        Bitmap bitmap=MediaDeal.getInstance().getVideoThumb(LitteMediaPlayer2.FILE_NAME);
		Bitmap bitmap=ImageDeal.getInstance().dealBitmap(HalfFaceUtil.getDataDir()+"有形的翅膀.jpg", 500, 500);
//        Bitmap bitmap=BitmapDecodeUtil.getInstance().decodeBitmap(context, HalfFaceUtil.getDataDir()+"有形的翅膀.jpg");
        holder.imageView.setImageBitmap(bitmap);
        holder.imageView.setOnClickListener(this);
        return convertView;
	}
	
	
	
	 public final class ViewHolder {  
	        public ImageView imageView;
	        public TextView textView;  
	 }



	@Override
	public void onClick(View v) {
		Intent intent=new Intent();
		intent.setClass(context, MediaDetalsActivity.class);
		context.startActivity(intent);
	}  

}
