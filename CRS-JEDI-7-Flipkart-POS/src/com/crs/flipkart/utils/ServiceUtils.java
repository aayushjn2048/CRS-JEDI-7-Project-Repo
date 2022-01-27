/**
 * 
 */
package com.crs.flipkart.utils;

import java.util.Random;

/**
 * @author HP
 *
 */
public class ServiceUtils {
	public static int createUserId()
	{
		Random rand = new Random();
		int userId = rand.nextInt(1000000)+100000;
		return userId;
	}
}
