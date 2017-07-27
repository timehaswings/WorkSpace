package com.example.adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.halffacemedia.PagerNoval;
import com.example.halffacemedia.PagerPhotos;
import com.example.halffacemedia.PagerVideo;
import com.example.halffacemedia.PagerVoice;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

public class MainPagerAdapter extends FragmentPagerAdapter{
	
	private List<Fragment> data;
	private FragmentManager fm;
	private int viewId;
	
	public MainPagerAdapter(FragmentManager fm,int viewId) {
		super(fm);
		this.fm=fm;
		data=new ArrayList<Fragment>();
		data.add(new PagerVideo());
		data.add(new PagerPhotos());
		data.add(new PagerNoval());
		data.add(new PagerVoice());
	}
	
	public MainPagerAdapter(FragmentManager fm,List<Fragment> data) {
		super(fm);
		this.fm=fm;
		this.data=data;
	}

	@Override
	public Fragment getItem(int arg0) {
		return data.get(arg0);
	}

	@Override
	public int getCount() {
		return data==null ? 0:data.size();
	}
	
	public static String makeFragmentName(int viewId, int index) {
	        return "android:switcher:" + viewId + ":" + index;
	}

	public void clearCache() {
	        FragmentTransaction ft = fm.beginTransaction();
	        for (int i = 0; i < getCount(); i++){
	            String name = makeFragmentName(viewId, i);
	            Fragment fragment = fm.findFragmentByTag(name);
	            if (fragment != null){
	                ft.remove(fragment);
	            }
	        }
	        ft.commit();
	}
}
