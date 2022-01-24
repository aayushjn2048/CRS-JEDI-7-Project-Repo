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
	public String approveStudentRegistration(Student student);
	public ArrayList<Student> viewAllStudents();
	public ArrayList<Professor> viewAllProfessors();
	public ArrayList<Course> viewAllCourses();
	public void registerCourses();
	public void allocatePendingCourses();
	public Challan generateChallan(SemesterRegistration semesterRegistration);
}
