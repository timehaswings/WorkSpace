package com.example.halffacemedia;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PagerVoice extends Fragment{
	
	public static String TAG=PagerVoice.class.getSimpleName();
	
	public PagerVoice(){
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.pager_voice, container, false);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		
	}
	
}
