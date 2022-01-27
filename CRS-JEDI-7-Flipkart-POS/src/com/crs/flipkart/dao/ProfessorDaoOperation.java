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
			String sql = "SELECT * FROM courseCatalog";
			stmt = conn.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery(sql);
			 
			 while(rs.next()){
				 if(rs.getInt("courseId")==courseId) {
					
					 if(rs.getInt("professorId")==0) {
						 
						 PreparedStatement stmt1 = null;
						 String sqlUpdate = "UPDATE courseCatalog "
					                + "SET professorId = ? "
					                + "WHERE courseId = ?";
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
	public Map<Integer,ArrayList<Student>> viewEnrolledStudents(int professorId) {
		// TODO Auto-generated method stub
		try {
			 
			PreparedStatement stmt = null;
			String sql = "SELECT * FROM courseCatalog WHERE professorId = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, professorId);
			ResultSet rs = stmt.executeQuery(sql);
			Map<Integer,ArrayList<Student>> registeredstudents = new HashMap<>();
			while(rs.next()){
				ArrayList<Student> stulist = new ArrayList<Student>();
			 	int courseid=rs.getInt("courseId");
		 		PreparedStatement stmt1 = null;
				String sql1 = "SELECT * FROM studentRegisteredCourses sc JOIN user ON (sc.studentId = user.userId) WHERE (courseId1 = ? OR courseId2 = ? OR courseId3 = ? OR courseId4 = ?)";
				stmt1 = conn.prepareStatement(sql1);
				stmt1.setInt(1, courseid);
				stmt1.setInt(2, courseid);
				stmt1.setInt(3, courseid);
				stmt1.setInt(4, courseid);
				ResultSet rs1 = stmt1.executeQuery(sql1);
				while(rs1.next()) {
					Student stu = new Student();
					stu.setStudentId(rs.getInt("studentId"));
					stu.setName(rs.getString("name"));
					stu.setContactNo(rs.getString("contactNo"));
					stulist.add(stu);	
				}
				registeredstudents.put(courseid, stulist);
			}
			 
		 	if(registeredstudents.size()==0) {
		 		System.out.println("No students enrolled");
		 	}
		 	else {
		 		return registeredstudents;
		 	}
		}
		catch(Exception e){
			
		}
		return null;
	}
	
	
	public void assignGrade(int studentId, int courseId, int semesterNumber) {
		ArrayList<Integer> semester = new ArrayList<Integer>();
		semester.add(1);
		semester.add(2);
		semester.add(3);
		semester.add(4);
		semester.add(5);
		semester.add(6);
		semester.add(7);
		semester.add(8);
		semester.add(9);
		semester.add(10);
		Random random = new Random();
		int index = random.nextInt(9);
		try {
			PreparedStatement stmt = null;
			
			String sql = "INSERT INTO grade(studentId,courseId,semenster,grade) values(?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, studentId);
			stmt.setInt(2, courseId);
			stmt.setInt(3, semesterNumber);
			stmt.setInt(4, semester.get(index));
			int rs = stmt.executeUpdate();
			
		} catch (Exception e) {
			
		} finally {
			
		}
		
	}


	@Override
	public ArrayList<Course> viewAvailableCourses() {
		try {
			ArrayList<Course> clist = new ArrayList<Course>();
			PreparedStatement stmt = null;
			String sql = "SELECT * FROM courseCatalog WHERE professorId IS NULL";
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
