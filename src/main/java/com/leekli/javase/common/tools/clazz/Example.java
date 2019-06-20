package com.leekli.javase.common.tools.clazz;

import java.text.MessageFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.zone.ZoneOffsetTransition;
import java.time.zone.ZoneRules;
import java.util.Arrays;
import java.util.Calendar;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.function.Function;

public class Example {
	
	
	public static  String dst(String city){
		
		
		
		StringBuilder sb = new StringBuilder();
		
		ZoneId zone = ZoneId.of(city);
		ZoneRules rules = zone.getRules();
		
	    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
	    cal.set(Calendar.MONTH, 0);
	    cal.set(Calendar.DAY_OF_MONTH,1);
	    cal.set(Calendar.HOUR_OF_DAY,0);
	    cal.set(Calendar.MINUTE,0);
	    cal.set(Calendar.SECOND,0);
	    
	    ZoneOffsetTransition next = rules.nextTransition(Instant.ofEpochMilli(cal.getTimeInMillis()));
	    long duration = next.getDuration().toMinutes();
	    boolean isDSTBegin = true;
	    if( duration  >0   ){
	    	isDSTBegin = true;
	    }else{
	    	isDSTBegin = false;
	    }
	    String DST = isDSTBegin?"开始":"结束";
	    
	    String  format =  "夏令时{0}:在当地时间 {1}时，  时钟调整 {2}小时， 变为 {3}";
	    Object [] array = new Object[]{DST,next.getDateTimeBefore(),next.getDuration().toHours(),next.getDateTimeAfter()};
	    String value = MessageFormat.format(format, array);
	    sb.append(value).append("\n");
	    
	    //next
	    cal.setTimeInMillis(next.getInstant().toEpochMilli());
	    next = rules.nextTransition(Instant.ofEpochMilli(cal.getTimeInMillis()));
	    duration = next.getDuration().toMinutes();
	    isDSTBegin = true;
	    if( duration  >0   ){
	    	isDSTBegin = true;
	    }else{
	    	isDSTBegin = false;
	    }
	    DST = isDSTBegin?"开始":"结束";
	    
	    format =  "夏令时{0}:在当地时间 {1}时，  时钟调整 {2}小时， 变为 {3}";
	    array = new Object[]{DST,next.getDateTimeBefore(),next.getDuration().toHours(),next.getDateTimeAfter()};
	    value = MessageFormat.format(format, array);
	    sb.append(value).append("\n");
	    
		return sb.toString();
	}
	
	
	public static void main(String[] args) {
		
		
		String s = dst("Australia/Sydney");
		System.out.println(s);
	    
		//2019悉尼 (Sydney) 夏令时开始结束时间
		ZoneId zone = ZoneId.of("Australia/Sydney");
	    ZoneRules rules = zone.getRules();
	    
	    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
	    cal.set(Calendar.MONTH, 0);
	    cal.set(Calendar.DAY_OF_MONTH,1);
	    cal.set(Calendar.HOUR_OF_DAY,0);
	    cal.set(Calendar.MINUTE,0);
	    cal.set(Calendar.SECOND,0);
	    
	    
	    ZoneOffsetTransition next = rules.nextTransition(Instant.ofEpochMilli(cal.getTimeInMillis()));

	    long duration = next.getDuration().toMinutes();
	    if( duration  >0   ){
	    	System.out.println("夏令时 开始时间");
	    }else{
	    	System.out.println("夏令时 结束时间");
	    }
	    System.out.println(next.getDateTimeBefore());
	    System.out.println(next.getDateTimeAfter());
	    System.out.println(next.getOffsetAfter());
	    
	    
	    cal.setTimeInMillis(next.getInstant().toEpochMilli());
	    ZoneOffsetTransition next2 = rules.nextTransition(Instant.ofEpochMilli(cal.getTimeInMillis()));
	    
	    long duration2 = next2.getDuration().toMinutes();
	    if( duration2  >0   ){
	    	System.out.println("夏令时 开始时间");
	    }else{
	    	System.out.println("夏令时 结束时间");
	    }
	    
	    System.out.println(next2.getDateTimeBefore());
	    System.out.println(next2.getDateTimeAfter());
	    System.out.println(next2.getOffsetAfter());
	    
	    
	    
	    
		//Objects
		
		//Collections Collectors
		
		
		//Maps
		
		//Arrays
		
		
		//Lists
		
		
		//MapUtils
		
		
		//ListUtils
		
	}
	
	public static int testToInt(Bean bean,Function<Bean,Integer> lt){
		return lt.apply(bean);
	}
	public static class Bean implements Comparable<Bean>{
		private String name;
		public Bean() {
			 
		}
		public void setName(String n){
			this.name = n;
		}
		
		public Bean(String name) {
			 this.name = name;
		}

		@Override
		public String toString() {
			return "Bean [name=" + name + "]";
		}

		@Override
		public boolean equals(Object obj) {
			if( ! (obj instanceof Bean)){
				return false;
			}
			Bean o = (Bean)obj;
			return o.name.equals(name);
		}

		@Override
		public int compareTo(Bean o) {
			return name.compareTo(o.name);
		}
	}
 
}
