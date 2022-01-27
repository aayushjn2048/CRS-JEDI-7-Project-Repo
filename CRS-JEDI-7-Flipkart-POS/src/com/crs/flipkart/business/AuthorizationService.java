/**
 * 
 */
package com.crs.flipkart.business;

import com.crs.flipkart.constants.Role;
import com.crs.flipkart.dao.AuthorizeDaoInterface;
import com.crs.flipkart.dao.AuthorizeDaoOperation;

/**
 * @author HP
 *
 */
public class AuthorizationService {
	public Role authorize(String username, String password)
	{
		AuthorizeDaoInterface auth = new AuthorizeDaoOperation();
		Role role = auth.authorizeUser(username, password);
		return role;
	}
}
