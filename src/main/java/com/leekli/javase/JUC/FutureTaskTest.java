package com.leekli.javase.JUC;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import com.leekli.javase.common.tools.class1.CollectionsTest.Bean;

public class FutureTaskTest  implements Callable<Bean>{

	public static void main(String[] args) {
		FutureTask<Bean> ft = new FutureTask<Bean>(new FutureTaskTest());
		
		Executors.newCachedThreadPool().submit(ft);
		try {
			Bean bean = ft.get();
			ft.isCancelled();
			ft.isDone();
			
			System.out.println(bean);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
 

	@Override
	public Bean call() throws Exception {
		
		return new Bean("a");
	}
}
