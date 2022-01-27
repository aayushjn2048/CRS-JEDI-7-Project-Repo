/**
 * 
 */
package com.crs.flipkart.dao;

import java.util.ArrayList;

import com.crs.flipkart.bean.Student;
import com.crs.flipkart.bean.StudentCourseChoice;

/**
 * @author HP
 *
 */
public interface StudentDaoInterface {
	public ArrayList<Student> viewAllStudents();

    public void activateGradeCard();

    public Student viewStudentDetails(int studentId);
    
    public boolean addStudentData(Student student);
    
    public void storeStudentCourseChoice(StudentCourseChoice studentCourseChoice);
}
