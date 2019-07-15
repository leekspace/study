package com.leekli.javase.JUC;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockTest extends Thread{

	static ReentrantReadWriteLock lock  = new ReentrantReadWriteLock();
	
	static int n = 0;
	int i;
	CountDownLatch cdl;
	
	public ReentrantReadWriteLockTest(CountDownLatch cdl,int i){
		this.i= i;
		this.cdl = cdl;
	}
	
	@Override
	public void run() {
		try {
			lock.readLock().lock();//可并发
			System.out.println(this.getClass().getName() + i+" : " + n++);
		} catch (Exception e) {

		}finally{
			cdl.countDown();
			lock.readLock().unlock();
		}
	}




	public static void main(String[] args) {
		CountDownLatch cdl = new CountDownLatch(1000);
		for(int i=0;i<1000;i++){
			new ReentrantReadWriteLockTest(cdl,i).start();
		}
		try {
			cdl.await();
			System.out.println("===:"+ReentrantReadWriteLockTest.n);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
