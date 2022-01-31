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
import com.crs.flipkart.exceptions.CourseNotFoundException;
import com.crs.flipkart.validator.StandardResponse;

/**
 * 
 * @author JEDI-03
 * Implementations of Course Operations
 * 
 */
public class CourseImplementation implements CourseInterface{
	
	private static CourseImplementation instance = null;
	private CourseDaoInterface courseDaoImplementation = CourseDaoImplementation.getInstance();

	private CourseImplementation(){}
	
	
	/**
	 * Method to make CourseImplementation Singleton
	 */
	
	public static CourseImplementation getInstance(){
		if(instance==null){
			synchronized (CourseImplementation.class){
				instance = new CourseImplementation();
			}
		}
		return instance;
	}
	
	 /**
		 * Method for Removing Course to Catalog DataBase
		 * @param courseId
		 * throws CourseNotFoundException
	 */
	public void removeCourse(int courseId) throws CourseNotFoundException
	{
		AdminDaoInterface admin = new AdminDaoOperation();
		if(admin.deleteCourse(courseId))
		{
			System.out.println("Course details deleted from the database");
		}
		else
		{
			throw new CourseNotFoundException();
		}
	}
	
	 /**
		 * Method for Adding Course to Catalog DataBase
		 * @param course the course object whose details need to be added
	 */
	public void addCourse(Course course)
	{
		AdminDaoInterface admin = new AdminDaoOperation();
		Boolean ans = admin.addCourse(course);
		if(ans)
			System.out.println("Course added successfully!!!");
		else
			System.out.println("Error while executing operation");
	}
	
	 /**
	 * Method for Updating Course to Catalog DataBase
	 * @param course object
	 * @throws CourseNotFoundException
	 */
	public void updateCourse(Course course) throws CourseNotFoundException{

		AdminDaoInterface admin = new AdminDaoOperation();
		if(admin.updateCourse(course))	//we need to change in the menu so that users can only add the details they can update
			System.out.println("Course updated successfully!!!");
		else
		{
			throw new CourseNotFoundException();
		}
	}
	
	/**
	 * Method to get all the courses
	 * @return List of courses
	 */
	@Override
	public ArrayList<Course> getAllCourses() {
		StudentDaoInterface studao = new StudentDaoOperation();
		return studao.viewAllCourses();
	}

	/**
	 * Method to get the course from course catalog
	 * @param courseId
	 * @throws CourseNotFoundException
	 * @return course object
	 */
	@Override
	public Course getCourseFromCatalog(int courseId) throws CourseNotFoundException{
		// TODO Auto-generated method stub
		ArrayList<Course> courseList = getAllCourses();
		if(courseList.size()==0)
		{
			throw new CourseNotFoundException();
		}
		else
		{
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
}
