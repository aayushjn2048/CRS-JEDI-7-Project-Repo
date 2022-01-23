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
import com.crs.flipkart.business.StudentImplementation;

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
		System.out.println("1 : View Students Data\n2 : View Professors Data\n3 : View All Courses\n4 : Student Course Allocation\n5 : Generate Challan");
		AdminImplementation admin = new AdminImplementation();
		while(true)
		{
			Scanner scanner = new Scanner(System.in);
			int choice = scanner.nextInt();
			if(choice == 0)
				break;
			switch(choice)
			{
				case 1: ArrayList<Student> studentData = admin.viewAllStudents();
						System.out.println("Student Id\t\tStudent Name");
						for(Student student: studentData)
							System.out.println(student.getStudentId() + "\t\t\t" + student.getName());
						break;
				case 2: ArrayList<Professor> professorData = admin.viewAllProfessors();
						System.out.println("Professor Id\t\tProfessor Name");
						for(Professor professor: professorData)
							System.out.println(professor.getProfessorId() + "\t\t\t" + professor.getName());
						break;
				case 3: ArrayList<Course> courseData = admin.viewAllCourses();
						System.out.println("Course Id\t\tCourse Name");
						for(Course course: courseData)
							System.out.println(course.getCourseId() + "\t\t\t" + course.getName());
						break;
				case 4: admin.registerCourses();
						admin.allocatePendingCourses();
						break;
				case 5: SemesterRegistration semesterRegistration = new SemesterRegistration();
						Challan challan = admin.generateChallan(semesterRegistration);
						break;
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
