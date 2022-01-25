package com.crs.flipkart.dao;

import com.crs.flipkart.bean.Student;

import java.util.ArrayList;
import java.util.List;

public interface StudentDaoInterface {

    public ArrayList<Student> viewAllStudents();

    public void activateGradeCard();

    public Student viewStudentDetails(int studentId);
}