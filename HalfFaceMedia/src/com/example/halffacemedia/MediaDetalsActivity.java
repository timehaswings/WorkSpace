package com.example.halffacemedia;


import com.example.adapter.LitteMediaPlayer2;
import com.example.utils.MediaDeal;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class MediaDetalsActivity extends Activity {
	
	private RelativeLayout statPlay,showMedia;
	private LitteMediaPlayer2 mPlayer;
	private SeekBar bar;
	private TextView startTime,totalTime;
	private Handler mHandler;
	private ImageView fullScreen;
	private int ctime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFormat(PixelFormat.TRANSLUCENT);
		setContentView(R.layout.activity_media_detals);
		
		initView();
	}
	
	/**
	 * 初始化视图
	 */
	public void initView(){
		mHandler=new Handler();
		statPlay=(RelativeLayout) findViewById(R.id.media_detals_button);
		showMedia=(RelativeLayout) findViewById(R.id.media_detals_media);
		mPlayer=(LitteMediaPlayer2) findViewById(R.id.media_litte);
		bar=(SeekBar) findViewById(R.id.media_litte_progress);
		bar.setMax(100);
		startTime=(TextView) findViewById(R.id.litte_start_time);
		totalTime=(TextView) findViewById(R.id.litte_total_time);
		fullScreen=(ImageView) findViewById(R.id.litte_full_screen);
		statPlay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showMedia.setVisibility(View.VISIBLE);
				statPlay.setVisibility(View.GONE);
				fullScreen.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent=new Intent();
						intent.setClass(MediaDetalsActivity.this, MediaPlayerActivity.class);
						intent.putExtra("fileName", "有形的翅膀");
						intent.putExtra("filePath", LitteMediaPlayer2.FILE_NAME);
						intent.putExtra("progress", ctime);
						startActivity(intent);
					}
				});
				mHandler.post(runnable);
			}
		});
	}
	
	
	private Runnable runnable=new Runnable() {
		@Override
		public void run() {
			if(mPlayer.mediaIsPlaying()){
				if(totalTime.getText().toString().contains("--")){
					totalTime.setText(MediaDeal.getInstance().formatTime(mPlayer.getMediaDuration()));
				}
				ctime=mPlayer.getMediaProgress();
				startTime.setText(MediaDeal.getInstance().formatTime(ctime));
				bar.setProgress((ctime*100/mPlayer.getMediaDuration()));
			}
			mHandler.postDelayed(runnable,1000);
		}
	};
	
	@Override
	protected void onResume() {
		super.onResume();
		if(statPlay.getVisibility() == View.GONE)
			mHandler.post(runnable);
	};
	
	
	@Override
	protected void onStop() {
		super.onStop();
		if(mHandler != null)
			mHandler.removeCallbacks(runnable);
	}
}
