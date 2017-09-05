package com.wings.halfface_beta2;

import com.wings.widget.CustomDialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentPerson extends Fragment{
	
	private TextView member,order,collection,settings,exit;
	
	public static final String TAG=FragmentPerson.class.getSimpleName();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_person, container, false);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initView(view);
		
	}
	
	//初始化视图
	private void initView(View view){
		member=(TextView) view.findViewById(R.id.person_member);
		order=(TextView) view.findViewById(R.id.person_order);
		collection=(TextView) view.findViewById(R.id.person_collection);
		settings=(TextView) view.findViewById(R.id.person_settings);
		exit=(TextView) view.findViewById(R.id.person_exit);
		
		member.setOnClickListener(memberListener);
		order.setOnClickListener(orderListener);
		collection.setOnClickListener(collectionListener);
		settings.setOnClickListener(settingsListener);
		exit.setOnClickListener(exitListener);
	}
	
	//成为会员
	private View.OnClickListener memberListener=new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Toast.makeText(getActivity(), "功能尚未实现", Toast.LENGTH_SHORT).show();
		}
	};
	
	//订单
	private View.OnClickListener orderListener=new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Toast.makeText(getActivity(), "功能尚未实现", Toast.LENGTH_SHORT).show();
		}
	};
	
	//收藏
	private View.OnClickListener collectionListener=new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Toast.makeText(getActivity(), "功能尚未实现", Toast.LENGTH_SHORT).show();
		}
	};
	
	//设置
	private View.OnClickListener settingsListener=new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Toast.makeText(getActivity(), "功能尚未实现", Toast.LENGTH_SHORT).show();
		}
	};
	
	//退出
	private View.OnClickListener exitListener=new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			CustomDialog.Builder builder = new CustomDialog.Builder(getActivity());  
	        builder.setMessage("确定要退出应用吗？");  
	        builder.setTitle("小贴士");  
	        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {  
	            public void onClick(DialogInterface dialog, int which) {  
	                dialog.dismiss();  
	                //设置你的操作事项  
	                ((LauncherApplication)getActivity().getApplication()).removeAllActivitys();
	            }  
	        });  
	  
	        builder.setNegativeButton("取消",  
	                new android.content.DialogInterface.OnClickListener() {  
	                    public void onClick(DialogInterface dialog, int which) {  
	                        dialog.dismiss();  
	                    }  
	                });  
	  
	        builder.create().show(); 
			
		}
	};
}
