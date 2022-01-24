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
	public StandardResponse addCourse(Course course);    //1
	public StandardResponse removeCourse(int courseId);//1
	public StandardResponse updateCourse(Course course);//1
	public StandardResponse addProfessor(Professor professor);//2
	public StandardResponse removeProfessor(int professorId);//2
	public StandardResponse updateProfessor(Professor professor);//2
	public StandardResponse approveStudentRegistration(Student student);//2
	public ArrayList<Course> viewAllCourses();//3
	public void activateGradeCard();//1
	public ArrayList<Student> viewAllStudents();//3
	public ArrayList<Professor> viewAllProfessors();//3
	public ArrayList<StudentRegisteredCourses> allocatePendingCourses();//3
	public Challan generateChallan(SemesterRegistration semesterRegistration);//3
}
