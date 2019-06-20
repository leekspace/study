package com.leekli.javase.JUC;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.FutureTask;

/**
 * 结合ConcurrentHashMap：：putIfAbsent  保证只运行一次
 * 不使用synchronized 低性能访问
 * @author media-liwei
 *
 */
public class FutureTaskTest2  {
	private ConcurrentHashMap<String,FutureTask<Connection>>connectionPool = new ConcurrentHashMap<String, FutureTask<Connection>>();
	
	
	public Connection getConnection(String key) throws Exception{
		FutureTask<Connection>connectionTask=connectionPool.get(key);
		if ( null != connectionTask ){
			return connectionTask.get();
		}else{
			FutureTask<Connection> ft = new FutureTask<>(() ->createConnection());
			FutureTask<Connection> obj = connectionPool.putIfAbsent(key, ft);
			if( null == obj){//put成功,需要调用run完成一次运行
				obj = connectionPool.get(key);
				obj.run();
			}
			//已经放入的情况 下，直接get返回
			return obj.get();
			
		}
	}
	

	private Connection createConnection() {
		return new Connection();
	}
	public static class Connection{
		
	}
}
