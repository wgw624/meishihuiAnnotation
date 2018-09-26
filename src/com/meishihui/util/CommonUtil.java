package com.meishihui.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CommonUtil {
	/**
	 * 
	 * @param date(yyyy-MM-dd)
	 * @return
	 */
	private static String  format = "yyyy-MM-dd";
	
	public static String getNextDate(String date){
		return getPrevDate(date,-1);
	}
	public static String getPrevDate(String date,int num){
		Date nextDay = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(getFormatDate(date));
		cal.add(cal.DAY_OF_MONTH, 0-num);
		nextDay = cal.getTime();
		return getDateTransStr(nextDay);
	}
	public static Date getPrevDate(Date date,int num){
		Date nextDay = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(cal.DAY_OF_MONTH, 0-num);
		nextDay = cal.getTime();
		return nextDay;
	}
	public static Date getNextDate(Date date){
		return getPrevDate(date,-1);
	}
	
	public static Date getFormatDate(String date){
		Date tmpDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			tmpDate = sdf.parse(date);
		} catch (ParseException e) {
			throw new RuntimeException("日期转换异常");
		}
		return tmpDate;
	}

	public static String getDateTransStr(Date date){
		String retStr = null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		retStr = sdf.format(date);
		return retStr;
	}
	
	public static int getCurrentDateMonth(){
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH);
		return month+1;
	}
	public static int getCurrentDateMonth(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);
		return month+1;
	}
	public static int getCurrentDateYear(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		return year;
	}
	public static int getCurrentDateYear(){
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		return year;
	}
	/**
	 * 获取某年某月有多少天
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getDaysYearMonth(int year,int month){
		
		int day = 0;
		if(month == 2){
			if(year%4 == 0 && year%100!=0 || year%400 ==0){
				day = 29;
			}else{
				day =28;
			}
			
		}else{
			switch(month){
				case 1:
				case 3:
				case 5:
				case 7:
				case 8:
				case 10:
				case 12:
				day = 31;break;	
				case 4:
				case 6:
				case 9:
				case 11:
				day = 30;break;
			}
		}
		
		return day;
	}
	/**非空判断*/
	public static boolean isNotNullList(@SuppressWarnings("rawtypes") List list){
		boolean flag = false;
		if(list !=null && list.size()>0){
			flag = true;
		}else{
			flag = false;
		}
		return flag;
	}
	/**非空判断*/
	public static boolean isNotNullSet(@SuppressWarnings("rawtypes") Set set){
		boolean flag = false;
		if(set !=null && set.size()>0){
			flag = true;
		}else{
			flag = false;
		}
		return flag;
	}
	/**非空判断*/
	public static boolean isNotNullMap(@SuppressWarnings("rawtypes") Map map){
		boolean flag = false;
		if(map !=null && map.size()>0){
			flag = true;
		}else{
			flag = false;
		}
		return flag;
	}
	public static boolean isNotNullDate(Date date){
		boolean flag = true;
		if(date == null || "undefined".equals(date)){
			flag = false;
		}
		return flag;
	}
	/**
	 *非空 返回true
	 * @param str
	 * @return
	 */
	public static boolean isNotNullStr(String str){
		boolean flag = true;
		if(str == null || "".equals(str)){
			flag = false;
		}
		return flag;
	}	/**
	 *非空 返回true
	 * @param str
	 * @return
	 */
	public static boolean isNotNullObj(Object obj){
		boolean flag = true;
		if(obj == null){
			flag = false;
		}
		return flag;
	}
}
