package com.meishihui.util;

import java.security.MessageDigest;

public class MD5 {
	public static String getMd5Str(String str) throws Exception{
		  MessageDigest md5 = MessageDigest.getInstance("MD5");
          md5.update(str.getBytes("utf-8"));
          byte[] bt = md5.digest();
          StringBuffer strBuffer = new StringBuffer();
          for(int i=0;i<bt.length;i++){
              strBuffer.append(Integer.toHexString(0xff & bt[i]));
          }
          String resultStr = strBuffer.toString();

		return resultStr;
	}
}
