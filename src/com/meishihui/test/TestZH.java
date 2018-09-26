package com.meishihui.test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class TestZH {
	
	@Test
	public void testMap(){
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put(1+"", 111);
		map.put(2+"", 222);
		map.put(3+"", 333);
		map.put(4+"", 444);
		for(int i=0;i<10;i++){
			if(map.containsKey(i+"")){
				System.out.println(map.get(i+""));
			}
		}
		Calendar date = Calendar.getInstance();

		
	}
	@Test
	public void testConvert(){
		String str = "00340";
		System.out.println(Integer.valueOf(str));
	}
}
