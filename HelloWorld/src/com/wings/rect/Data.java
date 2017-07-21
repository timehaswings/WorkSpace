package com.wings.rect;

import java.util.Random;

import android.content.SharedPreferences;



public class Data {
	final static int of_Width = 24;// 游戏运行的行数
	final static int of_Height = 10;// 游戏运行的列数
	public static int[][] m_screen = new int[of_Width][of_Height];// 屏幕数组
	public static int of_x = 0,of_y = 0;
	public static int k = 0, a = 0, state = 0, statenext = 0;// 随机数数和随机方块
	static long score = 0; // 分数、等级
	static long line = 0;
	Random m_Random = null;// 随机函数
	SharedPreferences m_sp = null;//用于存储最高分等
	String fileName = "Data";//存放游戏一些数据，最高分、难度、音乐、触摸屏
	Data(){
		
		clean(); // 清除游戏开始方块数组值
		score = 0;
		line = 0;
		m_Random = new Random();// 产生随机函数
		k = m_Random.nextInt(28);// 当前方块
		a = m_Random.nextInt(28);// 下一个方块
		of_x = 0;
		of_y = 3; // 出现方块的位置
	}
	public void clean() {//初始化m_screen数组
		for (int i = 0; i < of_Width; i++) {
			for (int j = 0; j < of_Height; j++) {
				m_screen[i][j] = 0;
			}
		}
	}
	public void goTurn(){//实现方块旋转和判断
		int shap = (k)/4;//判断形状
		int order = (k)%4;//判断状态
		if (order==3){
			order=0;
		}
		else{
			order=order+1;
		}
		int k_turn=shap*4+order;
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(StateFang.state[k_turn][i][j]!=0){
					if((i+of_x)>23||(j+of_y)<0||(j+of_y)>9){
						return;
					}
				    else if(m_screen[i+of_x][j+of_y]!=0){
						return;
					}
				}
			}
		}
		k=k_turn;
	}
	public void goRight(){//右移函数
		if(cangoRight()){
			of_y++;
		}
	}
	public boolean cangoRight(){
		boolean right = true;
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(StateFang.state[k][i][j]!=0){
					if((j+of_y+1)>=of_Height){
						return false;
					}
					else if(m_screen[i+of_x][j+of_y+1]!=0){
						return false;
					}
				}
			}
		}
		return right;
	}
	public void goLeft(){//左移函数
		if(cangoLeft()){
			of_y--;
		}
	}
	public boolean cangoLeft(){
		boolean left = true;
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(StateFang.state[k][i][j]!=0){
					if((j+of_y-1)<0){
						return false;
					}
					else if(m_screen[i+of_x][j+of_y-1]!=0){
						return false;
					}
				}
			}
		}
		return left;
	}
	public void goDown(){//下降函数
		if(cangoDown()){
			of_x++;
		}
		else{
			saveScream();//不能下降保存m_screem数组
			
			cleanLine();
			k = a;//加入下一个方块，使他成为当前方块
			a = m_Random.nextInt(28);//产生下一个方块
			of_x = 0;//重新设定当前方块的位置
			of_y = 3;
		}
		
	}
	public boolean cangoDown(){//判断是否下降
		boolean down = true;
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(StateFang.state[k][i][j]!=0){
					if((i+of_x+1)<of_Width){
						if(m_screen[i+of_x+1][j+of_y]!=0){
							return false;
						}
					}
					else{
						return false;
					}
				}
			}
		}
		return down;
	}
	public void saveScream(){//方块停止的时候，保存m_screen数组
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(StateFang.state[k][i][j]!=0){
//					  m_screen[i+of_x][j+of_y]=StateFang.color[k/4];
				}
			}
		}
	}
	public void cleanLine(){//当一行被占满时删除这一行
		for(int i=of_x;i<of_Width;i++){
			if(isFullLine(i)){
				for(int j=i;j>3;j--){
					for(int k=0;k<of_Height;k++){
					    m_screen[j][k]=m_screen[j-1][k]; 
					}
				}
				line++;
//				switch(GameActivity.level){//根据等级计算分数
//					case 0:
//						score=line*10;
//						break;
//					case 1:
//						score=line*50;
//						break;
//					case 2:
//						score=line*250;
//						break;
//					default:
//						score=line*10000;
//				}
			}
		}
	}
	public boolean isFullLine(int i){//判断一行是否被占满
		for(int j=0;j<of_Height;j++){
			if(m_screen[i][j]==0)
				return false;
		}
		return true;
	}
	public boolean isGameOver(){//判断游戏是否结束
		for(int i=0;i<of_Height;i++){
			if(m_screen[3][i]!=0){
				return true;
			}
		}
		return false;
	}
}
