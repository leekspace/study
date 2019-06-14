package com.leekli.java.jdk.utils.serviceLoader;

import java.util.ServiceLoader;

public class ServiceLoaderTest {
	public static void main(String[] args) throws ClassNotFoundException {
		ServiceLoader<LogService> loader = ServiceLoader.load(LogService.class);
		System.out.println(loader);
	      for (LogService service : loader) {
	         System.out.println(service.getClass());
	         service.log("xxxxxxx");
	      }
	      
	      System.out.println(ServiceLoaderTest.class.getClassLoader());
	        System.out.println(String.class.getClassLoader());
	     
	}
}
