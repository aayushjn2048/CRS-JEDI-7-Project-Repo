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
import com.crs.flipkart.dao.StudentDaoInterface;
import com.crs.flipkart.dao.StudentDaoOperation;
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
	
	public void removeCourse(int courseId)
	{
		AdminDaoInterface admin = new AdminDaoOperation();
		if(admin.deleteCourse(courseId))
			System.out.println("Course details deleted from the database");
		else
			System.out.println("Course with entered courseId does not exist");
	}
	public void addCourse(Course course)
	{
		AdminDaoInterface admin = new AdminDaoOperation();
		Boolean ans = admin.addCourse(course);
		if(ans)
			System.out.println("Course added successfully!!!");
		else
			System.out.println("Error while executing operation");
	}
	public void updateCourse(Course course){

		AdminDaoInterface admin = new AdminDaoOperation();
		if(admin.updateCourse(course))	//we need to change in the menu so that users can only add the details they can update
			System.out.println("Course updated successfully!!!");
		else
			System.out.println("Course with entered course Id does not exist");
	}
	
	@Override
	public ArrayList<Course> getAllCourses() {
		StudentDaoInterface studao = new StudentDaoOperation();
		return studao.viewAllCourses();
	}

	@Override
	public Course getCourseFromCatalog(int courseId) {
		// TODO Auto-generated method stub
		ArrayList<Course> courseList = getAllCourses();
		
		for(Course course:courseList)
		{
			if(course.getCourseId() == courseId)
			{
				return course;
			}
		}	
		return null;
	}
}
