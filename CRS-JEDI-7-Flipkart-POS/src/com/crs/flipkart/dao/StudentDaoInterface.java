/**
 * 
 */
package com.crs.flipkart.dao;

import java.util.ArrayList;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.bean.StudentCourseChoice;

/**
 * @author HP
 *
 */
public interface StudentDaoInterface {
	public ArrayList<Student> viewAllStudents();

    public Boolean activateGradeCard();

    public Student viewStudentDetails(int studentId);
    
    public ArrayList<Course> viewAllCourses();
    
    public boolean addStudentData(Student student);
    
    public void storeStudentCourseChoice(StudentCourseChoice studentCourseChoice);
    
    public Boolean studentAlreadyRegistered(int studentId);
    
    public void makePaymentSuccessful(int studentId);

    public Boolean update(String username,String oldPassword,String newPassword);
}
