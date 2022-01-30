/**
 * 
 */
package com.crs.flipkart.exceptions;

/**
 * @author HP
 *
 */
public class GradeCardNotPublishedException extends Exception{
	public GradeCardNotPublishedException()
	{
		System.out.println("GradeCard is not published yet");
	}
}