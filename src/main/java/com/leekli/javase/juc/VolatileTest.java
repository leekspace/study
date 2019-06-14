package com.leekli.javase.juc;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class VolatileTest implements Runnable{
	private int index;
	private static volatile int flag = 0;
	private static AtomicInteger  atominFlag = new AtomicInteger(0);


	public   VolatileTest(int index){
		this.index = index;
	}
	
	public static void main(String[] args) {
		
		ExecutorService es = Executors.newFixedThreadPool(80);
		for(int i=1;i<=100;i++){
			System.out.println(i);
			es.submit(new VolatileTest(i));
		}
	}
	@Override
	public void run() {
		Random random = new Random();
		try {
			int sleep = Math.abs(random.nextInt() % 1000);
			System.out.println(sleep);
			Thread.sleep(sleep);
		} catch ( Exception e) {
			e.printStackTrace();	
		}
		flag++;//这种基于自身值的自增的用法是错误的。有可能2个线程同时拿到相同的值自增
		atominFlag.getAndIncrement();//使用正常，原子性
		System.out.println(this.getClass().getName() + index + ":" + flag + " " + atominFlag.get());
	}
	
 
	
	
}
