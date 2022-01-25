package com.crs.flipkart.dao;

import com.crs.flipkart.bean.Student;
import com.crs.flipkart.business.StudentImplementation;
import com.crs.flipkart.constants.Gender;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImplementation implements StudentDaoInterface {

    private static StudentDaoImplementation instance = null;
    private Connection conn = DBConnection.connectDB();

    private void StudentImplementation(){}

    public static StudentDaoImplementation getInstance(){
        if(instance==null){
            synchronized (StudentImplementation.class){
                instance = new StudentDaoImplementation();
            }
        }
        return instance;
    }

    @Override
    public ArrayList<Student> viewAllStudents() {
        try {
            ArrayList<Student> list = new ArrayList<>();
            PreparedStatement stmt = null;
            String sql = "select s.studentId, u.name, u.address, u.gender, u.contactNo , s.gradeCardVisibility from student s, user u where u.userId = s.studentId";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                Student student= new Student();
                student.setStudentId(rs.getInt(1));
                student.setName(rs.getNString(2));
                student.setAddress(rs.getNString(3));
                student.setGender(Gender.stringToGender(rs.getNString(4)));
                student.setContactNo(rs.getNString(5));
                student.setGradeCardVisibility(rs.getInt(6)==1);
                System.out.println(student.getStudentId()+" "+student.getName()+" "+student.getAddress()+" "+student.getGender()+" "+student.getGradeCardVisibility());
            }
            return list;
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        }
        return null;
    }

    @Override
    public void activateGradeCard() {
        try {
            PreparedStatement stmt = null;
            String sql = "UPDATE student set gradeCardVisibility=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, 1);
            int rs = stmt.executeUpdate();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        }
    }

    @Override
    public Student viewStudentDetails(int studentId) {
        try {
            PreparedStatement stmt = null;
            String sql = "select * from student where studentId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            Student student = new Student();
            while(rs.next())
            {
                student.setStudentId(rs.getInt("studentId"));
                student.setName(rs.getNString("name"));
                student.setAddress(rs.getNString("address"));
                student.setGender(Gender.stringToGender(rs.getNString("gender")));
                student.setContactNo(rs.getNString("contactNo"));
                System.out.println(student.getStudentId()+" "+student.getName()+" "+student.getAddress()+" "+student.getGender()+" "+student.getGradeCardVisibility());
            }
            return student;

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        }
        return null;
    }
}
