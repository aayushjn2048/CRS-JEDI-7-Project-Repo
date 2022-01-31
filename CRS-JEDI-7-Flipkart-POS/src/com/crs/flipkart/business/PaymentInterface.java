/**
 * 
 */
package com.crs.flipkart.business;
/**
 * 
 * @author JEDI-03
 * Interface for PaymentImplementation 
 * 
 */
public interface PaymentInterface {
	
	/**
	 * Method to calculate the total fee for student
	 * @param StudentId
	 * @return totalFee
	 */
	public int calculateFees(int studentId);
	
	/**
	 * Method to Display the payment methods
	 */
	public String displayPaymentMethods();
}
