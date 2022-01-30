/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.exceptions.CourseNotFoundException;
import com.crs.flipkart.validator.StandardResponse;

/**
 * @author TEST
 *
 */
public interface SemesterRegistration {
	public ArrayList<Course> registerCourses();
	public StandardResponse addCourse(Course course);   
	public StandardResponse removeCourse(int courseId) throws CourseNotFoundException;
	public int payFee(ArrayList<Course> courses);
}
