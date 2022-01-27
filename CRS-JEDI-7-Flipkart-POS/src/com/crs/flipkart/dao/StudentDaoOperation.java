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
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.bean.StudentCourseChoice;
import com.crs.flipkart.constants.Gender;
import com.crs.flipkart.constants.SqlQueryConstants;

/**
 * @author HP
 *
 */
public class StudentDaoOperation implements StudentDaoInterface {

    private static StudentDaoOperation instance = null;
    private Connection conn = DBConnection.connectDB();

    private void StudentImplementation(){}

    public static StudentDaoOperation getInstance(){
        if(instance==null){
            synchronized (com.crs.flipkart.business.StudentImplementation.class){
                instance = new StudentDaoOperation();
            }
        }
        return instance;
    }
    
    @Override
	public boolean addStudentData(Student student) {
		try {
			PreparedStatement stmt = null;
			String sql = "INSERT INTO student(studentId,gradeCardVisibility) values(?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, student.getStudentId());
			stmt.setInt(2, student.getGradeCardVisibility()==true?1:0);
			
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
    public ArrayList<Student> viewAllStudents() {
        try {
            ArrayList<Student> list = new ArrayList<>();
            PreparedStatement stmt = null;
            String sql = "select * from student";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                Student student= new Student();
                student.setStudentId(rs.getInt("studentId"));
                student.setName(rs.getNString("name"));
                student.setAddress(rs.getNString("address"));
                student.setGender(Gender.stringToGender(rs.getNString("gender")));
                student.setContactNo(rs.getNString("contactNo"));
                student.setGradeCardVisibility(rs.getInt("gradeCardVisibility")==1);
                list.add(student);
            }
            return list;
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean activateGradeCard() {
        try {
            PreparedStatement stmt = null;
            String sql = "UPDATE student set gradeCardVisibility=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, 1);
            int rs = stmt.executeUpdate();
            if(rs!=0)
            	return true;
            return false;
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        }
        return false;
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
                break;
                //System.out.println(student.getStudentId()+" "+student.getName()+" "+student.getAddress()+" "+student.getGender()+" "+student.getGradeCardVisibility());
            }
            return student;

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        }
        return null;
    }
    
    @Override
	public void storeStudentCourseChoice(StudentCourseChoice studentCourseChoice) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement stmt = null;
			String sql = "INSERT INTO registrationDetails(studentId,courseId1,courseId2,courseId3,courseId4,courseId5,courseId6) values(?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, studentCourseChoice.getStudentId());
			stmt.setInt(2, studentCourseChoice.getCourses().get(0).getCourseId());
			stmt.setInt(3, studentCourseChoice.getCourses().get(1).getCourseId());
			stmt.setInt(4, studentCourseChoice.getCourses().get(2).getCourseId());
			stmt.setInt(5, studentCourseChoice.getCourses().get(3).getCourseId());
			stmt.setInt(6, studentCourseChoice.getCourses().get(4).getCourseId());
			stmt.setInt(7, studentCourseChoice.getCourses().get(5).getCourseId());
			
			int rs = stmt.executeUpdate();
		
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources // nothing we can do//end finally try
		}
		
	}

	@Override
	public ArrayList<Course> viewAllCourses() {
		try {
			ArrayList<Course> clist = new ArrayList<Course>();
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement(SqlQueryConstants.VIEW_ALLOCATED_COURSES_QUERY);
			 ResultSet rs = stmt.executeQuery(SqlQueryConstants.VIEW_ALLOCATED_COURSES_QUERY);
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
	public Boolean studentAlreadyRegistered(int studentId) {
		try {
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement("SELECT * FROM registrationDetails WHERE studentId = ?");
			stmt.setInt(1, studentId);
			 ResultSet rs = stmt.executeQuery();
			 while(rs.next()){
		            return true;
		         }
			 return false;
			}
			catch(Exception e){
				
			}
		return true;
	}

	@Override
	public void makePaymentSuccessful(int studentId) {
		try {

			String sql = "UPDATE registrationDetails SET paymentStatus = ? WHERE studentId = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "SUCCESS");
			stmt.setInt(2, studentId);
			stmt.executeUpdate();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources // nothing we can do//end finally try
		}
	}
	
	public Boolean update(String username,String oldPassword,String newPassword) {
        PreparedStatement stmt = null;

        try {

            {
                String sql = "SELECT * FROM user WHERE username = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                boolean ok = false;
                while (rs.next()) {
                    ok = true;
                    //System.out.println("Username exists");
                }
                if(!ok){
                    //System.out.println("Username Doesn't exist !");
                    return false;
                }
            }

            {
                String sql = "SELECT password FROM user WHERE username = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    String s = rs.getString("password");
                    if (!s.equals(oldPassword)) {
                        System.out.println("Wrong original Password");
                        return false;
                    }
                }
            }

            {
                String sql = "UPDATE user SET password = ? WHERE username = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, newPassword);
                stmt.setString(2, username);
                int sz = stmt.executeUpdate();
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

        return true;
    }

}