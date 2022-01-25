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
import com.crs.flipkart.business.ProfessorImplementation;
import com.crs.flipkart.business.StudentImplementation;
import com.crs.flipkart.constants.Role;

/**
 * @author HP
 *
 */
public class CRSAdminMenu {

	/**
	 * @param args
	 */
	
	
	public void adminMenuMain() {
		// TODO Auto-generated method stub

		final int nos = 12;
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
			}
		}
		StudentImplementation.updateStudentData(student);
		CourseImplementation.updateCourseData(course);
		StudentImplementation.updateCourseChoices(courseChoices);
		System.out.println("\n----------------!!Welcome Admin!!----------------\n");
		System.out.println("Choose which operation you want to perform:-");
		System.out.println("\t1 : Add Course\n\t2 : Delete Course\n\t3 : Update Course\n\t4 : Activate Grade Card");
		System.out.println("\t5 : Add Professor Details\n\t6 : Delete Professor Details\n\t7 : Update Professor Details\n\t8 : Approve Student Registration");
		System.out.println("\t9 : View Students Data\n\t10: View Professors Data\n\t11: View All Courses\n\t12: Student Course Allocation");
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
							System.out.print("Enter CourseId: ");
							newCourse.setCourseId(scanner.nextInt());
							
							System.out.print("Enter course name: ");
							newCourse.setName(scanner.next());
							
							System.out.print("Enter Offered Semester: ");
							newCourse.setOfferedSemester(scanner.nextInt());
							
							System.out.print("Enter Professor Id: ");
							newCourse.setProfessorId(scanner.nextInt());
							
							CourseImplementation.addCourse(newCourse);
							break;
						}
				case 2: {
							System.out.print("Enter CourseId: ");
							if(CourseImplementation.removeCourse(scanner.nextInt()))
								System.out.println("Course details deleted from the database");
							else
								System.out.println("Course with entered courseId does not exist");
							break;
						}
				case 3: {
							
							Course newCourse1 = new Course();
							System.out.print("Enter new CourseId: ");
							newCourse1.setCourseId(scanner.nextInt());
							
							System.out.print("Enter new course name: ");
							newCourse1.setName(scanner.next());
							
							System.out.print("Enter new offered Semester: ");
							newCourse1.setOfferedSemester(scanner.nextInt());
							
							System.out.print("Enter new Professor Id: ");
							newCourse1.setProfessorId(scanner.nextInt());
							
							CourseImplementation.updateCourse(newCourse1);
							
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
							System.out.print("Enter contact number: ");
							String contactNo = scanner.next();
							professor.setContactNo(contactNo);
							System.out.print("Enter role: ");
							String role = scanner.next();
							professor.setRole(Role.stringToName(role));
							System.out.print("Enter ProfessorId: ");
							int ProfessorId = scanner.nextInt();
							professor.setProfessorId(ProfessorId);
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
							System.out.println(admin.approveStudentRegistration(StudentImplementation.viewStudentDetails(studentId)));
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
							ArrayList<Professor> professorData = admin.viewAllProfessors();
							System.out.println("Professor Id\t\tProfessor Name");
							for(Professor prof: professorData)
								System.out.println(prof.getProfessorId() + "\t\t\t" + prof.getName());
							break;
						}
				case 11: {
							ArrayList<Course> courseData = admin.viewAllCourses();
							System.out.println("Course Id\t\tCourse Name");
							for(Course c: courseData)
								System.out.println(c.getCourseId() + "\t\t\t" + c.getName());
							break;
						}
				case 12: {    
							admin.registerCourses();
							admin.allocatePendingCourses();
							Map<Integer,ArrayList<Integer>> registeredCourseChoices = StudentImplementation.viewRegisteredCourseChoices();
							System.out.println("Student Id\t\tCourse Ids");
							for(Map.Entry<Integer, ArrayList<Integer>> entry: registeredCourseChoices.entrySet())
							{
								System.out.print(entry.getKey()+"\t\t\t");
								for(Integer tmp: entry.getValue())
									System.out.print(tmp + ", ");
								System.out.println("");
							}
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
