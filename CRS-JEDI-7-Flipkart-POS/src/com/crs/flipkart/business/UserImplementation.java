/**
 * 
 */
package com.crs.flipkart.business;

import com.crs.flipkart.bean.Student;
import com.crs.flipkart.dao.UserDaoInterface;
import com.crs.flipkart.dao.UserDaoOperation;

/**
 * 
 * @author JEDI-03
 * Implementations of User Operations
 * 
 */
public class UserImplementation implements UserInterface {

	/**
	 * Method to add credentials of Users
	 * @param user contains the student details
	 */
	@Override
	public void addUserdata(Student user) {
		// TODO Auto-generated method stub
		
		UserDaoInterface userDaoOperation = new UserDaoOperation();
		userDaoOperation.addUsertData(user);
	}
	

}
