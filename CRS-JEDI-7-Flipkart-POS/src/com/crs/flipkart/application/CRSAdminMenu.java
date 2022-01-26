/**
 * 
 */
package com.crs.flipkart.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import com.crs.flipkart.bean.Challan;
import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.SemesterRegistration;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.bean.StudentRegisteredCourses;
import com.crs.flipkart.business.AdminImplementation;
import com.crs.flipkart.business.CourseImplementation;
import com.crs.flipkart.business.CourseInterface;
import com.crs.flipkart.business.ProfessorImplementation;
import com.crs.flipkart.business.StudentImplementation;
import com.crs.flipkart.constants.Designation;
import com.crs.flipkart.constants.Gender;
import com.crs.flipkart.constants.Role;
import com.crs.flipkart.dao.AdminDaoOperation;

/**
 * @author HP
 *
 */
public class CRSAdminMenu {

	/**
	 * @param args
	 */
	CourseInterface courseImplementation = CourseImplementation.getInstance();
	StudentImplementation studentImplementation = StudentImplementation.getInstance();
	
	public void adminMenuMain() {
		// TODO Auto-generated method stub

		final int nos = 14;
		final int noc = 10;
		ArrayList<Student> student = new ArrayList<Student>();
		for(int i=1;i<=nos;i++)
		{
			Student s = new Student();
			s.setUsername("s"+i);
			s.setStudentId(i);
			s.setName("Student"+i);
			student.add(s);
		}
		ArrayList<Course> course = new ArrayList<Course>();
		for(int i=1;i<=noc;i++)
		{
			Course c = new Course();
			c.setCourseId(i);
			c.setName("Course"+i);
			course.add(c);
		}
		Map<Integer,ArrayList<Course>> courseChoices = new HashMap<>();
		for(int i=0;i<nos;i++)
		{
			Random random = new Random();  
			ArrayList<Course> tmp = new ArrayList<Course>();
			ArrayList<Integer> tmp2 = new ArrayList<Integer>();
			
			courseChoices.put(i+1, tmp);
			boolean flag[]=new boolean[noc];
			for(int j=0;j<noc;j++)
				flag[j] = false;
			for(int j=0;j<6;j++)
			{
				int x;
				while(true)
				{
					x = random.nextInt(noc);
					if(flag[x]==false)
					{
						flag[x] = true;
						break;
					}
				}
				courseChoices.get(i+1).add(course.get(x));
				tmp2.add(x+4);
			}
			//AdminDaoOperation admindao = new AdminDaoOperation();
			//admindao.addStudentCourseChoices(i+1, tmp2);
		}
		System.out.println("\n----------------!!Welcome Admin!!----------------\n");
		System.out.println("Choose an option:-");
		System.out.println("--------------------------------------------------------------");
		System.out.println("\t1 : Add Course\n\t2 : Delete Course\n\t3 : Update Course\n\t4 : Activate Grade Card");
		System.out.println("\t5 : Add Professor Details\n\t6 : Delete Professor Details\n\t7 : Update Professor Details\n\t8 : Approve Student Registration");
		System.out.println("\t9 : View Students Data\n\t10: View Professors Data\n\t11: View All Courses\n\t12: Student Course Allocation");
		System.out.println("==============================================================");
		AdminImplementation admin = new AdminImplementation();
		while(true)
		{
			Scanner scanner = new Scanner(System.in);
			System.out.print("\nEnter Choice Number: ");
			int choice = scanner.nextInt();
			if(choice == 0)
				break;
			switch(choice)
			{
				case 1: {
							Course newCourse = new Course();							
							System.out.print("Enter course name: ");
							newCourse.setName(scanner.next());
							
							System.out.print("Enter Offered Semester: ");
							newCourse.setOfferedSemester(scanner.nextInt());
							
							System.out.print("Enter Professor Id: ");
							newCourse.setProfessorId(scanner.nextInt());
							
							courseImplementation.addCourse(newCourse);
							break;
						}
				case 2: {
							System.out.print("Enter CourseId: ");
							if(courseImplementation.removeCourse(scanner.nextInt()))
								System.out.println("Course details deleted from the database");
							else
								System.out.println("Course with entered courseId does not exist");
							break;
						}
				case 3: {
							
							Course newCourse1 = new Course();
							System.out.print("Enter Course Id: ");
							newCourse1.setCourseId(scanner.nextInt());
							System.out.println("Note: Enter NA if you want to keep the details same.");
							String s;
							System.out.print("Enter new course name: ");
							s = scanner.next();
							if(!s.equals("NA"))
								newCourse1.setName(s);
							
							System.out.print("Enter new offered Semester: ");
							s = scanner.next();
							if(!s.equals("NA"))
								newCourse1.setOfferedSemester(Integer.parseInt(s));
							
							System.out.print("Enter new Professor Id: ");
							s = scanner.next();
							if(!s.equals("NA"))
								newCourse1.setProfessorId(Integer.parseInt(s));
							
							courseImplementation.updateCourse(newCourse1);
							
							break;
						}
				case 4: {
							AdminImplementation adminImplementation = new AdminImplementation();
							adminImplementation.activateGradeCard();
							break;
					    }
				case 5: {
							Professor professor = new Professor();
							System.out.print("Enter username: ");
							String username = scanner.next();
							professor.setUsername(username);
							System.out.print("Enter password: ");
							String passwordHash = scanner.next();
							professor.setPasswordHash(passwordHash);
							System.out.print("Enter name: ");
							String name = scanner.next();
							professor.setName(name);
							System.out.print("Enter address: ");
							String address = scanner.next();
							professor.setAddress(address);
							System.out.print("Enter gender('MALE'/'FEMALE'/'OTHERS'): ");
							String gender = scanner.next();	//Input MALE / FEMALE / OTHER all in capitals
							professor.setGender(Gender.valueOf(gender));
							System.out.print("Enter contact number: ");
							String contactNo = scanner.next();
							professor.setContactNo(contactNo);
							System.out.print("Enter designation('ASSOCIATE'/'ASSISTANT'/'PROFESSOR'): ");		//this is not role , role will always be professor , this is designation
							String designation = scanner.next();
							professor.setDesignation(Designation.valueOf(designation)); //Designation enum is not yet implemented , so uncomment it after imiplementation
							String response = admin.addProfessor(professor);
							System.out.println(response);
							break;
						}
				//for deleting professor
				case 6: {
							System.out.print("Enter ProfessorId: ");
							int ProfessorId = scanner.nextInt();
							String response = admin.removeProfessor(ProfessorId);
							System.out.println(response);
							break;
						}
				case 7: {
							Professor professor = new Professor();
							System.out.print("Enter username: ");
							String username = scanner.next();
							professor.setUsername(username);
							System.out.print("Enter password: ");
							String passwordHash = scanner.next();
							professor.setPasswordHash(passwordHash);
							System.out.print("Enter name: ");
							String name = scanner.next();
							professor.setName(name);
							System.out.print("Enter address: ");
							String address = scanner.next();
							professor.setAddress(address);
							System.out.print("Enter contact number: ");
							String contactNo = scanner.next();
							professor.setContactNo(contactNo);
							System.out.print("Enter role: ");
							String role = scanner.next();
							professor.setRole(Role.stringToName(role));
							System.out.print("Enter ProfessorId: ");
							int ProfessorId = scanner.nextInt();
							professor.setProfessorId(ProfessorId);
							String response = admin.updateProfessor(professor);
							System.out.println(response);
							break;
						}
				case 8: {
							System.out.print("Enter StudentId: ");
							int studentId = scanner.nextInt();
							System.out.println(admin.approveStudentRegistration(studentImplementation.viewStudentDetails(studentId).getStudentId()));
							break;
						}
				case 9: {
							ArrayList<Student> studentData = admin.viewAllStudents();
							System.out.println("Student Id\t\tStudent Name");
							for(Student stu: studentData)
								System.out.println(stu.getStudentId() + "\t\t\t" + stu.getName());
							break;
						}
				case 10: {
							admin.viewAllProfessors();
							break;
						}
				case 11: {
							admin.viewAllCourses();
							break;
						}
				case 12: {    
							admin.allocatePendingCourses();
							break;
						}
				case 13: {
							SemesterRegistration semesterRegistration = new SemesterRegistration();
							Challan challan = admin.generateChallan(semesterRegistration);
							break;
						}
				default: System.out.println("Invalid Input");
			}
		}
	}

}
