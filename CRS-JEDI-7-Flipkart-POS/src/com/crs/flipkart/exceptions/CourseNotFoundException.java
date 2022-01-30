/**
 * 
 */
package com.crs.flipkart.exceptions;

/**
 * @author HP
 *
 */
public class CourseNotFoundException extends Exception{
	
	public CourseNotFoundException()
	{
		System.out.println("No courses found");
	}
	
}
