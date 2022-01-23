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
public class Main {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Welcome Admin!!!");
		System.out.println("Choose which operation you want to perform:-");
		System.out.println("0 : Quit\n1 : Add Course\n2 : Delete Course\n3 : Update Course\n4 : Activate Grade Card");
		System.out.println("5 : Add Professor Details\n6 : Delete Professor Details\n7 : Update Professor Details\n8 : Approve Student Registration");
		System.out.println("9 : View Students Data\n10: View Professors Data\n11: View All Courses\n12: Student Course Allocation\n13 : Generate Challan");
		AdminImplementation admin = new AdminImplementation();
		while(true)
		{
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter Choice Number: ");
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
							CourseImplementation.removeCourse(scanner.nextInt());
							
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
							for(Student student: studentData)
								System.out.println(student.getStudentId() + "\t\t\t" + student.getName());
							break;
						}
				case 10: {
							ArrayList<Professor> professorData = admin.viewAllProfessors();
							System.out.println("Professor Id\t\tProfessor Name");
							for(Professor professor: professorData)
								System.out.println(professor.getProfessorId() + "\t\t\t" + professor.getName());
							break;
						}
				case 11: {
							ArrayList<Course> courseData = admin.viewAllCourses();
							System.out.println("Course Id\t\tCourse Name");
							for(Course course: courseData)
								System.out.println(course.getCourseId() + "\t\t\t" + course.getName());
							break;
						}
				case 12: {    
							admin.registerCourses();
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
		/*final int nos = 10;
		final int noc = 40;
		ArrayList<Student> student = new ArrayList<Student>();
		for(int i=1;i<=nos;i++)
		{
			Student s = new Student();
			s.setUsername("s"+i);
			s.setStudentId(i);
			student.add(s);
		}
		ArrayList<Course> course = new ArrayList<Course>();
		for(int i=1;i<=noc;i++)
		{
			Course c = new Course();
			c.setCourseId(i);
			c.setName("c"+i);
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
		for(Map.Entry<Integer,ArrayList<Course>> entry: courseChoices.entrySet())
		{
			ArrayList<Course> t = entry.getValue();
			for(int i=0;i<t.size();i++)
			{
				Course tmp = t.get(i);
				//System.out.println(entry.getKey() + " "+tmp.getCourseId());
			}
		}
		AdminImplementation admin = new AdminImplementation();
		admin.registerCourses();
		ArrayList<StudentRegisteredCourses> registeredCoursesData = StudentImplementation.viewRegisteredCourses();
		System.out.println("Registered Course List");
		for(int i=0;i<registeredCoursesData.size();i++)
		{
			StudentRegisteredCourses tmp = registeredCoursesData.get(i);
			System.out.println(tmp.getCourseId() + " " + tmp.getStudentId());
		}
		admin.allocatePendingCourses();
		registeredCoursesData = StudentImplementation.viewRegisteredCourses();
		System.out.println("Registered Course List New");
		for(int i=0;i<registeredCoursesData.size();i++)
		{
			StudentRegisteredCourses tmp = registeredCoursesData.get(i);
			System.out.println(tmp.getCourseId() + " " + tmp.getStudentId());
		}*/
	}

}
