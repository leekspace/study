package com.leekli.google.guava.baseutilities;

import java.util.Optional;

/**
 * 
 * Guava用Optional<T>表示可能为null的T类型引用
 * @author media-liwei
 *
 * 如果值可能为NULL，则使用Optional类来包装。
 */
public class NullNonUse {
	
	public static void main(String[] args) {
		Optional<Integer> possible = Optional.of(5);
		boolean bool = possible.isPresent(); // returns true
		Integer i = possible.get(); // returns 5
		
		
		Optional.ofNullable(null);		//允许为null
		//Optional.of(null);				//NullPointerException 快速异常
		Optional<Integer> o = Optional.empty();
		System.out.println(o.orElse(21));
	}
}
