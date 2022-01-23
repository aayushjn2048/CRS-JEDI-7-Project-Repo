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
	public static void updateCourseData(ArrayList<Course> courseList)
	{
		courseData = courseList;
	}
	public static void removeCourse(int courseId)
	{
		for(Course c: courseData)
		{
			if(c.getCourseId()==courseId)
			{
				courseData.remove(c);
				break;
			}
		}
	}
	public static void addCourse(Course course)
	{
		courseData.add(course);
	}
}
