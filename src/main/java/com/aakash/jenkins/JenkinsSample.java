package com.aakash.jenkins;

public class JenkinsSample {
	
	
	
	public static int addition(int a, int b) {
		return a+b;
	}
	
	public static int multiply(int a, int b) {
		return a*b;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JenkinsSample js =new JenkinsSample();
		int total =js.addition(2,5);
		int total1 =js.multiply(3,6);
		System.out.println(total);
		System.out.println(total1);
	}
}
