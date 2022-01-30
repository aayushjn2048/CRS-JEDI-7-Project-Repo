/**
 * 
 */
package com.crs.flipkart.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author HP
 *
 */
public class DateAndTimeUtil {
	
	public static void loginDisplayDateAndTime()
	{
		   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now();  
		   System.out.println("--------------------------------------------------------------");
		   System.out.println("\t\tLogin time : "+dtf.format(now));  
		   System.out.println("--------------------------------------------------------------\n");
	}
	
	public static void logoutDisplayDateAndTime()
	{
		   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now();  
		   System.out.println("--------------------------------------------------------------");
		   System.out.println("\t\tLogout time : "+dtf.format(now));  
		   System.out.println("--------------------------------------------------------------\n");
	}

}
