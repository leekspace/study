package com.leekli.javase.JUC;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShutdownNowTest implements Runnable{
	
	
	public static void main(String[] args) {
		ExecutorService es = Executors.newCachedThreadPool();
		es.submit(new ShutdownNowTest());
		es.submit(new ShutdownNowTest());
		es.submit(new ShutdownNowTest());
		es.submit(new ShutdownNowTest());
		Thread t = new Thread(new ShutdownNowTest());
		t.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		es.shutdownNow();//shutdownNow 会向线程发送中断，程序如果不处理，则继续运行
		es.shutdown();
		t.interrupt();//中断
		
		System.out.println("end-----");
		
		
		
	}

	@Override
	public void run() {
		while(true){
			System.out.println(Thread.currentThread().getName() + " sleep 1s");
			if(Thread.interrupted()){//选择是否处理中断
				System.out.println("处理中断,清除中断状态 ");
				break;
			}
		}
	}
}
