package com.common.util;

import java.util.Random;


/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
public class RandomWordUtil {
	
	  private static String characters="ABCDEFGHKMNPQSTWXYZ23456789";
	  private static int minLength=6;
	  private static int maxLength=8;

	  public static String getRandomWord() {
	    Random rnd = new Random();
	    StringBuffer sb = new StringBuffer();
	    int l = minLength + (maxLength > minLength ? rnd.nextInt(maxLength - minLength) : 0);
	    for (int i = 0; i < l; i++) {
	      int j = rnd.nextInt(characters.length());
	      sb.append(characters.charAt(j));
	    }
	    return sb.toString();
	  }
	  
	  public static void main(String[] args) {
		  for (int i = 0; i < 43; i++) {
			System.out.println(getRandomWord());
		}
		  
	  }
	  
}
