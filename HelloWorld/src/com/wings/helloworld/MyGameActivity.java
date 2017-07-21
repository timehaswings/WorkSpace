package com.wings.helloworld;

import com.wings.myrect.MyData;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MyGameActivity extends Activity {
	
	private Button down,up,left,right,start;
	private TextView show;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_game);
		
		down=(Button) findViewById(R.id.button_down);
		up=(Button) findViewById(R.id.button_up);
		left=(Button) findViewById(R.id.button_left);
		right=(Button) findViewById(R.id.button_right);
		start=(Button) findViewById(R.id.button_start);
		show=(TextView) findViewById(R.id.text_show);
		
		MyData.show=show;
		MyData.start=start;
		
		down.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MyData.moveDown();
			}
		});
		
		up.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MyData.changeDirection();
			}
		});
		
		left.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MyData.moveLeft();
			}
		});
		
		right.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MyData.moveRight();
			}
		});
		
		start.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(!start.getText().equals("暂停")){//点击开始
					MyData.isRun=true;
					start.setText("暂停");
					show.setText("当前得分：\n"+0);
				}else{//点击暂停
					MyData.isRun=false;
					start.setText("开始");
				}
			}
		});
	}
	
}
