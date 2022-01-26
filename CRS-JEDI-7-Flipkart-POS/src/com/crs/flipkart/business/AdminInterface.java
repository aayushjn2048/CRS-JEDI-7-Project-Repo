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
	public void deactivateGradeCard();
	public String addProfessor(Professor professor);
	public String removeProfessor(int professorId);
	public String updateProfessor(Professor professor);
	public Boolean approveStudentRegistration(int studentId);
	public ArrayList<Student> viewAllStudents();
	public void viewAllProfessors();
	public void viewAllCourses();
	public void allocatePendingCourses();
	public Challan generateChallan(SemesterRegistration semesterRegistration);
}
