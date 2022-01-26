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

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Professor;

/**
 * @author HP
 *
 */
public class AdminDaoOperation implements AdminDaoInterface {
	
	Connection conn = DBConnection.connectDB();

	@Override
	public Boolean addCourse(Course course) {

		try {
			PreparedStatement stmt = null;
			String sql = "INSERT INTO courseCatalog(name,offeredSemester) values(?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, course.getName());
			stmt.setInt(2, course.getOfferedSemester());
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
			String sql = "DELETE FROM courseCatalog WHERE courseId = ?";
			stmt = conn.prepareStatement(sql);
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
	public Boolean updateCourse(Course courseOld,Course courseNew) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement stmt = null;
			String sql = "UPDATE courseCatalog SET name = ? , offeredSemester = ? , professorId = ? WHERE courseId = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, courseNew.getName());
			stmt.setInt(2, courseNew.getOfferedSemester());
			stmt.setInt(3, courseNew.getProfessorId());
			stmt.setInt(4, courseOld.getCourseId());
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
		String sql = "SELECT * FROM professor";
		stmt = conn.prepareStatement(sql);
		 ResultSet rs = stmt.executeQuery(sql);
		 while(rs.next()){
	            //Display values
	            System.out.print("ProfessorId: " + rs.getInt("professorId")+" ");
	            System.out.print("Professor Designation: " + rs.getString("designation"));
	            System.out.println("\n");
	         }
		}
		catch(Exception e){
			
		}
		
		
		
	}

	@Override
	public void viewAllCourses() {
		
		try {
			 
			PreparedStatement stmt = null;
			String sql = "SELECT * FROM courseCatalog";
			stmt = conn.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery(sql);
			 while(rs.next()){
		            //Display values
		            System.out.print("CourseId: " + rs.getInt("courseId")+" ");
		            System.out.print("Course Name: " + rs.getString("name"));
		            System.out.println("\n");
		         }
			}
			catch(Exception e){
				
			}
		
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
		// As per current schema this whole query is supposed to be run as a Transaction , but right now there are no concurrent queries so left it simple.
		try {
			String sql = "INSERT INTO user(username,password,name,address,gender,contactNo,role) values(?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, professor.getUsername());
			stmt.setString(2, professor.getPasswordHash());
			stmt.setString(3, professor.getName());
			stmt.setString(4, professor.getAddress());
			stmt.setString(5, professor.getGender().toString());
			stmt.setString(6, professor.getContactNo());
			stmt.setString(7, "Professor");
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
		
		
		
		//get the id of this professor from the DB
		int profId = 0;
		try {
			String sql = "SELECT userId FROM user WHERE username = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,professor.getUsername());
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				profId = rs.getInt(1);
			}
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

		
		//add this professor and his designation to the professor table

		try {
			String sql = "INSERT INTO professor(professorId,designation) values(?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,profId);
			//stmt.setString(2, professor.getDesignation().toString());
			stmt.setString(2,"Associate Professor");	//for now the value is hardcoded , change it once Designation enum is implemented
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

	@Override
	public Boolean removeProfessor(int professorId) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		try {
			//Should we remove this professor from user as well or will he still be the user ?
			String sql = "DELETE FROM professor WHERE professorId = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, professorId);		
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
			String sql = "SELECT * FROM studentCourseChoices";
			Map<Integer,Course> courseDetailer = new HashMap<>();
			for(Course c: courseDetails)
				courseDetailer.put(c.getCourseId(), c);
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			
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
				String sql = "INSERT INTO studentRegisteredCourses values (?,?,?,?,?,?) ";
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
