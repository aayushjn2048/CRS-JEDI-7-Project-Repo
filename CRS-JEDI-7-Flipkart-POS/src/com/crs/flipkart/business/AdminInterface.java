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
	public StandardResponse addCourse(Course course);
	public StandardResponse removeCourse(int courseId);
	public StandardResponse updateCourse(Course course);
	public StandardResponse addProfessor(Professor professor);
	public StandardResponse removeProfessor(int professorId);
	public StandardResponse updateProfessor(Professor professor);
	public StandardResponse approveStudentRegistration(Student student);
	public ArrayList<Course> viewAllCourses();
	public void activateGradeCard();
	public ArrayList<Student> viewAllStudents();
	public ArrayList<Professor> viewAllProfessors();
	public ArrayList<StudentRegisteredCourses> allocatePendingCourses();
	public Challan generateChallan(SemesterRegistration semesterRegistration);
}
