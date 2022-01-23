/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;

import com.crs.flipkart.bean.Catalogue;
import com.crs.flipkart.bean.Course;
import com.crs.flipkart.validator.StandardResponse;

/**
 * @author TEST
 *
 */
public class SemesterRegisterImplementation implements SemesterRegistration {

	@Override
	public ArrayList<Course> registerCourses() {
		// TODO Auto-generated method stub
		return null;
	}
	CourseImplementation courseImplementation=new CourseImplementation();
	@Override
	public StandardResponse addCourse(Course course) {
		// TODO Auto-generated method stub
		courseImplementation.addCourse(course);
		return new StandardResponse();
	}

	@Override
	public StandardResponse removeCourse(int courseId) {
		// TODO Auto-generated method stub
		courseImplementation.removeCourse(courseId);
		return new StandardResponse();
	}

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
