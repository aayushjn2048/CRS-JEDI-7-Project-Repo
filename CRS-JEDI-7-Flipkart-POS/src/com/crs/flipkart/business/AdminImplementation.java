/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// import javax.xml.crypto.Data;

import com.crs.flipkart.bean.Challan;
import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.PaymentReference;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.SemesterRegistration;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.bean.StudentRegisteredCourses;
import com.crs.flipkart.constants.PaymentStatus;
import com.crs.flipkart.dao.AdminDaoInterface;
import com.crs.flipkart.dao.AdminDaoOperation;
import com.crs.flipkart.dao.PaymentsDaoImplementation;
import com.crs.flipkart.dao.PaymentsDaoInterface;
import com.crs.flipkart.dao.StudentDaoInterface;
import com.crs.flipkart.dao.StudentDaoOperation;

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

public class AdminImplementation implements AdminInterface{
	private PaymentsDaoInterface paymentsDaoImplementation = PaymentsDaoImplementation.getInstance();
	//Group 1
	public void activateGradeCard(){
		StudentDaoInterface studentDaoImplementation = StudentDaoOperation.getInstance();
		try {
			studentDaoImplementation.activateGradeCard();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	//Group 2
	public String addProfessor(Professor professor) {
		
		//ProfessorImplementation.addProfessor data(professor);
		AdminDaoInterface admin = new AdminDaoOperation();
		if(admin.addProfessor(professor))
			return "Professor is successfully created";
		else
			return "Professor is not created";
	}
	
	public String removeProfessor(int professorId) {

		AdminDaoInterface admin = new AdminDaoOperation();
		if(admin.removeProfessor(professorId)){
		return "Professor is succesfully removed";
		}
		else {
			return "Professor is not removed. Please enter valid professor id";
		}
	}
	
	public String updateProfessor(Professor professor) {
		

		AdminDaoInterface admindao = new AdminDaoOperation();
		if(admindao.updateProfessor(professor,professor)){	//Ultimately we should update only the details that were newly changed
			return "Professor is updated";

		}
		else{
			return "Professor not updated";
		}
		
	}
	
	public Boolean approveStudentRegistration(int studentId) {
		AdminDaoInterface admindaooperation = new AdminDaoOperation();
		 return(admindaooperation.studentSelfRegistration(studentId));
	}
	
	//Group 3
	public ArrayList<Student> viewAllStudents(){
		StudentInterface studentImplementation = StudentImplementation.getInstance();
		return studentImplementation.viewStudentData();
	}//3
	public void viewAllProfessors(){
		AdminDaoInterface admindaooperation = new AdminDaoOperation();
		 admindaooperation.viewAllProfessor();
	}
	public void viewAllCourses(){
		 AdminDaoInterface admindaooperation = new AdminDaoOperation();
		 ArrayList<Course> clist = admindaooperation.viewAllCourses();
		 System.out.println("Serial No\tCourse Id\tCourse Name");
		 int count = 1;
		 for(Course c : clist)
		 {
			 System.out.println(count+"\t\t"+c.getCourseId()+"\t\t"+c.getName());
			 count++;
		 }
	}

	public void allocatePendingCourses(){
		//Contains number of request made by students for particular course 
		ArrayList<StudentRegisteredCourses> registeredCourses = new ArrayList<StudentRegisteredCourses>();
		AdminDaoInterface admindao = new AdminDaoOperation();
		Map<Integer,ArrayList<Course>> data = admindao.getAllCourseChoices();
		Map<Integer,Integer> courseChoices = new HashMap<>();
		Map<Integer,Integer> studentRegisteredCourseNos = new HashMap<>();
		for(Map.Entry<Integer,ArrayList<Course>> entry: data.entrySet())
		{
			ArrayList<Course> tmp = entry.getValue();
			for(int i=0;i<tmp.size();i++)
			{
				if(courseChoices.containsKey((tmp.get(i).getCourseId())))
					courseChoices.put(tmp.get(i).getCourseId(), courseChoices.get(tmp.get(i).getCourseId())+1);
				else
					courseChoices.put(tmp.get(i).getCourseId(),1);
			}
		}
		ArrayList<Integer> removeList = new ArrayList<Integer>();
		for(Map.Entry<Integer, Integer> entry: courseChoices.entrySet())
		{
			int count = 0;
			if(entry.getValue()>=3)
			{
				for(Map.Entry<Integer,ArrayList<Course>> entry2: data.entrySet())
				{
					if(count==10)
					{
						removeList.add(entry.getKey());
						break;
					}
					if(studentRegisteredCourseNos.containsKey(entry2.getKey())&&studentRegisteredCourseNos.get(entry2.getKey())==4)
						continue;
					StudentRegisteredCourses src = new StudentRegisteredCourses();
					boolean flag = false;
					ArrayList<Course> tmp = entry2.getValue();
					for(int j=0;j<tmp.size();j++)
					{
						if(tmp.get(j).getCourseId()==entry.getKey())
						{
							flag = true;
							src.setCourseId1(entry.getKey());
							src.setStudentId(entry2.getKey());
							break;
						}
					}
					if(flag)
					{
						registeredCourses.add(src);
						if(studentRegisteredCourseNos.containsKey(src.getStudentId()))
							studentRegisteredCourseNos.put(src.getStudentId(), studentRegisteredCourseNos.get(src.getStudentId())+1);
						else
							studentRegisteredCourseNos.put(src.getStudentId(), 1);
						count++;
					}
				}
			}
		}
		Map<Integer,ArrayList<Course>> newData = new HashMap<>();
		for(Map.Entry<Integer,ArrayList<Course>> entry: data.entrySet())
		{
			ArrayList<Course> tmp = entry.getValue();
			ArrayList<Course> tor = new ArrayList<Course>();
			for(int i=0;i<tmp.size();i++)
			{
				if(removeList.contains(tmp.get(i).getCourseId()))
					tor.add(tmp.get(i));
			}
			for(int i=0;i<tor.size();i++)
				tmp.remove(tor.get(i));
			newData.put(entry.getKey(), tmp);
		}
		data = newData;
		int maxCoursesLeft = 0;
		//Contains number of students allocated to a particular course
		ArrayList<StudentRegisteredCourses> registeredData = registeredCourses;
		
		//Number of requests pending for a particular course
		Map<Integer,Integer> pendingCourseChoices = new HashMap<>();
		Map<Integer,ArrayList<Integer>> studentRegisteredCoursesNos = new HashMap<>();
		for(int i=0;i<registeredData.size();i++)
		{
			if(studentRegisteredCoursesNos.containsKey(registeredData.get(i).getStudentId()))
				studentRegisteredCoursesNos.get(registeredData.get(i).getStudentId()).add(registeredData.get(i).getCourseId1());
			else
			{
				ArrayList<Integer> tmp = new ArrayList<Integer>();
				tmp.add(registeredData.get(i).getCourseId1());
				studentRegisteredCoursesNos.put(registeredData.get(i).getStudentId(), tmp);
			}
		}
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
			if(pendingCourseChoices.containsKey(registeredData.get(i).getCourseId1()))
				pendingCourseChoices.put(registeredData.get(i).getCourseId1(), pendingCourseChoices.get(registeredData.get(i).getCourseId1())-1);
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
		//System.out.println(noOfRequestsPending + " " + noOfCoursesRequired);
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
				if((studentRegisteredCoursesNos.containsKey(entry.getKey()) && studentRegisteredCoursesNos.get(entry.getKey()).size() >= 4))
					break;
				if(topCoursesId.contains(tmp.get(i).getCourseId()))
				{
					StudentRegisteredCourses newCourse = new StudentRegisteredCourses();
					newCourse.setCourseId1(tmp.get(i).getCourseId());
					newCourse.setStudentId(entry.getKey());
					registeredData.add(newCourse);
					if(studentRegisteredCoursesNos.containsKey(entry.getKey()))
						studentRegisteredCoursesNos.get(entry.getKey()).add(tmp.get(i).getCourseId());
					else
					{
						ArrayList<Integer> tmp3 = new ArrayList<Integer>();
						tmp3.add(tmp.get(i).getCourseId());
						studentRegisteredCoursesNos.put(entry.getKey(), tmp3);
					}
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
						newCourse.setCourseId1(topCoursesId.get(i));
						newCourse.setStudentId(entry.getKey());
						registeredData.add(newCourse);
						studentRegisteredCoursesNos.get(entry.getKey()).add(topCoursesId.get(i));
					}
				}
				else
					break;
			}
		}
		Map<Integer,ArrayList<Integer>> registeredCourseChoices = new HashMap<>();
		for(StudentRegisteredCourses sc: registeredData)
		{
			if(registeredCourseChoices.containsKey(sc.getStudentId()))
				registeredCourseChoices.get(sc.getStudentId()).add(sc.getCourseId1());
			else
			{
				ArrayList<Integer> tmp = new ArrayList<Integer>();
				tmp.add(sc.getCourseId1());
				registeredCourseChoices.put(sc.getStudentId(), tmp);
			}
		}
		admindao.updateAllocatedStudentCourses(registeredCourseChoices);
	}
	public Challan generateChallan(SemesterRegistration semesterRegistration) {
		int fee = semesterRegistration.getTotalFee();
		int studentId = semesterRegistration.getStudentId();
		PaymentReference paymentReference = getPaymentReference(fee, studentId);
		int paymentReferenceNumber = paymentReference.getReferenceNo();
		Challan challan = new Challan();
		challan.setChallanNo((paymentReferenceNumber*265)%10000);
		challan.setPaymentReference(paymentReference);
		paymentsDaoImplementation.storeChallan(challan);
		return challan;
	}

	private PaymentReference getPaymentReference(int fee, int studentId) {
		PaymentReference paymentReference = new PaymentReference();
		StudentInterface studentImplementation = StudentImplementation.getInstance();
		paymentReference.setPaymentStatus(PaymentStatus.SUCCESSFUL);
		paymentReference.setAmount(fee);
		paymentReference.setPayeeName(studentImplementation.viewStudentDetails(studentId).getName());
		int paymentReferenceNumber = paymentsDaoImplementation.storePaymentReference(paymentReference);
		paymentReference.setReferenceNo(paymentReferenceNumber);
		return paymentReference;
	}

}
