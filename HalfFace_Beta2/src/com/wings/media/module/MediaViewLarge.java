/*
 * Copyright (C) 2013 yixia.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wings.media.module;

import com.wings.halfface_beta2.BaseActivity;
import com.wings.halfface_beta2.R;
import com.wings.utils.ImageLoader;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnBufferingUpdateListener;
import io.vov.vitamio.MediaPlayer.OnInfoListener;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class MediaViewLarge extends BaseActivity implements OnInfoListener, OnBufferingUpdateListener{

	// private String path = "http://172.31.84.73/res/video/v001.mp4";
	private String path = "//sdcard//v001.mp4";
	private VideoView mVideoView;
	private ProgressBar pb;//下载中
	private TextView downloadRateView, loadRateView;//下载中显示
	private RelativeLayout relativeInit,mRLcontent;//初始显示
	private ImageView mIvThumbnail,mPause;
	private FrameLayout mFlVideoGroup;
	private LinearLayout mLLcontent;
	private ViewGroup.LayoutParams mVideoParams,mRLcontentParams;
	private MediaController mController;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		// 初始化vitamio
		Vitamio.isInitialized(getApplicationContext());
		setContentView(R.layout.mediaview_large);

		//初始化视图
		initView();
		
	}
	
	// 寻找控件
	public void initView(){
		
		relativeInit = (RelativeLayout) findViewById(R.id.media2_init_view);
		mVideoView = (VideoView) findViewById(R.id.buffer_l);
		pb = (ProgressBar) findViewById(R.id.media2_probar);
		downloadRateView = (TextView) findViewById(R.id.media2_download_rate);
		loadRateView = (TextView) findViewById(R.id.media2_load_rate);
		mIvThumbnail = (ImageView) findViewById(R.id.iv_video_thumbnail_l);
		mPause=(ImageView) findViewById(R.id.iv_video_pause);
		mFlVideoGroup=(FrameLayout) findViewById(R.id.media2_frame_play);
		mLLcontent=(LinearLayout) findViewById(R.id.media2_content);
		mRLcontent=(RelativeLayout) findViewById(R.id.media2_content_play);
		
		//控件监听事件
		relativeInit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
//				relativeInit.setVisibility(View.GONE);
				mIvThumbnail.setVisibility(View.GONE);
				mPause.setVisibility(View.GONE);
				mVideoView.setVisibility(View.VISIBLE);
				initMedia();
			}
		});
		
		//获取
		mVideoParams=mVideoView.getLayoutParams();
		mRLcontentParams=mRLcontent.getLayoutParams();
		
		//
		setImage(mIvThumbnail);
	}
	
	
	// 初始化播放器
	public void initMedia() {
		
		//上来先隐藏controller
		mController=new MediaController(this,true,mFlVideoGroup);
        mController.setVisibility(View.GONE);
        
		mVideoView.setVideoURI(Uri.parse(path));
//		mVideoView.setMediaController(new MediaController(this));
		mVideoView.setMediaController(mController);
		mVideoView.requestFocus();
		mVideoView.setOnInfoListener(this);
		mVideoView.setOnBufferingUpdateListener(this);
		mVideoView.setClickable(true);
		mVideoView.setBufferSize(256 * 1024);
		
		mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
			@Override
			public void onPrepared(MediaPlayer mediaPlayer) {
				mediaPlayer.setPlaybackSpeed(1.0f);// 设置快进速率
			}
		});
	}

	//缓存速率
	@Override
	public void onBufferingUpdate(MediaPlayer mp, int percent) {
		loadRateView.setText(percent + "%");
	}
	
	//设置图片
	public void setImage(final ImageView imageView){
		ImageLoader loader=new ImageLoader();
		loader.showBitmap(this, imageView, "file://"+path);
	}
	
	//播放中状态改变
	@Override
	public boolean onInfo(MediaPlayer mp, int what, int extra) {
		switch (what) {
		case MediaPlayer.MEDIA_INFO_BUFFERING_START:// MediaPlayer暂时暂停内部播放，以缓冲更多的数据
			if (mVideoView.isPlaying()) {
				mVideoView.pause();
				pb.setVisibility(View.VISIBLE);
				downloadRateView.setText("");
				loadRateView.setText("");
				downloadRateView.setVisibility(View.VISIBLE);
				loadRateView.setVisibility(View.VISIBLE);
			}
			break;
		case MediaPlayer.MEDIA_INFO_BUFFERING_END:// 填充缓冲区后，MediaPlayer正在恢复播放。
			mVideoView.start();
			pb.setVisibility(View.GONE);
			downloadRateView.setVisibility(View.GONE);
			loadRateView.setVisibility(View.GONE);
			break;
		case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED://
			downloadRateView.setText("" + extra + "kb/s" + "  ");
			break;
		}
		return true;
	}

	//切换横竖屏时调用
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
			mLLcontent.setVisibility(View.GONE);
			LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
			mRLcontent.setLayoutParams(params);
			FrameLayout.LayoutParams params2=new FrameLayout.LayoutParams(-1, -1);
			mVideoView.setLayoutParams(params2);
	     }else {//if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
	    	mLLcontent.setVisibility(View.VISIBLE);
			mRLcontent.setLayoutParams(mRLcontentParams);
			mVideoView.setLayoutParams(mVideoParams);
	     }
	}
	
	//监听返回键
	@Override
	public void onBackPressed() {
		if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}else{
			super.onBackPressed();
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mVideoView != null) {
            //清除缓存
            mVideoView.destroyDrawingCache();
            //停止播放
            mVideoView.stopPlayback();
            mVideoView = null;
        }
	}

}
