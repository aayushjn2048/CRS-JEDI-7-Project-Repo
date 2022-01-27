/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.StudentRegisteredCourses;

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
            String sql = "INSERT INTO courseCatalog(name) values(?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, course.getName());
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
            String sql = "UPDATE courseCatalog set name=? where courseId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, course.getName());
            stmt.setInt(2, course.getCourseId());
            int rs = stmt.executeUpdate();
        } catch (Exception se) {
            // Handle errors for JDBC
            se.printStackTrace();
        }
    }
    

    @Override
   	public StudentRegisteredCourses getStudentRegisteredCourses(int studentId) {
   		// TODO Auto-generated method stub
   		try {
               PreparedStatement stmt = null;
               String sql = "select * from studentRegisteredDetails where studentId = ?";
               stmt = conn.prepareStatement(sql);
               stmt.setInt(1, studentId);
               ResultSet rs = stmt.executeQuery();
               StudentRegisteredCourses registeredCourses=new StudentRegisteredCourses();
               while(rs.next())
               {
               	registeredCourses.setStudentId(rs.getInt("studentId"));
               	registeredCourses.setCourseId1(rs.getInt("courseId1"));
               	registeredCourses.setCourseId2(rs.getInt("courseId2"));
               	registeredCourses.setCourseId3(rs.getInt("courseId3"));
               	registeredCourses.setCourseId4(rs.getInt("courseId4"));
               }
               return registeredCourses;
           } catch (Exception se) {
               // Handle errors for JDBC
               se.printStackTrace();
           }
   		return null;
   	}

   	@Override
   	public Course getCourseFromCourseId(int courseId) {
           try {
               PreparedStatement stmt = null;
               String sql = "SELETC * from course where courseId = ?";
               stmt = conn.prepareStatement(sql);
               stmt.setInt(1, courseId);
               ResultSet rs = stmt.executeQuery(sql);
               while(rs.next()){
                   Course c = new Course();
                   c.setCourseId(rs.getInt("courseId"));
                   c.setName(rs.getString("name"));
                   c.setProfessorId(rs.getInt("professorId"));
                   c.setCourseFee(rs.getInt("courseFee"));
                   return c;
               }
           } catch (Exception se) {
               // Handle errors for JDBC
               se.printStackTrace();
           }
           return null;
   	}

	@Override
	public ArrayList<Course> getAllCourse() {
		// TODO Auto-generated method stub
		try {
			ArrayList<Course> clist = new ArrayList<Course>();
			PreparedStatement stmt = null;
			String sql = "SELECT * FROM course";
			stmt = conn.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery(sql);
			 while(rs.next()){
		            //Display values
				 	Course c = new Course();
				 	c.setCourseId(rs.getInt("courseId"));
				 	c.setName(rs.getString("name"));
				 	c.setProfessorId(rs.getInt("professorId"));
				 	c.setCourseFee(rs.getInt("courseFee"));
				 	clist.add(c);
		         }
			 return clist;
			}
			catch(Exception e){
				
			}
		return null;
	}
}
