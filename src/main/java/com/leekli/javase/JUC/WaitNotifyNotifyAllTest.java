package com.leekli.javase.JUC;

import java.util.LinkedList;
import java.util.Queue;
/**
 * 生产者和消费者
 * @author media-liwei
 *
 */
public class WaitNotifyNotifyAllTest implements Runnable{

	Object lock;
	Queue<String> list;
	boolean isProduction;
	
	WaitNotifyNotifyAllTest(Object lock,Queue<String> list,boolean isProduction){
		this.lock = lock;
		this.list = list;
		this.isProduction = isProduction;
	}
	
	public static void main(String[] args) {
		Object lock = new Object();
		Queue<String> list = new LinkedList<>();
		new Thread(new WaitNotifyNotifyAllTest(lock, list,true)).start();
		new Thread(new WaitNotifyNotifyAllTest(lock, list,false)).start();
		
	}

	@Override
	public void run() {
		synchronized (lock) {
			
			while(isProduction){
				for(int i=0;i<10;i++){
					list.add("" + i);
				}
				try {
					System.out.println(" Production wait.....list:" + list);
					System.out.println("Production notifyAll");
					lock.notifyAll();
					System.out.println("Production wait");
					lock.wait();//释放锁
					System.out.println("Production waikup");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			while( ! isProduction){
				if( list.isEmpty()){
					System.out.println("consume notifyAll");
					lock.notifyAll();//通过其它线程运行
					try {
						System.out.println("consume wait");
						lock.wait();//释放锁
						System.out.println("consume waitup");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					continue;
				}else{
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("poll:"+list.poll());
				}
			}
			
		}
		
	}
	
}
