package com.wings.myrect;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

import android.util.AttributeSet;
import android.view.View;


public class MyGameView extends View {
	
	private float xdis=0;
	private float ydis=0;
	private Paint m_Paint;

	public MyGameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		m_Paint=new Paint(); // 新建画笔 
	}
	
	public MyGameView(Context context){
		super(context);
		m_Paint=new Paint(); // 新建画笔 
	}
	


	public void onDraw(Canvas canvas) {// 画图函数，主线程中实现不断刷新，此函数响应刷新
		super.onDraw(canvas);
		xdis = getWidth() / MyData.of_Width;
		ydis = getHeight() / MyData.of_Height;
		
		paintLine(canvas); //绘制线条
		if(MyData.isRun)
			paintShape(canvas);
		else
			paintFixed(canvas);
		invalidate();
	}

	public void paintLine(Canvas canvas) {
		float width = getWidth();
		float height = getHeight();
		m_Paint.setColor(Color.WHITE);
		canvas.drawRect(0.0f, 0.0f, width, height, m_Paint);//画白底背景
		m_Paint.setColor(Color.LTGRAY);
		for(int i = 0;i < 21;i++){//画横线
			canvas.drawLine(0.0f, i*ydis, width, i*ydis, m_Paint);
		}
		for(int j = 0;j < 11;j++){//画竖线
			canvas.drawLine(j*xdis, 0.0f, j*xdis, height, m_Paint);
		}
	}
	
	public void paintShape(Canvas canvas){
		m_Paint.setStyle(Style.FILL);
		m_Paint.setColor(Color.YELLOW);
		
		paintFixed(canvas);
		
		MyData.move_y++;
		if(MyData.move_y%MyData.game_Speed==0)
			MyData.move_s++;
		
		for(int i=0;i<4;i++){//画下一个方块
			for(int j=0;j<4;j++){
				if(MyStateFang.state[MyData.rN][i][j]!=0){
					canvas.drawRect((j+MyData.move_x)*xdis+2, (i+MyData.move_s)*ydis+2, (j+MyData.move_x+1)*xdis-2, (i+MyData.move_s+1)*ydis-2,m_Paint);
				}
			}
		}
		
		if(!MyData.isDown())
			MyData.endMove();
	}
	
	
	public void paintFixed(Canvas canvas){//画固定的方块
		
		m_Paint.setStyle(Style.FILL);
		m_Paint.setColor(Color.YELLOW);
		MyData.isRemoveRect();
		for(int i=0;i<MyData.of_Width;i++){
			for(int j=0;j<MyData.of_Height;j++){
				if(MyData.m_screen[i][j]!=0){
					canvas.drawRect((i)*xdis+2, (j)*ydis+2, (i+1)*xdis-2, (j+1)*ydis-2, m_Paint);
				}
			}
		}
		
	}
}
