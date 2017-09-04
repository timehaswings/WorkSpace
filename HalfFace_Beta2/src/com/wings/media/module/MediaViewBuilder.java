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

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.ThumbnailUtils;
import io.vov.vitamio.MediaPlayer.OnBufferingUpdateListener;
import io.vov.vitamio.MediaPlayer.OnInfoListener;
import io.vov.vitamio.provider.MediaStore;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class MediaViewBuilder extends BaseActivity implements OnInfoListener, OnBufferingUpdateListener {

//  private String path = "http://172.31.84.73/res/video/v001.mp4";
  private String path = "//sdcard//v001.mp4";
  private VideoView mVideoView;
  private ProgressBar pb;
  private TextView downloadRateView, loadRateView;
  private RelativeLayout relativeInit,relativePlay;
  private ImageView mIvThumbnail;

  @Override
  public void onCreate(Bundle icicle) {
    super.onCreate(icicle);
    //初始化vitamio
    Vitamio.isInitialized(getApplicationContext());
    setContentView(R.layout.mediaview_large);
    
    //寻找控件
    mVideoView = (VideoView) findViewById(R.id.buffer);
    pb = (ProgressBar) findViewById(R.id.probar);
    downloadRateView = (TextView) findViewById(R.id.download_rate);
    loadRateView = (TextView) findViewById(R.id.load_rate);
    relativeInit=(RelativeLayout) findViewById(R.id.large_init_view);
    relativePlay=(RelativeLayout) findViewById(R.id.large_play_view);
    mIvThumbnail=(ImageView) findViewById(R.id.large_img_view);
    
    //显示正在加载
//    relativeInit.setVisibility(View.VISIBLE);
//    relativePlay.setVisibility(View.GONE);
    relativePlay.setVisibility(View.GONE);
    
    //初始化播放器
    initMedia();
  }
  
  //初始化播放器
	public void initMedia(){
		mVideoView.setVideoURI(Uri.parse(path));
		mVideoView.setMediaController(new MediaController(this));
		mVideoView.requestFocus();
		mVideoView.setOnInfoListener(this);
		mVideoView.setOnBufferingUpdateListener(this);
		mVideoView.setClickable(true);
		mVideoView.setBufferSize(256 * 1024);
		mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
			@Override
			public void onPrepared(MediaPlayer mediaPlayer) {
				mediaPlayer.setPlaybackSpeed(1.0f);//设置快进速率
			}
		});
		setData();
	  }
  
  
  @Override
  public boolean onInfo(MediaPlayer mp, int what, int extra) {
//	relativeInit.setVisibility(View.GONE);
//	relativePlay.setVisibility(View.VISIBLE);
    switch (what) {
    case MediaPlayer.MEDIA_INFO_BUFFERING_START://MediaPlayer暂时暂停内部播放，以缓冲更多的数据
	      if (mVideoView.isPlaying()) {
	        mVideoView.pause();
	        pb.setVisibility(View.VISIBLE);
	        downloadRateView.setText("");
	        loadRateView.setText("");
	        downloadRateView.setVisibility(View.VISIBLE);
	        loadRateView.setVisibility(View.VISIBLE);
	      }
	      break;
    case MediaPlayer.MEDIA_INFO_BUFFERING_END://填充缓冲区后，MediaPlayer正在恢复播放。
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

  @Override
  public void onBufferingUpdate(MediaPlayer mp, int percent) {
    loadRateView.setText(percent + "%");
  }
  
  public void setData(){
	  new Thread() {
          @Override
          public void run() {
              //设置缩略图,Vitamio提供的工具类。
              final Bitmap videoThumbnail = ThumbnailUtils.createVideoThumbnail(
                      MediaViewBuilder.this, path
                      , MediaStore.Video.Thumbnails.MINI_KIND);
              if (videoThumbnail != null) {
                  mIvThumbnail.post(new Runnable() {
                      @Override
                      public void run() {
                          mIvThumbnail.setImageBitmap(videoThumbnail);
                      }
                  });
              }
          }
      }.start();
      
      mIvThumbnail.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			relativePlay.setVisibility(View.VISIBLE);
			mIvThumbnail.setVisibility(View.GONE);
		}
	});
  }

}
