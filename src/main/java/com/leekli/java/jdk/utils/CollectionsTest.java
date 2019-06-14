package com.leekli.java.jdk.utils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.leekli.java.jdk.utils.Example.Bean;


public class CollectionsTest {
	public static void main(String[] args) {
		Collections.emptyList();
		Collections.emptyMap();
		Collections.emptySet();
		
		
		{
			List<Bean> list = Lists.newArrayList();
			Bean[] array = new Bean[]{new Bean("a"),new Bean("b")};
			Collections.addAll(list, array);
			Collections.addAll(list, new Bean("c"));//增加数据到容器
			
			list.addAll(Arrays.asList(array));
			
			System.out.println(list);
		}
		{
			Deque<Bean> deque1 = new ArrayDeque<>();
			Deque<Bean> deque2 = Lists.newLinkedList();
			Bean[] array = new Bean[]{new Bean("a"),new Bean("b")};
			Collections.addAll(deque1,array);
			deque1.addLast(new Bean("f"));
			System.out.println(deque1);
			Queue<Bean> view = Collections.asLifoQueue(deque1);//返回后进先出队列
			view.add(new Bean("d"));
			System.out.println(view);
			
			System.out.println(view.poll());
			System.out.println(view.poll());
			System.out.println(view);
		}
		{
			List<Bean> list = Lists.newArrayList();
			Bean[] array = new Bean[]{new Bean("a"),new Bean("b"),new Bean("c")};
			Collections.addAll(list, array);
			int index = Collections.binarySearch(list, new Bean("b"));//二分查找
			System.out.println(index);
			
			Collections.binarySearch(list,  new Bean("c"), (x,y) ->x.compareTo(y));
		}
		{
			List c1 = new ArrayList<>();
			c1.add(new Bean());
			c1.add(new Object());
			System.out.println(c1);
			List<String> view = Collections.checkedList(c1, String.class);
			view.add("abcd");
			System.out.println(view);
		}
		{
			List<Bean> dest = new ArrayList<>();
			List<Bean> src = new ArrayList<>();
			System.out.println(dest.size());
			System.out.println(src.size());
			src.addAll(Arrays.asList(new Bean[]{new Bean("1"),new Bean("2")}));
			dest.addAll(Arrays.asList(new Bean[]{new Bean("a"),new Bean("b"),new Bean("c")}));
			
			Collections.copy(dest, src);
			System.out.println(dest);
			
		}
		
		{
			Collection<Bean> c1 = Lists.newArrayList();
			Collection<Bean> c2 = Lists.newArrayList();
			c1.addAll(Arrays.asList(new Bean[]{new Bean("a"),new Bean("b"),new Bean("c")}));
			c2.addAll(Arrays.asList(new Bean[]{new Bean("d"),new Bean("e"),new Bean("f")}));
			boolean c3 = Collections.disjoint(c1, c2);
			System.out.println(c3);
		}
			
			
		{
			List<Bean> list =Lists.newArrayList();
			list.add(new Bean("a"));
			list.add(new Bean("b"));
			
			Collections.fill(list, new Bean("c"));
			System.out.println(list);
			
			
		}
		
		{
			Collection<Bean> list = Lists.newArrayList();
			list.add(new Bean("a"));
			list.add(new Bean("a"));
			int i = Collections.frequency(list, new Bean("a"));
			System.out.println(i);
		}
		
		{
			List<String> target = Lists.newArrayList();
			target.addAll(Arrays.asList(new String[]{"a","b"}));
			
			List<String> source = Lists.newArrayList();
			source.addAll(Arrays.asList(new String[]{"1","2","a","b","c"}));
			
			int i = Collections.indexOfSubList(source, target);
			System.out.println(i);
		}
		{
			int n = 5;
			Bean o = new Bean("a");
			List<Bean> r = Collections.nCopies(n, o);
			System.out.println(r);
			o.setName("b");
			System.out.println(r);
		}
		{
			Map<Bean,Boolean> map = Maps.newHashMap();
			map.put(new Bean("a"), false);
			map.put(new Bean("b"), false);
			map.put(new Bean("c"), true);
			Set<Bean> set = Collections.newSetFromMap(Maps.newHashMap() );
			System.out.println(set);
		}
		{
			List<String> list = Lists.newArrayList();
			list.addAll(Arrays.asList(new String[]{"a","b","c","d","e"}));
			System.out.println(list.subList(1, 4));
			Collections.rotate(list.subList(1, 4), -1);
			System.out.println(list);
		}

		{
			List<String> list = Lists.newArrayList();
			list.addAll(Arrays.asList(new String[]{"a","b","c","d","e"}));
			Collections.shuffle(list);
			System.out.println(list);
		}
		{
			Bean b =  new Bean("a");
			Set<Bean> list = Collections.singleton(b);
			List<Bean> list2 = Collections.singletonList(b);
		}
		{
			List<String> list = Lists.newArrayList();
			list.addAll(Arrays.asList(new String[]{"a","b","c","d","e"}));
			Collections.swap(list, 0, list.size() - 1);
			System.out.println(list);
		}
		
		{
			List<String> list = Lists.newArrayList();
			list.addAll(Arrays.asList(new String[]{"a","b","c","d","e"}));
			List<String> syncList = Collections.synchronizedList(list);
		}
		
	}
}
