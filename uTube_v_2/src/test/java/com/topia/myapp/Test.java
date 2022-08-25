package com.topia.myapp;

public class Test {
	public static void main(String[] args) {
		String url = "https://youtu.be/rS5IOVV-VK8";
		String[] arr = url.trim().split("/");
		System.out.println(arr[3]);
	}
}
