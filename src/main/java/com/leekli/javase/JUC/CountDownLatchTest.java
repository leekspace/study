package com.leekli.javase.JUC;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest extends Thread{

	CountDownLatch cdl;

	
	public CountDownLatchTest(CountDownLatch cdl) {
		this.cdl = cdl;
	}


	@Override
	public void run() {
		try {
			System.out.println(this.getClass().getName() +" run..");
			cdl.countDown();
		} catch (Exception e) {
			
		}
		
	}
	
	public static void main(String[] args) {
		CountDownLatch cdl = new   CountDownLatch(5);
		
		CountDownLatchTest c1 = new CountDownLatchTest(cdl);
		CountDownLatchTest c2 = new CountDownLatchTest(cdl);
		CountDownLatchTest c3 = new CountDownLatchTest(cdl);
		CountDownLatchTest c4 = new CountDownLatchTest(cdl);
		CountDownLatchTest c5 = new CountDownLatchTest(cdl);
		c1.start();
		c2.start();
		c3.start();
		c4.start();
		c5.start();
		try {
			cdl.await();//等待5个线程都countDown 后，才继续执行
			System.out.println("end");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
