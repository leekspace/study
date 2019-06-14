package com.leekli.java.jdk.utils.serviceLoader;

public class LogServiceImpl  implements LogService{

 
	@Override
	public void log(String msg) {
		System.out.println("LogServiceImpl:" + msg);
		
	}

}
