package com.example.halffacemedia;

import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {
	
	private Fragment fragmentHot,fragmentFree,fragmentMember,fragmentPerson;
	private RadioGroup radioGroup;
	private FragmentManager manager;
	private Fragment mContent;
	private TextView text;
	private LinearLayout linear;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		fragmentHot=new FragmentHot();
		fragmentFree=new FragmentFree();
		fragmentMember=new FragmentMember();
		fragmentPerson=new FragmentPerson();
		radioGroup=(RadioGroup) findViewById(R.id.main_rbGroup);
		text=(TextView) findViewById(R.id.main_text);
		linear=(LinearLayout) findViewById(R.id.main_linear);
		
		manager=getSupportFragmentManager();
		manager.beginTransaction().add(R.id.main_frame, fragmentHot,FragmentHot.TAG).commit();
		mContent=fragmentHot;
		
		//控件事件监听
		radioGroup.setOnCheckedChangeListener(listener);
	}
	
	
	/**
	 * RadioGroup监听
	 */
	private RadioGroup.OnCheckedChangeListener listener=new RadioGroup.OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			switch (checkedId) {
			case R.id.main_rbHot:
				switchContent(fragmentHot);
				text.setText("Hot Zone");
				linear.setVisibility(View.VISIBLE);
				break;
			case R.id.main_rbFree:
				switchContent(fragmentFree);
				text.setText("Free Zone");
				linear.setVisibility(View.VISIBLE);
				break;
			case R.id.main_rbMember:
				switchContent(fragmentMember);
				text.setText("Member Zone");
				linear.setVisibility(View.VISIBLE);
				break;
			case R.id.main_rbPerson:
				switchContent(fragmentPerson);
				text.setText("Personal Zone");
				linear.setVisibility(View.GONE);
				break;
			default:
				break;
			}
		}
	};
	
	
	/** 
     * fragment修改显示的内容 不会重新加载 * 
     */  
    private void switchContent(Fragment to) {  
        if (mContent != to) {  
            FragmentTransaction transaction = manager.beginTransaction();  
            if (!to.isAdded())// 先判断是否被add过  
                transaction.hide(mContent).add(R.id.main_frame, to).commit(); // 隐藏当前的fragment，add下一个到Activity中  
            else  
                transaction.hide(mContent).show(to).commit(); // 隐藏当前的fragment，显示下一个  
            mContent = to;  
        }
    } 
    
}
