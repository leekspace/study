package com.leekli.java.jdk.utils.serviceLoader;

public class LogServiceImpl2  implements LogService{

	@Override
	public void log(String msg) {
		System.out.println("LogServiceImpl2:" + msg);
	}

}
