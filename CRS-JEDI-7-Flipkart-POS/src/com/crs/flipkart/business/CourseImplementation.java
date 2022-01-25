/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.dao.AdminDaoInterface;
import com.crs.flipkart.dao.AdminDaoOperation;
import com.crs.flipkart.dao.CourseDaoImplementation;
import com.crs.flipkart.dao.CourseDaoInterface;
import com.crs.flipkart.validator.StandardResponse;

/**
 * @author HP
 *
 */
public class CourseImplementation implements CourseInterface{

	private static CourseImplementation instance = null;
	private CourseDaoInterface courseDaoImplementation = CourseDaoImplementation.getInstance();

	private CourseImplementation(){}

	public static CourseImplementation getInstance(){
		if(instance==null){
			synchronized (CourseImplementation.class){
				instance = new CourseImplementation();
			}
		}
		return instance;
	}

	private static ArrayList<Course> courseData = new ArrayList<Course>();
	public static ArrayList<Course> viewCourseData()
	{
		return courseData;
	}
	public static void updateCourseData(ArrayList<Course> courseList)
	{
		courseData = courseList;
	}

	@Override
	public void removeCourse(int courseId) {
		courseDaoImplementation.deleteCourse(courseId);
	}

	@Override
	public void addCourse(Course course) {
		courseDaoImplementation.addCourse(course);
	}

	@Override
	public void updateCourse(Course course) {
		courseDaoImplementation.updateCourse(course);
	}
}
