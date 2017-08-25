package com.example.halffacemedia;

import java.io.File;
import java.util.List;

import com.example.data.DatabaseManager;
import com.example.data.FreeInfo;
import com.example.net.GsonHelper;
import com.example.net.MyOkhttp;
import com.example.utils.HalfFaceUtil;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentPerson extends Fragment{
	
	public static String TAG=FragmentPerson.class.getSimpleName();
	private TextView textView2,textView1;
	
	public FragmentPerson(){
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_person, container, false);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		textView1=(TextView) view.findViewById(R.id.person_text1);
		textView2=(TextView) view.findViewById(R.id.person_text2);
		
		MyOkhttp okhttp=new MyOkhttp();
		
		String string="http://172.31.84.73/json/MediaInfo.json";
		okhttp.setGsonListener(string, new MyOkhttp.OnGetNetFileListener() {
			
			@Override
			public void onCompelet(String content) {
				GsonHelper helper=new GsonHelper();
				List<FreeInfo> infos=helper.paserFile(new File(HalfFaceUtil.getDataDir()+"MediaInfo.json"));
				DatabaseManager manager=new DatabaseManager(FragmentPerson.this.getActivity());
				manager.add(infos);
				textView2.setText(manager.query().get(0).getVactor());
				manager.close();
//				textView2.setText(helper.paserFile(new File(HalfFaceUtil.getDataDir()+"MediaInfo.json")).get(0).getVactor());
			}
		});
		
	}
	
}
