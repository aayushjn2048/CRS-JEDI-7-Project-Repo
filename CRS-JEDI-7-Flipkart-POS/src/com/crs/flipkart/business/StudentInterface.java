/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;

import com.crs.flipkart.bean.Student;

/**
 * @author HP
 *
 */
public interface StudentInterface {
	 public ArrayList<Student> viewStudentData();
	    public void activateGradeCard();
	    public Student viewStudentDetails(int studentId);
}
