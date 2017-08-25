package com.example.halffacemedia;

import com.example.adapter.LargeMediaPlayer;
import com.example.utils.MediaDeal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.SeekBar;
import android.widget.TextView;

public class MediaPlayerActivity extends Activity {

	private FrameLayout frame;
	private LargeMediaPlayer player;
	private TextView text1,start,total;
//	private LinearLayout linear;
	private SeekBar bar;
	private static Handler handler;
	private int totalTime,currentTime;
	private int progress;
	private boolean isSet;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_media_player);
		initView();
		
		handler.post(runnable);
	}
	
	private void initView(){
		frame=(FrameLayout) findViewById(R.id.player_frame);
		text1=(TextView) findViewById(R.id.palyer_file_name);
//		linear=(LinearLayout) findViewById(R.id.player_linear);
		bar=(SeekBar) findViewById(R.id.player_bar);
		start=(TextView) findViewById(R.id.player_start);
		total=(TextView) findViewById(R.id.player_total);
		
		handler=new Handler();
		isSet=true;
		Intent intent=getIntent();
		String fileName=intent.getStringExtra("fileName");
		String filePath=intent.getStringExtra("filePath");
		progress=intent.getIntExtra("progress", 0);
		text1.setText(fileName);
		
		FrameLayout.LayoutParams params=new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		player=new LargeMediaPlayer(this, filePath);
		player.setLayoutParams(params);
		frame.addView(player);
		
		
	}
	
	private Runnable runnable=new Runnable() {
		@Override
		public void run() {
			if(player.getIsPlaying()){
				if(total.getText().toString().contains("--")){
					totalTime=player.getMediaTotalProgress();
					total.setText(MediaDeal.getInstance().formatTime(totalTime));
				}
				if(isSet){
					player.setMideaProgress(progress);
					isSet=false;
				}
				currentTime=player.getProgress();
				bar.setProgress(currentTime*100/totalTime);
				start.setText(MediaDeal.getInstance().formatTime(currentTime));
			}
			handler.postDelayed(runnable, 1000);
		}
	};
	
	
	protected void onStop() {
		super.onStop();
		if(handler != null)
			handler.removeCallbacks(runnable);
		isSet=true;
	};
}
