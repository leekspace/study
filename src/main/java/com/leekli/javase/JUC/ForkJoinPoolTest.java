package com.leekli.javase.JUC;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ForkJoinPoolTest {
	public static void main(String[] args) {
		
		ConcurrentLinkedQueue q = new ConcurrentLinkedQueue<>();
		ArrayList list = new ArrayList();
		Collection<String> l = Collections.checkedCollection(list, String.class);
		
		
	}
}
