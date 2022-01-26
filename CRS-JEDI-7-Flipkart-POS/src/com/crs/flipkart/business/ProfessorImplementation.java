/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;

import com.crs.flipkart.bean.Professor;
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
	
	public void viewAllCourses() {
		AdminDaoInterface admindaooperation = new AdminDaoOperation();
		 admindaooperation.viewAllCourses();
	}
	
	public void viewEnrolledStudents(int professorid) {
		ProfessorDaoInterface professorDaoOperation = new ProfessorDaoOperation();
		professorDaoOperation.viewEnrolledStudents(professorid);
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
