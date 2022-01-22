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
		for(int i=1;i<=40;i++)
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
		for(int i=0;i<40;i++)
		{
			Random random = new Random();  
			ArrayList<Course> tmp = new ArrayList<Course>();
			courseChoices.put(i+1, tmp);
			for(int j=0;j<6;j++)
			{
				int x = random.nextInt(10);
				courseChoices.get(i+1).add(course.get(x));
			}
		}
		
	}

}
