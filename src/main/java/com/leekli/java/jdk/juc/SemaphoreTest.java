package com.leekli.java.jdk.juc;

import java.util.concurrent.Semaphore;

public class SemaphoreTest extends Thread{
	Semaphore sh;
	
	public  SemaphoreTest(Semaphore sh){
		this.sh = sh;
	}
	
	@Override
	public void run() {
		try {
			sh.acquire();
			System.out.println(sh.availablePermits() + " " + this.getClass().getName() + " ����");
			sleep(3000);
		} catch (Exception e) {
			
		}finally{
			System.out.println(sh.availablePermits() + " " + this.getClass().getName() + " �ͷ�");
			sh.release();
		}
	}

	public static void main(String[] args) {
		Semaphore sh = new Semaphore(3);	
		
		SemaphoreTest st1 = new SemaphoreTest(sh);
		SemaphoreTest st2 = new SemaphoreTest(sh);
		SemaphoreTest st3 = new SemaphoreTest(sh);
		SemaphoreTest st4 = new SemaphoreTest(sh);
		SemaphoreTest st5 = new SemaphoreTest(sh);
		st1.start();
		st2.start();
		st3.start();
		st4.start();
		st5.start();
	}
}
