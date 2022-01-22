/**
 * 
 */
package com.crs.flipkart.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.crs.flipkart.bean.Course;
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
		ArrayList<Student> student = new ArrayList<Student>();
		for(int i=1;i<=20;i++)
		{
			Student s = new Student();
			s.setUsername("s"+i);
			s.setStudentId(i);
			student.add(s);
		}
		ArrayList<Course> course = new ArrayList<Course>();
		for(int i=1;i<=10;i++)
		{
			Course c = new Course();
			c.setCourseId(i);
			c.setName("c"+i);
			course.add(c);
		}
		Map<Integer,ArrayList<Course>> courseChoices = new HashMap<>();
		for(int i=0;i<20;i++)
		{
			Random random = new Random();  
			ArrayList<Course> tmp = new ArrayList<Course>();
			courseChoices.put(i+1, tmp);
			boolean flag[]=new boolean[10];
			for(int j=0;j<10;j++)
				flag[j] = false;
			for(int j=0;j<6;j++)
			{
				int x;
				while(true)
				{
					x = random.nextInt(10);
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
		/*System.out.println("Registered Course List");
		for(int i=0;i<registeredCoursesData.size();i++)
		{
			StudentRegisteredCourses tmp = registeredCoursesData.get(i);
			System.out.println(tmp.getCourseId() + " " + tmp.getStudentId());
		}*/
		admin.allocatePendingCourses();
		registeredCoursesData = StudentImplementation.viewRegisteredCourses();
	}

}
