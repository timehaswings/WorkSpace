package com.example.halffacemedia;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PagerPhotos extends Fragment{
	
	public static String TAG=PagerPhotos.class.getSimpleName();
	
	public PagerPhotos(){
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.pager_photos, container, false);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		
	}
	
}