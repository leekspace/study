package com.leekli.javase.JUC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock 替代了synchronized方法和语句的使用 Condition替代了Object监视器方法的使用
 * Condition将Object监视器方法（wait、notify 和 notifyAll）分解成截然不同的对象
 * 
 * @author media-liwei
 *
 * 子线程执行10次，主线程执行5次，交替执行 
 */
public class ConditionTest {
	public static void main(String[] args) {
		Business b = new Business();
		new Thread(()->{
			for(int i=0;i<50;i++){
				b.sub(i);
			}
		}).start();
		
		for(int i=0;i<10;i++){
			b.main(i);
		}
	}

	static class Business {
		Lock lock = new ReentrantLock();
		Condition condition = lock.newCondition();
		// Condition是在具体的lock之上的
		private Boolean bShouldSub = true;

		public void sub(int i) {
			lock.lock();
			try {
				while (!bShouldSub) {
					try {
						condition.await();//有了这个condition，就可以实现，在某种状态下暂停，通知其它线程继续执行
						// 用condition来调用await方法
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				for (int j = 1; j <= 10; j++) {
					System.out.println("sub thread sequence of " + j + ", loop of " + i);
				}
				bShouldSub = false;
				condition.signal();	// 用condition来发出唤醒信号，唤醒某一个
				/**
				 * 第一次，bShouldSub为false ,所以不await
				 * 直接执行for循环，结束后将bShouldSub设为true,唤醒等待中的一个线程
				 * 因为bShouldSub为true，进入await
				 */
			} finally {
				lock.unlock();
			}
		}

		public void main(int i) {
			lock.lock();
			try {
				while (bShouldSub) {
					try {
						condition.await();
						// 用condition来调用await方法
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				for (int j = 1; j <= 5; j++) {
					System.out.println("main thread sequence of " + j + ", loop of " + i);
				}
				bShouldSub = true;
				condition.signal(); // 用condition来发出唤醒信号么，唤醒某一个
			} finally {
				lock.unlock();
			}
		}
	}
}
