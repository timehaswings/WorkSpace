package com.wings.halfface_beta2;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends BaseActivity{
	
	private Fragment fragmentHot,fragmentFree,fragmentMember,fragmentPerson;
	private ViewPager pager;
	private RadioGroup group;
	private RadioButton[] radioButtons;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}
	
	//初始化视图
	private void initView(){
		//寻找控件
		pager=(ViewPager) findViewById(R.id.main_pager);
		group=(RadioGroup) findViewById(R.id.main_rbGroup);
		radioButtons=new RadioButton[]{
				(RadioButton) findViewById(R.id.main_rbHot),
				(RadioButton) findViewById(R.id.main_rbFree),
				(RadioButton) findViewById(R.id.main_rbMember),
				(RadioButton) findViewById(R.id.main_rbPerson)
		};
		//初始化fragement
		fragmentHot=new FragmentHot();
		fragmentFree=new FragmentFree();
		fragmentMember=new FragmentMember();
		fragmentPerson=new FragmentPerson();
		List<Fragment> data=new ArrayList<Fragment>();
		data.add(fragmentHot);
		data.add(fragmentFree);
		data.add(fragmentMember);
		data.add(fragmentPerson);
		pager.setAdapter(new MainPagerAdapter(getSupportFragmentManager(),data));
		//设置控件监听
		group.setOnCheckedChangeListener(rdGroupListener);
		pager.setOnPageChangeListener(pagerListener);
	}
	
	/**
	 * RadioGroup监听
	 */
	private RadioGroup.OnCheckedChangeListener rdGroupListener=new RadioGroup.OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			switch (checkedId) {
			case R.id.main_rbHot:
				pager.setCurrentItem(0);
				break;
			case R.id.main_rbFree:
				pager.setCurrentItem(1);
				break;
			case R.id.main_rbMember:
				pager.setCurrentItem(2);
				break;
			case R.id.main_rbPerson:
				pager.setCurrentItem(3);
				break;
			default:
				break;
			}
		}
	};
	
	
	private ViewPager.OnPageChangeListener pagerListener=new ViewPager.OnPageChangeListener() {
		@Override
		public void onPageSelected(int arg0) {
			for(int i=0;i<radioButtons.length;i++){
				if(arg0==i)
					radioButtons[i].setChecked(true);
				else 
					radioButtons[i].setChecked(false);
			}
		}
		
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {}
		
		@Override
		public void onPageScrollStateChanged(int arg0) {}
	};
}
