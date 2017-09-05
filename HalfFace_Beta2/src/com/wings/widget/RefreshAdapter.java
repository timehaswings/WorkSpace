package com.wings.widget;

import java.util.List;

import com.wings.data.storage.DataProvider;
import com.wings.data.storage.MediaInfo;
import com.wings.halfface_beta2.R;
import com.wings.media.module.MediaViewLarge;
import com.wings.utils.ImageLoader;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RefreshAdapter extends BaseAdapter {
	
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
		ViewHolder holder=new ViewHolder();
		if(convertView == null){
			convertView=mInflater.inflate(R.layout.refresh_item, parent, false);
			holder.videoImage=(ImageView) convertView.findViewById(R.id.refresh_video_img);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		TextView vname=(TextView) convertView.findViewById(R.id.refresh_vname);
		TextView vactor=(TextView) convertView.findViewById(R.id.refresh_vactor);
		TextView vdesc=(TextView) convertView.findViewById(R.id.refresh_vdesc);
		TextView vdate=(TextView) convertView.findViewById(R.id.refresh_date);
//		ImageView videoImage=(ImageView) convertView.findViewById(R.id.refresh_video_img);
		vname.setText(getItem(position).getVname());
		vactor.setText(getItem(position).getVactor());
		vdesc.setText(getItem(position).getVdesc());
		vdate.setText(getItem(position).getVdate());
		setImage(holder.videoImage);
		holder.videoImage.setOnClickListener(new ImageListener(position));
		return convertView;
	}
	
	class ViewHolder{
		ImageView videoImage;
	}

	//跳转至播放界面
	private class ImageListener implements View.OnClickListener{
		private int position;
		public ImageListener(int position){
			this.position=position;
		}
		@Override
		public void onClick(View v) {
			Intent intent=new Intent(context,MediaViewLarge.class);
			intent.putExtra("info", getItem(position));
			context.startActivity(intent);
		}
	}
	
	//设置图片
	public void setImage(final ImageView imageView){
		ImageLoader loader=new ImageLoader();
		loader.showBitmap(context, imageView, "file://"+"//sdcard//v001.mp4");
	}
	
}
