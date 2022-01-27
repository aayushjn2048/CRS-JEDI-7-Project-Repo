/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;

import com.crs.flipkart.bean.Challan;
import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.SemesterRegistration;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.bean.StudentRegisteredCourses;
import com.crs.flipkart.validator.StandardResponse;

/**
 * @author HP
 *
 */
public interface AdminInterface {
	public void activateGradeCard();//1
	public void addProfessor(Professor professor);
	public void removeProfessor(int professorId);
	public void updateProfessor(Professor professor);
	public Boolean approveStudentRegistration(int studentId);
	public void viewAllStudents();
	public void viewAllProfessors();
	public void viewAllCourses();
	public void allocatePendingCourses();
	public Challan generateChallan(SemesterRegistration semesterRegistration);
}
