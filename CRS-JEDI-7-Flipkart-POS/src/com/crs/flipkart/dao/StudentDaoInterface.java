/**
 * 
 */
package com.crs.flipkart.dao;

import java.util.ArrayList;

import com.crs.flipkart.bean.Student;

/**
 * @author HP
 *
 */
public interface StudentDaoInterface {
	public ArrayList<Student> viewAllStudents();

    public void activateGradeCard();

    public Student viewStudentDetails(int studentId);
    
    public boolean addStudentData(Student student);
}
