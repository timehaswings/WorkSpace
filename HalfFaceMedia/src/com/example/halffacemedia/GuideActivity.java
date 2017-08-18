package com.example.halffacemedia;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

public class GuideActivity extends Activity {

	private GuideVideoView guide;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		guide=(GuideVideoView) findViewById(R.id.guide);
		//设置播放加载路径
		guide.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.flash3973));
        //播放
		guide.start();
        //循环播放
		guide.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
            	guide.start();
            }
        });
	}
	
	@Override
	public void onBackPressed() {
	    // super.onBackPressed();   不要调用父类的方法
	    Intent intent = new Intent(Intent.ACTION_MAIN);
	    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    intent.addCategory(Intent.CATEGORY_HOME);
	    startActivity(intent);
	}
}
