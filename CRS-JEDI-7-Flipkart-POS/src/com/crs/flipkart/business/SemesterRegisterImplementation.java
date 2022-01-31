/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;

import com.crs.flipkart.bean.Catalogue;
import com.crs.flipkart.bean.Course;
import com.crs.flipkart.exceptions.CourseNotFoundException;
import com.crs.flipkart.validator.StandardResponse;

/**
 * 
 * @author JEDI-03
 * SemesterRegisterImplementation class
 * 
 */
public class SemesterRegisterImplementation implements SemesterRegistration {

	
	/**
	 * Method for getting the register courses
	 * @return List of registered courses
	 */
	@Override
	public ArrayList<Course> registerCourses() {
		// TODO Auto-generated method stub
		return null;
	}
	private CourseInterface courseImplementation=CourseImplementation.getInstance();
	
	
	/**
	 * Method for Adding the course
	 * @param course object
	 * @return StandardResponse object
	 */
	@Override
	public StandardResponse addCourse(Course course) {
		// TODO Auto-generated method stub
		courseImplementation.addCourse(course);
		return new StandardResponse();
	}

	@Override
	
	/**
	 * Method for Removing the course
	 * @param courseId
	 * @throws CourseNotFoundException
	 * @return StandardResponse object
	 */
	public StandardResponse removeCourse(int courseId) throws CourseNotFoundException{
		// TODO Auto-generated method stub
		courseImplementation.removeCourse(courseId);
		return new StandardResponse();
	}

	
	/**
	 * Method for Total amount of fee for courses
	 * @param courseList : Courses available in the catalog
	 * @return finalsum
	 */
	public int payFee(ArrayList<Course> courses) {
		// TODO Auto-generated method stub
		Catalogue catalogue=new Catalogue();
		int finalSum=0;
		for(Course course:courses) {
			finalSum+=catalogue.getCourseAmount(course.getCourseId());
		}
		return finalSum;
	}

}
