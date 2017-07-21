package com.wings.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class AnimateView extends View{
	
	private int radius=10,i=0;
	private int widthPixels,heightPixels;
	private Paint paint;

	public AnimateView(Context context) {
		super(context);
		paint=new Paint();
		paint.setColor(Color.GREEN);
		paint.setStyle(Paint.Style.FILL_AND_STROKE);
		widthPixels = getResources().getDisplayMetrics().widthPixels;
		heightPixels = getResources().getDisplayMetrics().heightPixels;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawCircle(i++, 0, radius++, paint);
		if(i==widthPixels)
			i=0;
		if(radius==100)
			radius=0;
		invalidate();
	}

}
