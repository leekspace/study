package com.leekli.javase.JUC;

public class ThreadTest extends Thread{
	ThreadTest(ThreadGroup tg){
		super(tg,tg.getName());
	}
	ThreadTest(){
	
	}
	@Override
	public void run() {
		for(;;){
			
			System.out.print(".");
			if( Thread.interrupted()){
				System.out.println();
				System.out.println("interrupted");
				return;
			}
		}
		
		
	}

	public static void main(String[] args) throws InterruptedException {

		
		
		
		ThreadGroup tg = new ThreadGroup("parent");
		
		ThreadTest t = new ThreadTest(tg);
		ThreadTest t2 = new ThreadTest(tg);
		t.start();
		t2.start();
		
		ThreadTest t3 = new ThreadTest();
		ThreadTest t4 = new ThreadTest();
		System.out.println(t.getThreadGroup().getName());
		System.out.println(t2.getThreadGroup().getName());
		System.out.println(t3.getThreadGroup().getName());
		System.out.println(t4.getThreadGroup().getName());
		System.out.println("sleep 1s");
		Thread.sleep(1000);
		//tg.list();
		
		//tg.interrupt();
		//Thread.sleep(1000);
		System.out.println(t.getState() + " "+ t2.getState());
		
		
		
		t.checkAccess();//检查权限 
		
		t.suspend();//挂起，已经不推荐使用，容易导致死锁
		int n = t.countStackFrames();
		System.out.println(n);
		t.resume();//恢复挂起的线程
		
//		t.destroy();//销毁线程，已弃用，从未实现
		
//		t.getContextClassLoader();
		
//		t.getId();
//		t.getName();
		
		t.getPriority();//线程优先级,优先级为1-10
		t.setPriority(1);
		
		t.getStackTrace();
		
		t.getState(); //线程状态 
		
		t.getThreadGroup();
//		t.getUncaughtExceptionHandler();
//		t.interrupt();
//		t.isAlive();
//		t.isDaemon();
//		t.isInterrupted();
		

		
		
	}
}
