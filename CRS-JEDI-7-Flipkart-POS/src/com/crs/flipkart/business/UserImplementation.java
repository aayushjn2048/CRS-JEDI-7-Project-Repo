/**
 * 
 */
package com.crs.flipkart.business;

import com.crs.flipkart.bean.Student;
import com.crs.flipkart.dao.UserDaoInterface;
import com.crs.flipkart.dao.UserDaoOperation;

/**
 * @author HP
 *
 */
public class UserImplementation implements UserInterface {

	@Override
	public void addUserdata(Student user) {
		// TODO Auto-generated method stub
		
		UserDaoInterface userDaoOperation = new UserDaoOperation();
		userDaoOperation.addUsertData(user);
	}
	

}
