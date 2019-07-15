package com.leekli.javase.JUC;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁
 * @author media-liwei
 *
 */
public class ReentrantLockTest implements Runnable{
	static ReentrantLock rl = new ReentrantLock();
	public static void main(String[] args) {
		new Thread(new ReentrantLockTest()).start();
		new Thread(new ReentrantLockTest()).start();
		new Thread(new ReentrantLockTest()).start();
		
	}

 
	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		try {
			System.out.println(name + "请求锁");
			rl.lock();
			Thread.sleep(1000);
			System.out.println(name + "queueLength:"+rl.getQueueLength());
			System.out.println(name + "得到lock");
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			System.out.println(name + "释放lock");
			rl.unlock();
		}
	}
}
