/**
 * 
 */
package com.crs.flipkart.business;

import com.crs.flipkart.bean.Student;

import java.util.ArrayList;

/**
 * @author HP
 *
 */
public interface StudentInterface {

    public ArrayList<Student> viewStudentData();
    public void activateGradeCard();
    public Student viewStudentDetails(int studentId);

}
