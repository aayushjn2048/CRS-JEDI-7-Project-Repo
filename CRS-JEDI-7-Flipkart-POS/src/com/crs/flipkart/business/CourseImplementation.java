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
	private static ArrayList<Course> courseData = new ArrayList<Course>();
	
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
	
	public static ArrayList<Course> viewCourseData()
	{
		return courseData;
	}
	public static void updateCourseData(ArrayList<Course> courseList)
	{
		courseData = courseList;
	}
	public boolean removeCourse(int courseId)
	{
		AdminDaoInterface admin = new AdminDaoOperation();
		if(admin.deleteCourse(courseId))
			return true;
		else
			return false;
	}
	public void addCourse(Course course)
	{
		AdminDaoInterface admin = new AdminDaoOperation();
		admin.addCourse(course);
	}
	public void updateCourse(Course course){

		AdminDaoInterface admin = new AdminDaoOperation();
		admin.updateCourse(course,course);	//we need to change in the menu so that users can only add the details they can update

	}
}
