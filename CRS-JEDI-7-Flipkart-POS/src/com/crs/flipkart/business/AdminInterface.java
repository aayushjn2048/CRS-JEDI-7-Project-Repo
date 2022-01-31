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
import com.crs.flipkart.exceptions.ProfessorNotFoundException;
import com.crs.flipkart.validator.StandardResponse;
/**
 * 
 * @author JEDI-03
 * Interface for Admin Operations
 * 
 */
public interface AdminInterface {
	
	/**
	 * Method for Activating GradeCard
	 */
	public void activateGradeCard();
	
	/**
	 * Method for Adding Professor
	 * @param professor object
	 * @throws ProfessorNotFoundException
	 */
	public void addProfessor(Professor professor) throws ProfessorNotFoundException;
	
	/**
	 * Method for Removing Professor
	 * @param professor Id
	 * @throws ProfessorNotFoundException
	 */
	public void removeProfessor(int professorId) throws ProfessorNotFoundException;
	
	/**
	 * Method for Updating Professor
	 * @param professor object
	 * @throws ProfessorNotFoundException
	 */
	public void updateProfessor(Professor professor) throws ProfessorNotFoundException;
	
	/**
	 * Method for Approving Student Registration
	 * @param student id
	 * @return boolean object, student approved or not
	 */
	public Boolean approveStudentRegistration(int studentId);
	
	/**
	 * Method for Viewing All Student Details 
	 */
	public void viewAllStudents();
	
	/**
	 * Method for Viewing All Professor Details 
	 */
	public void viewAllProfessors();
	
	/**
	 * Method for Viewing All Course Details 
	 */
	public void viewAllCourses();
	
	/**
	 * Method for Allocating Pending Courses 
	 */
	
	public void allocatePendingCourses();
	
	/**
	 * Method for Generating Challan
	 * @returns Challan object
	 */
	public Challan generateChallan(SemesterRegistration semesterRegistration);
}
