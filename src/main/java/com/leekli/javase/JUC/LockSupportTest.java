package com.leekli.javase.JUC;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest extends Thread{
	FIFOMutex f;
	LockSupportTest(FIFOMutex f){
		this.f = f;
	}
	
	@Override
	public void run() {
		
		try {
			
			f.lock();
			System.out.println(Thread.currentThread().getName() + " lock");	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(Thread.currentThread().getName() + " unlock");
			f.unlock();	
		}
		
		
	}

	public static void main(String[] args) {
		FIFOMutex f = new FIFOMutex();
		new LockSupportTest(f).start();
		new LockSupportTest(f).start();
		new LockSupportTest(f).start();
		new LockSupportTest(f).start();
		new LockSupportTest(f).start();
		new LockSupportTest(f).start();
		
	}
	
	static class FIFOMutex {
		   private final AtomicBoolean locked = new AtomicBoolean(false);
		   private final Queue<Thread> waiters   = new ConcurrentLinkedQueue<Thread>();

		   public void lock() {
		     boolean wasInterrupted = false;
		     Thread current = Thread.currentThread();
		     waiters.add(current);
		     Random r = new Random();
		     try {
				Thread.sleep(Math.abs(r.nextInt()%1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     
		     // Block while not first in queue or cannot acquire lock
		     //不是当前线程，则park
		     //或设置失败，则park
		     //保证队列的下一个线程能够设置locked 为true
		     //只要不是当前线程，就断了，直接进入 pack,没有机会cas,当前线程才有机会cas
		     //
		     System.out.println(current + "isCurrent:"+(waiters.peek() == current) );
		     while (waiters.peek() != current || !locked.compareAndSet(false, true)) {
		       System.out.println(locked.get());
		       LockSupport.park(this);
		       if (Thread.interrupted()) // ignore interrupts while waiting
		         wasInterrupted = true;
		     }
		     
		     waiters.remove();
		     if (wasInterrupted)          // reassert interrupt status on exit
		       current.interrupt();
		   }

		   public void unlock() {
		     locked.set(false);
		     LockSupport.unpark(waiters.peek());//将队列的下一个出队线程unpark
		   }
		 }
}
