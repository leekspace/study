package com.leekli.google.guava.baseutilities;

import java.util.Optional;

/**
 * 
 * Guava��Optional<T>��ʾ����Ϊnull��T��������
 * @author media-liwei
 *
 * ���ֵ����ΪNULL����ʹ��Optional������װ��
 */
public class NullNonUse {
	
	public static void main(String[] args) {
		Optional<Integer> possible = Optional.of(5);
		boolean bool = possible.isPresent(); // returns true
		Integer i = possible.get(); // returns 5
		
		
		Optional.ofNullable(null);		//����Ϊnull
		//Optional.of(null);				//NullPointerException �����쳣
		Optional<Integer> o = Optional.empty();
		System.out.println(o.orElse(21));
	}
}
