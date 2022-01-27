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
 *
 */
public class ProfessorImplementation {
	private static ArrayList<Professor> professorData = new ArrayList<Professor>();
	public static ArrayList<Professor> viewProfessorData()
	{
		return professorData;
	}
	
	public static void addProfessordata(Professor professor) {
		professorData.add(professor);
	}
	
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
	
	public void viewAvailableCourses() {
		ProfessorDaoInterface profdao = new ProfessorDaoOperation();
		ArrayList<Course> clist = profdao.viewAvailableCourses();
		 System.out.println("Serial No\tCourse Id\tCourse Name");
		 int count = 1;
		 for(Course c : clist)
		 {
			 System.out.println(count+"\t\t"+c.getCourseId()+"\t\t"+c.getName()+"\t\t");
			 count++;
		 }
	}
	
	public void viewEnrolledStudents(int professorid) {
		ProfessorDaoInterface professorDaoOperation = new ProfessorDaoOperation();
		Map<Integer,ArrayList<Student>> stulist = professorDaoOperation.viewEnrolledStudents(professorid);
		for(Map.Entry<Integer,ArrayList<Student>> entry: stulist.entrySet())
		{
			System.out.println("\nCourse Id: "+entry.getKey()+"\n");
			System.out.println("Student Id\t\tStudent Name\t\tContact No");
			for(Student st: entry.getValue())
				System.out.println(st.getStudentId()+"\t\t\t"+st.getName()+"\t\t\t"+st.getContactNo());
		}
	}
	
	public boolean selectCourse(int professorId, int courseId) {
		
		ProfessorDaoInterface professorDaoOperation = new ProfessorDaoOperation();
		return professorDaoOperation.selectCourse(professorId,courseId);
	}
	
	public void assignGrade(int studentId, int courseId, int semesterNumber) {
		ProfessorDaoInterface professorDaoOperation = new ProfessorDaoOperation();
		professorDaoOperation.assignGrade(studentId,courseId,semesterNumber);
	}
	
}
