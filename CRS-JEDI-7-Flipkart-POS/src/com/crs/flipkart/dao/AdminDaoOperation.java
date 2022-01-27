/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.constants.SqlQueryConstants;

/**
 * @author HP
 *
 */
public class AdminDaoOperation implements AdminDaoInterface {
	
	Connection conn = DBConnection.connectDB();

	@Override
	public Boolean addCourse(Course course) {
		PreparedStatement stmt = null;
		//add the course to 'course' table
		try {

			stmt = conn.prepareStatement(SqlQueryConstants.ADD_COURSE_QUERY);
			stmt.setInt(1, course.getCourseId());
			stmt.setString(2, course.getName());
			stmt.setInt(3,course.getCourseFee());
			int rs = stmt.executeUpdate();
			if (rs == 0)
				return false;
			return true;
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources // nothing we can do//end finally try
		}

		//add (catalogId,courseId) to catalog table
		try {
			
			stmt = conn.prepareStatement(SqlQueryConstants.ADD_COURSE_TO_CATALOG_QUERY);
			stmt.setInt(1, course.getCatalogId());
			stmt.setInt(2, course.getCourseId());
			int rs = stmt.executeUpdate();
			if (rs == 0)
				return false;
			return true;
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources // nothing we can do//end finally try
		}


		return false;
	}

	@Override
	public Boolean deleteCourse(int courseId) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement(SqlQueryConstants.DELETE_COURSE_QUERY);
			stmt.setInt(1, courseId);		//we can only pass string as argument in setString
			int rs = stmt.executeUpdate();
			if (rs == 0)
				return false;
			return true;
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources // nothing we can do//end finally try
		}
		return false;
		
	}

	@Override
	//Considers the fact that courseId cannot be changed
	public Boolean updateCourse(Course course) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement stmt = null;
			String sql = "UPDATE course SET ";
			if(course.getName()!=null)
				sql += " name = ?,";
			if(course.getCourseFee()!=-1)
				sql += " courseFee = ?,";
			if(course.getProfessorId() !=-1)
				sql += " professorId = ?,";
			sql = sql.substring(0, sql.length() - 1);
			sql += " WHERE courseId = ?;";
			stmt = conn.prepareStatement(sql);
			int count = 1;
			if(course.getName()!=null)
			{
				stmt.setString(count, course.getName());
				count++;
			}
			if(course.getCourseFee()!=-1)
			{
				stmt.setInt(count, course.getCourseFee());
				count++;
			}
			if(course.getProfessorId() !=-1)
			{
				stmt.setInt(count, course.getProfessorId());
				count++;
			}
			stmt.setInt(count, course.getCourseId());
			int rs = stmt.executeUpdate();
			if (rs == 0)
				return false;
			return true;
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources // nothing we can do//end finally try
		}
		return false;
	}
	
	@Override
	public void viewAllProfessor()  {
		try {
			 
		PreparedStatement stmt = null;
		stmt = conn.prepareStatement(SqlQueryConstants.VIEW_PROFESSORS_QUERY);
		 ResultSet rs = stmt.executeQuery();
		 while(rs.next()){
	            //Display values
	            System.out.print("ProfessorId: " + rs.getInt("professorId")+" ");
	            System.out.print("Professor Name: " + rs.getString("name"));
				System.out.print("Professor Address: " + rs.getString("address"));
				System.out.print("Professor Gender: " + rs.getString("gender"));
				System.out.print("Professor Contact No: " + rs.getString("contactNo"));
				System.out.print("Professor Designation: " + rs.getString("designation"));
	            System.out.println("\n");
	         }
		}
		catch(Exception e){
			
		}
		
		
		
	}

	@Override
	public ArrayList<Course> viewAllCourses() {
		
		try {
			ArrayList<Course> clist = new ArrayList<Course>();
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement(SqlQueryConstants.VIEW_COURSES_QUERY);
			 ResultSet rs = stmt.executeQuery(SqlQueryConstants.VIEW_COURSES_QUERY);
			 while(rs.next()){
		            //Display values
				 	Course c = new Course();
				 	c.setCourseId(rs.getInt("courseId"));
				 	c.setName(rs.getString("name"));
				 	c.setProfessorId(rs.getInt("professorId"));	//handle if no professor is yet alloted
					c.setCourseFee(rs.getInt("courseFee"));
				 	clist.add(c);
		         }
			 return clist;
			}
			catch(Exception e){
				
			}
		return null;
	}
	
	@Override
	public boolean studentSelfRegistration(int studentId) {
		// TODO Auto-generated method stub
		
		try {
			PreparedStatement stmt = null;
			String sql = "SELECT * FROM studentdetails";
			stmt = conn.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery(sql);
			 while(rs.next()){
		           int id=rs.getInt("studentid");
		           if(id==studentId) {
		        	   return true;
		        	   
		           }
		           
		        }
			 return false;
			 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
			}
		
		return false;
	}

	@Override
	public Boolean addProfessor(Professor professor) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		int val;
		// As per current schema this whole query is supposed to be run as a Transaction , but right now there are no concurrent queries and assumng connection doesn't break so left it simple.
		try {

			//Checting if username is already taken or not
			{
				String sql = "SELECT * FROM user WHERE username = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1,professor.getUsername());
				ResultSet rs = stmt.executeQuery();
				while(rs.next())
				{
					System.out.println("Username already Taken");
					return false;
				}
			}

			//adding user
			{
				stmt = conn.prepareStatement(SqlQueryConstants.ADD_USER_QUERY);
				stmt.setInt(1, professor.getUserId());
				stmt.setString(2, professor.getUsername());
				stmt.setString(3, professor.getPasswordHash());
				int rs = stmt.executeUpdate();
				if (rs == 0)
					return false;
			}

			//adding role
			{
				stmt = conn.prepareStatement(SqlQueryConstants.ADD_ROLE_QUERY);
				stmt.setInt(1, professor.getUserId());
				stmt.setString(2,"PROFESSOR");
				int rs = stmt.executeUpdate();
				if (rs == 0)
					return false;
			}

			//adding professor
			{
				stmt = conn.prepareStatement(SqlQueryConstants.ADD_PROFESSOR_QUERY);
				stmt.setInt(1, professor.getUserId());
				stmt.setString(2, professor.getName());
				stmt.setString(3, professor.getAddress());
				stmt.setString(4, professor.getGender().toString());
				stmt.setString(5, professor.getContactNo());
				stmt.setString(6, professor.getDesignation().toString());
				int rs = stmt.executeUpdate();
				if (rs == 0)
					return false;
			}
			return true;

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
			return false;
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
			return false;
		} finally {
			// finally block used to close resources // nothing we can do//end finally try
		}
	}

	@Override
	public Boolean removeProfessor(int professorId) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		try {

			stmt = conn.prepareStatement(SqlQueryConstants.DELETE_PROFESSOR_QUERY);
			stmt.setInt(1, professorId);		
			int rs = stmt.executeUpdate();
			if (rs == 0)
				return false;

			stmt = conn.prepareStatement(SqlQueryConstants.DELETE_USER_QUERY);
			stmt.setInt(1, professorId);		
			rs = stmt.executeUpdate();
			if (rs == 0)
				return false;
			return true;
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources // nothing we can do//end finally try
		}
		return false;
		
	}

	@Override
	public Boolean updateProfessor(Professor professorOld,Professor professorNew) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		try {
			String sql = "UPDATE user SET username = ? ,password = ? ,name = ? ,address = ? ,contactNo = ? WHERE userId = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, professorNew.getUsername());
			stmt.setString(2, professorNew.getPasswordHash());
			stmt.setString(3, professorNew.getName());
			stmt.setString(4, professorNew.getAddress());
			stmt.setString(5, professorNew.getContactNo());
			stmt.setInt(6, professorOld.getProfessorId());
			int rs = stmt.executeUpdate();
			if (rs == 0)
				return false;
			
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
			return false;
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
			return false;
		} finally {
			// finally block used to close resources // nothing we can do//end finally try
		}

		/* We are not taking change in Designation for now
		if(!professorOld.getDesignation().equals(professorNew.getDesignation())) {
			
			try {
				String sql = "UPDATE professor SET designation = ? WHERE professorId = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, professorNew.getDesignation().toString());
				stmt.setInt(2, professorOld.getProfessorId());
				int rs = stmt.executeUpdate();
				if (rs == 0)
					return false;
				return true;
				
			} catch (SQLException se) {
				// Handle errors for JDBC
				se.printStackTrace();
				return false;
			} catch (Exception e) {
				// Handle errors for Class.forName
				e.printStackTrace();
				return false;
			} finally {
				// finally block used to close resources // nothing we can do//end finally try
			}
			
		}
		 */
		
		return true;
		
	}

	@Override
	public ArrayList<Course> getAllCourseDetails() {
		ArrayList<Course> courseDetails = new ArrayList<Course>();
		try {
			PreparedStatement stmt = null;
			String sql = "SELECT * FROM courseCatalog";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				Course c = new Course();
				c.setCourseId(rs.getInt("courseId"));
				c.setName(rs.getString("name"));
				courseDetails.add(c);
			}
			return courseDetails;
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources // nothing we can do//end finally try
		}
		return null;
	}

	@Override
	public Map<Integer, ArrayList<Course>> getAllCourseChoices() {
		Map<Integer, ArrayList<Course>> mp = new HashMap<>();
		try {
			ArrayList<Course> courseDetails = this.getAllCourseDetails();
			PreparedStatement stmt = null;
			Map<Integer,Course> courseDetailer = new HashMap<>();
			for(Course c: courseDetails)
				courseDetailer.put(c.getCourseId(), c);
			stmt = conn.prepareStatement(SqlQueryConstants.VIEW_COURSES_QUERY);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				int studentId = rs.getInt("studentId");
				ArrayList<Course> tmp = new ArrayList<Course>();
				for(int i=1;i<=6;i++)
					tmp.add(courseDetailer.get(rs.getInt("courseId"+i)));
				mp.put(studentId, tmp);
			}
			return mp;
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources // nothing we can do//end finally try
		}
		return null;
	}

	@Override
	public Boolean addStudentCourseChoices(int studentId, ArrayList<Integer> courseList) {
		//include paymentStatus and payment number in this as well
		try {
			PreparedStatement stmt = null;
			String sql = "INSERT INTO studentCourseChoices values(?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, studentId);
			for(int i=2;i<=7;i++)
				stmt.setInt(i, courseList.get(i-2));
			int rs = stmt.executeUpdate();
			if (rs == 0)
				return false;
			return true;
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources // nothing we can do//end finally try
		}
		return false;
	}

	@Override
	public Boolean updateAllocatedStudentCourses(Map<Integer, ArrayList<Integer>> list) {
		// TODO Auto-generated method stub
		try {
			for(Map.Entry<Integer,ArrayList<Integer>> entry: list.entrySet())
			{
				String sql = "INSERT INTO studentRegisteredDetails values (?,?,?,?,?,?) ";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, entry.getKey());
				stmt.setInt(2, entry.getKey());
				ArrayList<Integer> tmp = entry.getValue();
				for(int i=0;i<tmp.size();i++)
					stmt.setInt(i+3, tmp.get(i));
				int rs = stmt.executeUpdate();
				if (rs == 0)
					return false;
			}
			return true;
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources // nothing we can do//end finally try
		}
		return false;
	}
	

}
