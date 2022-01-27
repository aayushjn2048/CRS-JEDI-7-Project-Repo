/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;

import com.crs.flipkart.bean.Student;
import com.crs.flipkart.bean.StudentCourseChoice;

/**
 * @author HP
 *
 */
public interface StudentInterface {
	 public ArrayList<Student> viewStudentData();
	 public Student viewStudentDetails(int studentId);
	 public StudentCourseChoice selectCourses(int studentId);
	 public void displayCourseCatalog();
	 public void displayGradeCard(int studentId);
	 public Boolean studentAlreadyRegistered(int studentId);
	 public void makePaymentSuccessful(int studentId);
}
