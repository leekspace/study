package com.leekli.javase.JUC;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.FutureTask;

/**
 * ���ConcurrentHashMap����putIfAbsent  ��ֻ֤����һ��
 * ��ʹ��synchronized �����ܷ���
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
			if( null == obj){//put�ɹ�,��Ҫ����run���һ������
				obj = connectionPool.get(key);
				obj.run();
			}
			//�Ѿ��������� �£�ֱ��get����
			return obj.get();
			
		}
	}
	

	private Connection createConnection() {
		return new Connection();
	}
	public static class Connection{
		
	}
}
