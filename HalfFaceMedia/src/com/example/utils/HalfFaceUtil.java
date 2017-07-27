package com.example.utils;

import java.util.ArrayList;
import java.util.List;

import com.example.halffacemedia.PagerNoval;
import com.example.halffacemedia.PagerPhotos;
import com.example.halffacemedia.PagerVideo;
import com.example.halffacemedia.PagerVoice;

import android.support.v4.app.Fragment;

public abstract class HalfFaceUtil {
	
	public static List<Fragment> getMediaFragment(){
		List<Fragment> data=new ArrayList<Fragment>();
		data.add(new PagerVideo());
		data.add(new PagerPhotos());
		data.add(new PagerNoval());
		data.add(new PagerVoice());
		return data;
	}
}
