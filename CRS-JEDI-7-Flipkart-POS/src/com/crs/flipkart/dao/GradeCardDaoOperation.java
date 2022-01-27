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
import com.crs.flipkart.bean.GradeCard;

/**
 * @author HP
 *
 */
public class GradeCardDaoOperation implements GradeCardDaoInterface{
	
	private static GradeCardDaoOperation instance = null;
    private Connection conn = DBConnection.connectDB();
    private CourseDaoInterface courseDaoOperation = CourseDaoImplementation.getInstance();

    private GradeCardDaoOperation() {
    }

    public static GradeCardDaoOperation getInstance() {
        if (instance == null) {
            synchronized (GradeCardDaoOperation.class) {
                instance = new GradeCardDaoOperation();
            }
        }
        return instance;
    }
	

    @Override
	public GradeCard getGradeCard(int studentId) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement stmt = null;
			String sql = "select * from gradeCard where studentId = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, studentId);
			ResultSet rs = stmt.executeQuery();
			sql = "select * from student where studentId = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, studentId);
			ResultSet rs1 = stmt.executeQuery();
			GradeCard gradeCard = new GradeCard();
			while(rs1.next())
				gradeCard.setPublished(rs1.getInt("gradeCardVisibility")==1);
			float cgpa = (float)0.0;
			ArrayList<Course> courseList = new ArrayList<>();
			gradeCard.setStudentId(studentId);
			while(rs.next())
			{
				cgpa += rs.getFloat("grade");
				courseList.add(courseDaoOperation.getCourseFromCourseId(rs.getInt("courseId")));
			}
			gradeCard.setSgpa((float)(cgpa/4.0));
			gradeCard.setRegisteredCourse(courseList);
			return gradeCard;
			
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		}  finally {
			// finally block used to close resources // nothing we can do//end finally try
		}
		
		return null;
	}

	public int getGradeFromCourseId(int studentId, int courseId) {
		// TODO Auto-generated method stub
		 try {
	            PreparedStatement stmt = null;
	            String sql = "select grade from gradeCard where studentId = ? AND courseId=?";
	            stmt = conn.prepareStatement(sql);
	            stmt.setInt(1, studentId);
	            stmt.setInt(2, courseId);
	            ResultSet rs = stmt.executeQuery();
	            int grade = 0;
	            while(rs.next()){
					grade=rs.getInt("grade");

				}
	            return grade;
	        } catch (SQLException se) {
	            // Handle errors for JDBC
	            se.printStackTrace();
	        }
		return 0;
	}

}
