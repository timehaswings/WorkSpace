package com.wings.myrect;

import java.util.Random;

import android.widget.Button;
import android.widget.TextView;



public class MyData {
	
	public final static int of_Width = 10;// 游戏运行的行数
	public final static int of_Height = 20;// 游戏运行的列数
	
	public static int game_Speed=25;//游戏速度
	
	public static int[][] m_screen=new int[of_Width][of_Height];//整个屏幕
	
	public static int move_s=0,move_y=0,move_x=4;//竖直偏移，水平偏移
	
	public static int rN=0;//初始化图形随机数
	
	public static boolean isRun=false;//游戏是否运行
	
	public static TextView show;//文本显示
	
	public static int fraction=0;//得分
	
	public static Button start;
	
	/**
	 * 获取随机数
	 * @return
	 */
	public static int getRandom(){
		return new Random().nextInt(28);
	}
	
	/**
	 * 下按钮
	 */
	public static void moveDown(){
		if(!(game_Speed==5))
			game_Speed-=5;
	}
	
	/**
	 * 左移
	 */
	public static void moveLeft(){
		if(!(move_x==0))
			move_x--;
	}
	
	/**
	 * 右移
	 */
	public static void moveRight(){
		if(!(move_x==of_Width-MyStateFang.stateWdith[rN]))
			move_x++;
	}
	
	/**
	 * 改变方向
	 */
	public static void changeDirection(){
		for(int i=0;i<8;i++){
			if(rN >= 4*i && rN < 4*(i+1)-1){
				rN++;
				break;
			}else if(rN==4*(i+1)-1){
				rN = 4*i;
				break;
			}
		}
		game_Speed=25;
	}
	
	/**
	 * 方块停止移动
	 */
	public static void endMove(){
		
		//保存图形
		saveShape();
		
		//重置属性
		move_s=0;
		move_y=0;
		move_x=4;
		if(isStop())
			isRun=false;
		rN=MyData.getRandom();
		game_Speed=25;
		
	}
	
	/**
	 * 保存图形
	 */
	public static void saveShape(){
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(MyStateFang.state[rN][i][j]!=0){
					  m_screen[j+move_x][i+move_s]=1;
				}
			}
		}
	}
	
	/**
	 * 判断游戏停止
	 */
	public static boolean isStop(){
		for(int j=0;j<MyData.of_Width;j++){
			if(MyData.m_screen[j][0]==1){
				for(int i=0;i<MyData.of_Width;i++){
					for(int m=0;m<MyData.of_Height;m++){
						m_screen[i][m]=0;
					}
				}
				show.setText("游戏结束！\n您的得分为："+fraction*100);
				isRun=false;
				start.setText("开始");
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断能否下降
	 */
	public static boolean isDown(){
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(MyStateFang.state[rN][i][j]!=0){
					if(i+move_s+1>=of_Height){
						 return false;
					}
					if( m_screen[j+move_x][i+move_s+1]==1)
					  return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * 是否需要移除
	 */
	public static void isRemoveRect(){
		for(int j=of_Height-1;j>=0;j--){
			if(m_screen[0][j]==1 && m_screen[1][j]==1 && m_screen[2][j]==1 && m_screen[3][j]==1 && m_screen[4][j]==1
				&& m_screen[5][j]==1 && m_screen[6][j]==1 && m_screen[7][j]==1 && m_screen[8][j]==1 && m_screen[9][j]==1){
				for(int i=0;i<of_Width;i++){
					for(int s=j;s>0;s--){
						m_screen[i][s]=m_screen[i][s-1];
					}
				}
				fraction++;
				show.setText("当前得分：\n"+fraction*100);
			}
		}
	}
}