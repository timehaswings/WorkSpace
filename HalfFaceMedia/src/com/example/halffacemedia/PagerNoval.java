package com.example.halffacemedia;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PagerNoval extends Fragment{
	
	public static String TAG=PagerNoval.class.getSimpleName();
	
	public PagerNoval(){
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.pager_noval, container, false);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		
	}
	
}
