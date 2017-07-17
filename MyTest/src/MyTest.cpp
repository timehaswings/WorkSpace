//============================================================================
// Name        : MyTest.cpp
// Author      : DavilMayCry
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
using namespace std;

//根据半径计算圆的周长和面积
void test01();


int main() {

//	cout << "!!!Hello World!!!" << endl; // prints !!!Hello World!!!

	test01();
	return 0;
}


void test01(){
	cout<<"请输入半径： ";
	int float rs;
	rs >> cin;
	cout<<endl;
	if(rs>=0.0){
		const float PI=3.141592653;
		cout<<" 圆的周长："<<2*PI*rs<<endl;
		cout<<" 圆的周长："<<PI*rs*rs<<endl;
	}else{
		cout<<"输入有误"<<endl;
	}
}
