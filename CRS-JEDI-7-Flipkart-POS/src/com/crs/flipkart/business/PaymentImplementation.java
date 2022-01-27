/**
 * 
 */
package com.crs.flipkart.business;

/**
 * @author HP
 *
 */
import java.util.ArrayList;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.StudentRegisteredCourses;
import com.crs.flipkart.dao.CourseDaoImplementation;
import com.crs.flipkart.dao.CourseDaoInterface;

/**
 * @author HP
 *
 */
public class PaymentImplementation implements PaymentInterface {

	private CourseDaoInterface courseDaoImplementation = CourseDaoImplementation.getInstance();
	
	@Override
	public int calculateFees(int studentId) {
		// TODO Auto-generated method stub
		StudentRegisteredCourses studentRegistereCourses = courseDaoImplementation.getStudentRegisteredCourses(studentId);
		int totalFee = 0;
		totalFee += courseDaoImplementation.getCourseFromCourseId(studentRegistereCourses.getCourseId1()).getCourseFee();
		totalFee += courseDaoImplementation.getCourseFromCourseId(studentRegistereCourses.getCourseId2()).getCourseFee();
		totalFee += courseDaoImplementation.getCourseFromCourseId(studentRegistereCourses.getCourseId3()).getCourseFee();
		totalFee += courseDaoImplementation.getCourseFromCourseId(studentRegistereCourses.getCourseId4()).getCourseFee();
		return totalFee;
	}

}