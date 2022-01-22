/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.xml.crypto.Data;

import com.crs.flipkart.bean.Challan;
import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.SemesterRegistration;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.bean.StudentRegisteredCourses;

/**
 * @author HP
 *
 */
class Pair{
	int noOfStudents;
	int courseId;
	Pair(int x,int y)
	{
		noOfStudents = x;
		courseId = y;
	}
}

public class AdminImplementation {
	public ArrayList<Student> viewAllStudents(){
		return StudentImplementation.viewStudentData();
	}//3
	public ArrayList<Professor> viewAllProfessors(){
		return ProfessorImplementation.viewProfessorData();
	}
	public ArrayList<Course> viewAllCourses(){
		return CourseImplementation.viewCourseData();
	}
	public void allocatePendingCourses(){
		//Contains number of request made by students for particular course 
		Map<Integer,ArrayList<Course>> data = StudentImplementation.viewAllCourseChoices();
		int maxCoursesLeft = 0;
		//Contains number of students allocated to a particular course
		ArrayList<StudentRegisteredCourses> registeredData = StudentImplementation.viewRegisteredCourses();
		
		//Number of requests pending for a particular course
		Map<Integer,Integer> pendingCourseChoices = new HashMap<>();
		Map<Integer,ArrayList<Integer>> studentRegisteredCoursesNos = new HashMap<>();
		for(int i=0;i<registeredData.size();i++)
			studentRegisteredCoursesNos.get(registeredData.get(i).getStudentId()).add(registeredData.get(i).getCourseId());
		for(Map.Entry<Integer,ArrayList<Course>> entry: data.entrySet())
		{
			ArrayList<Course> tmp = entry.getValue();
			//For a particular student how many courses are left to be assigned
			int t = 4 - entry.getValue().size();
			if(t>maxCoursesLeft)
				maxCoursesLeft = t;
			for(int i=0;i<tmp.size();i++)
			{
				if(pendingCourseChoices.containsKey((tmp.get(i).getCourseId())))
					pendingCourseChoices.put(tmp.get(i).getCourseId(), pendingCourseChoices.get(tmp.get(i).getCourseId())+1);
				else
					pendingCourseChoices.put(tmp.get(i).getCourseId(),1);
			}
		}
		for(int i=0;i<registeredData.size();i++)
		{
			pendingCourseChoices.put(registeredData.get(i).getCourseId(), pendingCourseChoices.get(registeredData.get(i).getCourseId())-1);
		}
		
		ArrayList<Pair> tmp2 = new ArrayList<Pair>();
		int noOfRequestsPending = 0;
		for(Map.Entry<Integer,Integer> entry: pendingCourseChoices.entrySet())
		{
			tmp2.add(new Pair(entry.getValue(),entry.getKey()));
			noOfRequestsPending += entry.getValue();
		}
		Collections.sort(tmp2,(a,b)->(a.noOfStudents-b.noOfStudents));
		int noOfCoursesRequired = Math.max(maxCoursesLeft,(noOfRequestsPending-1)/10+1);
		ArrayList<Integer> topCoursesId = new ArrayList<Integer>();
		ArrayList<Integer> topRequestNos = new ArrayList<Integer>();
		int fixCourses = noOfRequestsPending/noOfCoursesRequired;
		for(int i=0;i<noOfCoursesRequired;i++)
		{
			topCoursesId.add(tmp2.get(i).courseId);
			topRequestNos.add(fixCourses);
		}
		if(noOfRequestsPending-fixCourses*noOfCoursesRequired>0)
			topRequestNos.set(0,topRequestNos.get(0)+noOfRequestsPending-fixCourses*noOfCoursesRequired);
		//Allocating top courses first to those students having in their optional choices
		for(Map.Entry<Integer,ArrayList<Course>> entry: data.entrySet())
		{
			ArrayList<Course> tmp = entry.getValue();
			for(int i=0;i<tmp.size();i++)
			{
				if(studentRegisteredCoursesNos.get(entry.getKey()).size() >= 4)
					break;
				if(topCoursesId.contains(tmp.get(i).getCourseId()))
				{
					StudentRegisteredCourses newCourse = new StudentRegisteredCourses();
					newCourse.setCourseId(tmp.get(i).getCourseId());
					newCourse.setStudentId(entry.getKey());
					registeredData.add(newCourse);
					studentRegisteredCoursesNos.get(entry.getKey()).add(tmp.get(i).getCourseId());
					noOfCoursesRequired--;
					int index = topCoursesId.indexOf(tmp.get(i).getCourseId());
					topRequestNos.set(index, topRequestNos.get(index)-1);
				}
			}
		}
		for(Map.Entry<Integer, ArrayList<Integer>> entry: studentRegisteredCoursesNos.entrySet())
		{
			for(int i=0;i<noOfCoursesRequired;i++)
			{
				if(entry.getValue().size() < 4)
				{
					if(!entry.getValue().contains(topCoursesId.get(i)))
					{
						StudentRegisteredCourses newCourse = new StudentRegisteredCourses();
						newCourse.setCourseId(topCoursesId.get(i));
						newCourse.setStudentId(entry.getKey());
						registeredData.add(newCourse);
						studentRegisteredCoursesNos.get(entry.getKey()).add(topCoursesId.get(i));
					}
				}
				else
					break;
			}
		}
		StudentImplementation.updateRegisteredCourses(registeredData);
	}
	
	//public Challan generateChallan(SemesterRegistration semesterRegistration);
}
