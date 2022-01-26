/**
 * 
 */
package com.crs.flipkart.dao;

import com.crs.flipkart.constants.Role;

/**
 * @author HP
 *
 */
public interface AuthorizeDaoInterface {
	public Role authorizeUser(String username, String password);
}
