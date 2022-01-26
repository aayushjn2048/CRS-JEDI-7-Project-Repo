/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.crs.flipkart.bean.Course;

/**
 * @author HP
 *
 */
public class CourseDaoImplementation implements CourseDaoInterface{
	private static CourseDaoImplementation instance = null;
    private Connection conn = DBConnection.connectDB();

    private CourseDaoImplementation() {
    }

    public static CourseDaoImplementation getInstance() {
        if (instance == null) {
            synchronized (CourseDaoImplementation.class) {
                instance = new CourseDaoImplementation();
            }
        }
        return instance;
    }

    @Override
    public void addCourse(Course course) {
        try {
            PreparedStatement stmt = null;
            String sql = "INSERT INTO courseCatalog(name,offeredSemester) values(?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, course.getName());
            stmt.setInt(2, course.getOfferedSemester());
            int rs = stmt.executeUpdate();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCourse(int courseId) {
        try {
            PreparedStatement stmt = null;
            String sql = "delete from courseCatalog where courseId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, courseId);
            int rs = stmt.executeUpdate();

        } catch (Exception se) {
            // Handle errors for JDBC
            se.printStackTrace();
        }
    }

    @Override
    public void updateCourse(Course course) {
        try {
            PreparedStatement stmt = null;
            String sql = "UPDATE courseCatalog set name=?, offeredSemester=? where courseId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, course.getName());
            stmt.setInt(2, course.getOfferedSemester());
            stmt.setInt(3, course.getCourseId());
            int rs = stmt.executeUpdate();
        } catch (Exception se) {
            // Handle errors for JDBC
            se.printStackTrace();
        }
    }
}
