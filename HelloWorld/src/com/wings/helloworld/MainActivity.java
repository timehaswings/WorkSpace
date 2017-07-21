package com.wings.helloworld;

import com.wings.rect.GameView;
import com.wings.rect.ShapView;
import com.wings.view.BoxView;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
		setContentView(new ShapView(this));
	}
}
