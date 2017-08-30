package com.wings.halfface_beta2;

import java.util.ArrayList;
import java.util.List;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainPagerAdapter extends FragmentPagerAdapter{
	
	private List<Fragment> data;
	
	public MainPagerAdapter(FragmentManager fm) {
		super(fm);
		data=new ArrayList<Fragment>();
		data.add(new FragmentHot());
		data.add(new FragmentFree());
		data.add(new FragmentMember());
		data.add(new FragmentPerson());
	}
	
	public MainPagerAdapter(FragmentManager fm,List<Fragment> data) {
		super(fm);
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
	
}
