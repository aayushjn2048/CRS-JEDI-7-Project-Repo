/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.constants.SqlQueryConstants;

/**
 * @author HP
 *
 */
public class ProfessorDaoOperation implements ProfessorDaoInterface {

	Connection conn = DBConnection.connectDB();
	
	
	@Override
	public boolean selectCourse(int professorId, int courseId) {
		// TODO Auto-generated method stub
		try {
			 
			PreparedStatement stmt = null;
			String sql = SqlQueryConstants.SELECT_COURSES_QUERY;
			stmt = conn.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery(sql);
			 
			 while(rs.next()){
				 if(rs.getInt("courseId")==courseId) {
					
					 if(rs.getInt("professorId")==0) {
						 
						 PreparedStatement stmt1 = null;
						 String sqlUpdate = SqlQueryConstants.SELECT_COURSES_QUERY1;
						 stmt1=conn.prepareStatement(sqlUpdate);
						
						 stmt1.setInt(1,professorId);
						 stmt1.setInt(2,courseId);
						 
						 int r=stmt1.executeUpdate();
						
						 return true;
						 
			            }
				 }
		            
		            
		       }
			 
			 	return false;
			 	
			}
			catch(Exception e){
				
			}
		return false;
		
	}
	
	
	@Override
	public Map<Integer,ArrayList<Student>> viewEnrolledStudents(int professorid) {
		// TODO Auto-generated method stub
		try {
			// System.out.println("hello");
			PreparedStatement stmt = null;
			String sql = "SELECT * FROM course WHERE professorId = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, professorid);
			ResultSet rs = stmt.executeQuery();
			
			Map<Integer,ArrayList<Student>> registered = new HashMap<>();
			while(rs.next()){
				ArrayList<Integer> temp = new ArrayList<Integer>();
				 
				ArrayList<Student> registeredstudents = new ArrayList<Student>();
				int courseid=rs.getInt("courseId");
		 		PreparedStatement stmt1 = null;
				String sql1 = SqlQueryConstants.VIEW_ENROLLED_STUDENTS_QUERY1;
				stmt1 = conn.prepareStatement(sql1);
				 ResultSet rs1 = stmt1.executeQuery(sql1);
				 while(rs1.next()) {
					
					 int courseid1=rs1.getInt("courseId1");
					 int courseid2=rs1.getInt("courseId2");
					 int courseid3=rs1.getInt("courseId3");
					 int courseid4=rs1.getInt("courseId4");
					
					 if(courseid1==courseid || courseid2==courseid || courseid3==courseid || courseid4==courseid) {
						
						 temp.add(rs1.getInt("studentId"));
							 
					 }
				 }
				for(int i=0;i<temp.size();i++) {
					int k=temp.get(i);
					PreparedStatement stmt2 = null;
					String sql2 = SqlQueryConstants.VIEW_ENROLLED_STUDENTS_QUERY2;
					stmt2 = conn.prepareStatement(sql2);
					stmt2.setInt(1, k);
					ResultSet rs3 = stmt2.executeQuery();
					Map<Student,Integer> check = new HashMap<>();
					while(rs3.next()) {
						Student stu = new Student();
						stu.setStudentId(rs3.getInt("studentId"));
						stu.setName(rs3.getString("name"));
						stu.setContactNo(rs3.getString("contactNo"));
							registeredstudents.add(stu);
							check.put(stu,1);
						
					}			
				}
					
				
				registered.put(courseid,registeredstudents );
			}
			 return registered;
		}
		catch(Exception e){
			
		}
		return null;
	}
	
	
	public Boolean assignGrade(int studentId, int courseId,float grade) {
		try {
			PreparedStatement stmt = null;
			
			String sql = SqlQueryConstants.ASSIGN_GRADES_QUERY;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, studentId);
			stmt.setInt(2, courseId);
			stmt.setFloat(3, grade);
			int rs = stmt.executeUpdate();
			if(rs==0)
				return false;
			return true;
		} catch (Exception e) {
			
		} finally {
			
		}
		return false;
	}


	@Override
	public ArrayList<Course> viewAvailableCourses() {
		try {
			ArrayList<Course> clist = new ArrayList<Course>();
			PreparedStatement stmt = null;
			String sql = SqlQueryConstants.AVAILABLE_COURSE_QUERY;
			stmt = conn.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery(sql);
			 while(rs.next()){
		            //Display values
				 	Course c = new Course();
				 	c.setCourseId(rs.getInt("courseId"));
				 	c.setName(rs.getString("name"));
				 	clist.add(c);
		         }
			 return clist;
			}
			catch(Exception e){
				
			}
		return null;
	}

}
