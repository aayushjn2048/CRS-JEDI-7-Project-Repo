/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;
import java.util.Map;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.dao.AdminDaoInterface;
import com.crs.flipkart.dao.AdminDaoOperation;
import com.crs.flipkart.dao.ProfessorDaoInterface;
import com.crs.flipkart.dao.ProfessorDaoOperation;

/**
 * @author HP
 * ProfessorImplementation class
 * 
 */
public class ProfessorImplementation {
	private static ArrayList<Professor> professorData = new ArrayList<Professor>();
	
	/**
	 * Method to view the professor data
	 * @return the list of professors
	 */
	public static ArrayList<Professor> viewProfessorData()
	{
		return professorData;
	}
	
	/**
	 * Method to add the professor data
	 * @param professor object
	 */
	public static void addProfessordata(Professor professor) {
		professorData.add(professor);
	}
	
	/**
	 * Method to remove the professor data
	 * @param professor id
	 * @return boolean data 
	 */
	public static boolean removeProfessordata(int professorId) {
		int pos=0;
		
		for(Professor professor: professorData) {
			if(professor.getProfessorId()==professorId) {
				professorData.remove(pos);
				return true;
			}
			pos++;
		}
		return false;
	}
	
	/**
	 * Method to update the professor data
	 * @param professor object
	 */
	public static void updateProfessordata(Professor professor) {
		int pos=0;
		for(Professor prof: professorData) {
			if(prof.getProfessorId()==professor.getProfessorId()) {
				professorData.remove(pos);
				professorData.add(professor);
				break;
			}
			pos++;
		}
	}
	
	/**
	 * Method to view all the available courses
	 */
	public void viewAvailableCourses() {
		ProfessorDaoInterface profdao = new ProfessorDaoOperation();
		ArrayList<Course> clist = profdao.viewAvailableCourses();
		 System.out.println("Serial No\tCourse Id\tCourse Name");
		 int count = 1;
		 for(Course c : clist)
		 {
			 System.out.println(count+"\t\t"+c.getCourseId()+"\t\t"+c.getName());
			 count++;
		 }
	}
	
	/**
	 * Method to view list of enrolled Students
	 * @param: professorId the id of professor
	 */
	public void viewEnrolledStudents(int professorid) {
		ProfessorDaoInterface professorDaoOperation = new ProfessorDaoOperation();
		Map<Integer,ArrayList<Student>> stulist = professorDaoOperation.viewEnrolledStudents(professorid);
		if(stulist==null) {
			System.out.println("Cannot fetch data");
			return ;
		}
		for(Map.Entry<Integer,ArrayList<Student>> entry: stulist.entrySet())
		{
			System.out.println("\nCourse Id: "+entry.getKey()+"\n");
			if(entry.getValue().size()==0)
			{
				System.out.println("No students enrolled");
				continue;
			}
			System.out.println("Student Id\t\tStudent Name\t\tContact No");
			entry.getValue().forEach((st) ->{
				System.out.println(st.getStudentId()+"\t\t\t"+st.getName()+"\t\t\t"+st.getContactNo());
			});
		}
	}
	
	/**
	 * Method to get Courses by Professor Id
	 * @param professorId the id of professor
	 * @param courseId the id of course
	 * @return boolean data whether offered to the professor or not.
	 */
	public boolean selectCourse(int professorId, int courseId) {
		
		ProfessorDaoInterface professorDaoOperation = new ProfessorDaoOperation();
		return professorDaoOperation.selectCourse(professorId,courseId);
	}
	
	/**
	 * Assign grades to student
	 * @param studentId the student id
	 * @param courseId the course id
     * @param grade the grade to be assigned
	 */
	public void assignGrade(int studentId, int courseId, float grade) {
		ProfessorDaoInterface professorDaoOperation = new ProfessorDaoOperation();
		professorDaoOperation.assignGrade(studentId,courseId,grade);
	}
	
}
