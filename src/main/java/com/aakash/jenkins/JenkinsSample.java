package com.aakash.jenkins;

public class JenkinsSample {
	int a =0;
	int b = 1;
	
	public static int addition(int a, int b) {
		return a+b;
	}
	
	public static int multiply(int a, int b) {
		return a*b;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int total =addition(2,5);
		int total1 =multiply(3,6);
		System.out.println(total);
		System.out.println(total1);
	}
}
