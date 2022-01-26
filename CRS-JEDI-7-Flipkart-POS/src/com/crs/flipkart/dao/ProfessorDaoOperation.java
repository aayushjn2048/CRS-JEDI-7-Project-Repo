/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

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
	public void viewEnrolledStudents(int professorId) {
		// TODO Auto-generated method stub
		try {
			 
			PreparedStatement stmt = null;
			String sql = "SELECT * FROM courseCatalog";
			stmt = conn.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery(sql);
			 ArrayList<Integer> registeredstudents = new  ArrayList<Integer>();
			 while(rs.next()){
		            //Display values
				 	int professorid=rs.getInt("professorId");
				 	
				 	if(professorid==professorId) {
				 		//System.out.println(rs.getInt("courseId"));
				 		
				 		int courseid=rs.getInt("courseId");
				 		PreparedStatement stmt1 = null;
						String sql1 = "SELECT * FROM studentRegisteredCourses";
						stmt1 = conn.prepareStatement(sql1);
						 ResultSet rs1 = stmt1.executeQuery(sql1);
						 while(rs1.next()) {
							 int courseid1=rs1.getInt("courseId1");
							 int courseid2=rs1.getInt("courseId2");
							 int courseid3=rs1.getInt("courseId3");
							 int courseid4=rs1.getInt("courseId4");
							// System.out.println(courseid1);
							 if(courseid1==courseid || courseid2==courseid || courseid3==courseid || courseid4==courseid) {
								 registeredstudents.add(rs1.getInt("studentId"));							 
							 }
						 }
						 
				 	}
		            
		         }
			 
			 	if(registeredstudents.size()==0) {
			 		System.out.println("No students enrolled");
			 	}
			 	else {
			 		for(int i=0;i<registeredstudents.size();i++) {
			 			System.out.println(registeredstudents.get(i));
			 		}
			 	}
			 	
			}
			catch(Exception e){
				
			}
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

}
