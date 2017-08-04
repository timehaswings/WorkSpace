package com.example.halffacemedia;

import java.io.File;

import com.example.adapter.LitteMediaPlayer;
import com.example.utils.HalfFaceApp;
import com.example.utils.MediaDeal;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
	
	private Fragment fragmentHot,fragmentFree,fragmentMember,fragmentPerson;
	private RadioGroup radioGroup;
	private FragmentManager manager;
	private Fragment mContent;
	private TextView text;
	private RelativeLayout linear;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initView();//初始化视图
		applyPermissions();//申请应用权限
		setAppDir();
//		saveImage();
		
		radioGroup.setOnCheckedChangeListener(listener);//控件事件监听
	}
	
	/**
	 * 初始化视图
	 */
	private void initView(){
		fragmentHot=new FragmentHot();
		fragmentFree=new FragmentFree();
		fragmentMember=new FragmentMember();
		fragmentPerson=new FragmentPerson();
		radioGroup=(RadioGroup) findViewById(R.id.main_rbGroup);
		text=(TextView) findViewById(R.id.main_text);
		linear=(RelativeLayout) findViewById(R.id.main_linear);
		
		manager=getSupportFragmentManager();
		manager.beginTransaction().add(R.id.main_frame, fragmentHot,FragmentHot.TAG).commit();
		mContent=fragmentHot;
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
    
    /**
     * 申请权限
     * @return
     */
    @TargetApi(23)
	private boolean applyPermissions(){
    	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
	    	if(checkSelfPermission( Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
	    		requestPermissions(HalfFaceApp.permissions, 1);
		        return false;
	    	}
	    }
	    return true;
    }
    
    /* (non-Javadoc)
     * @see android.app.Activity#onRequestPermissionsResult(int, java.lang.String[], int[])
     */
    @SuppressLint("NewApi")
	@Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
    	if(requestCode == 1){
    		for(int i=0;i<grantResults.length;i++){
    			if(grantResults[i] == PackageManager.PERMISSION_DENIED){
    				Toast.makeText(this, "应用未被授权将会无法运行！", Toast.LENGTH_LONG).show();;
    			}
    		}
    	}
    	super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    
    
    /**
     * 设置应用文件夹
     */
    private void setAppDir(){
    	File file=new File(Environment.getExternalStorageDirectory()+"/HalfFace");
    	if(!file.exists())
    		file.mkdirs();
    	File cache=new File(file.getAbsoluteFile()+"/Cache");
    	if(!cache.exists())
    		cache.mkdirs();
    	File data=new File(file.getAbsoluteFile()+"/Data");
    	if(!data.exists())
    		data.mkdirs();
    }
    
    
    private String saveImage(){
    	MediaDeal deal=MediaDeal.getInstance();
    	Bitmap bitmap=deal.getVideoThumb(LitteMediaPlayer.FILE_NAME);
    	String path=deal.bitmap2File(bitmap,"有形的翅膀");
    	bitmap.recycle();
    	bitmap=null;
    	return path;
    }
}
