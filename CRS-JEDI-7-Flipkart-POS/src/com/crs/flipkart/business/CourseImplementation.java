/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;

import com.crs.flipkart.bean.Course;

/**
 * @author HP
 *
 */
public class CourseImplementation {
	private static ArrayList<Course> courseData = new ArrayList<Course>();
	public static ArrayList<Course> viewCourseData()
	{
		return courseData;
	}
}
