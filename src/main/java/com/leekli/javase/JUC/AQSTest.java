package com.leekli.javase.JUC;

import java.util.Optional;

public class AQSTest {
	public static void main(String[] args) {
		Integer a = 10000;
		int b = 10000;
		System.out.println(a == b);
		
		Byte aaa = -127;
		Byte bbb = -127;
		System.out.println(aaa.equals(bbb));
		
	}
}
