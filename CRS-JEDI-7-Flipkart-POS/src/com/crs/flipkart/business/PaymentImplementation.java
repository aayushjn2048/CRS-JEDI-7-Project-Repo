/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;
import java.util.Scanner;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.StudentRegisteredCourses;
import com.crs.flipkart.dao.CourseDaoImplementation;
import com.crs.flipkart.dao.CourseDaoInterface;
import com.crs.flipkart.utils.ServiceUtils;

/**
 * 
 * @author JEDI-03
 * PaymentImplementation class
 * 
 */
public class PaymentImplementation implements PaymentInterface {

	private CourseDaoInterface courseDaoImplementation = CourseDaoImplementation.getInstance();

	/**
	 * Method to calculate the total fee for student
	 * @param StudentId
	 * @return totalFee
	 */
	@Override
	public int calculateFees(int studentId) {
		// TODO Auto-generated method stub
		StudentRegisteredCourses studentRegistereCourses = courseDaoImplementation
				.getStudentRegisteredCourses(studentId);
		int totalFee = 0;
		totalFee += courseDaoImplementation.getCourseFromCourseId(studentRegistereCourses.getCourseId1())
				.getCourseFee();
		totalFee += courseDaoImplementation.getCourseFromCourseId(studentRegistereCourses.getCourseId2())
				.getCourseFee();
		totalFee += courseDaoImplementation.getCourseFromCourseId(studentRegistereCourses.getCourseId3())
				.getCourseFee();
		totalFee += courseDaoImplementation.getCourseFromCourseId(studentRegistereCourses.getCourseId4())
				.getCourseFee();
		return totalFee;
	}
	Scanner scn;
	@Override
	/**
	 * Method to Display the payment methods
	 */
	public String displayPaymentMethods() {
		// TODO Auto-generated method stub
		scn = new Scanner(System.in);
		System.out.println("Choose payment mode\n\t1)Offline\n\t2)Online");
		System.out.print("Enter Choice: ");
		int paymentMethod = scn.nextInt();
		switch (paymentMethod) {
		case 1:
			return displayOffline();
		case 2:
			return displayOnline();
		}
		return null;
	}

	/**
	 * Method to Display the online mode methods
	 * @return returns the reference number
	 */
	private String displayOnline() {
		// TODO Auto-generated method stub
		System.out.println("\nEnter Debit/Credit Card Details:-");
		System.out.print("\n\tCard number: ");
		scn.next();
		System.out.print("\n\tExpiry Date: ");
		scn.next();
		System.out.print("\n\tCVV: ");
		scn.next();
		String refNo = "R"+ServiceUtils.createUserId();
		return refNo;
	}

	/**
	 * Method to Display the offline mode methods
	 */
	private String displayOffline() {
		// TODO Auto-generated method stub
		System.out.print("Enter Reference Details for Offline payment: ");
		return scn.next();
	}

}