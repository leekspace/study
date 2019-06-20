package com.leekli.javase.JUC;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {

	public static void main(String[] args) {

		//���3���̵߳���await��Ȼ��һ��ִ��
	    CyclicBarrier cyclicBarrier = new CyclicBarrier(3,new TourGuideTask());

        Executor executor = Executors.newFixedThreadPool(3);
        //�Ǹ�����ƣ���������
        executor.execute(new TravelTask(cyclicBarrier,"����",5));
        executor.execute(new TravelTask(cyclicBarrier,"����",3));
        executor.execute(new TravelTask(cyclicBarrier,"���",1));
        
	}

	public static class TravelTask implements Runnable {
		private CyclicBarrier cyclicBarrier;
		private String name;
		private int arriveTime;// �ϵ���ʱ��

		public TravelTask(CyclicBarrier cyclicBarrier, String name, int arriveTime) {
			this.cyclicBarrier = cyclicBarrier;
			this.name = name;
			this.arriveTime = arriveTime;
		}

		@Override
		public void run() {
			 try {
		            //ģ��ﵽ��Ҫ����ʱ��
		            Thread.sleep(arriveTime * 1000);
		            System.out.println(name +"���Ｏ�ϵ�");
		            cyclicBarrier.await();
		            //ͬʱִ�е��߼�
		            System.out.println(name +"��ʼ����������");
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		}

	}
	
	public static class TourGuideTask implements Runnable{

	    @Override
	    public void run() {
	        System.out.println("****���ηַ�����ǩ֤****");
	        try {
	            //ģ�ⷢ����ǩ֤��Ҫ2��
	            Thread.sleep(2000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	}

}
