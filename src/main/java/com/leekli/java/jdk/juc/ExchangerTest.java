package com.leekli.java.jdk.juc;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
/**
 * 线程运行时，数据交换 
 * @author media-liwei
 *
 */
public class ExchangerTest extends Thread {
	private Exchanger<String> exchanger;
    private String string;
    private String threadName;
    
    public ExchangerTest(Exchanger<String> exchanger, String string, String threadName) {
        this.exchanger = exchanger;
        this.string = string;
        this.threadName = threadName;
    }

	@Override
	public void run() {
		try {
            System.out.println(threadName + ": " + exchanger.exchange(string, 1, TimeUnit.SECONDS));
        } catch (Exception e) {
            e.printStackTrace();
        }

	}
 
	public static void main(String[] args) {
		Exchanger<String> exchanger = new Exchanger<>();
        ExchangerTest test1 = new ExchangerTest(exchanger, "string1", "thread-1");
        ExchangerTest test2 = new ExchangerTest(exchanger, "string2", "thread-2");
        ExchangerTest test3 = new ExchangerTest(exchanger, "string3", "thread-3");
        ExchangerTest test4 = new ExchangerTest(exchanger, "string4", "thread-4");
        test1.start();
        test2.start();
        test3.start();
        test4.start();
	}
}
