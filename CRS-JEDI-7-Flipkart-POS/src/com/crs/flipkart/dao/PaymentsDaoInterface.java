/**
 * 
 */
package com.crs.flipkart.dao;

import com.crs.flipkart.bean.Challan;
import com.crs.flipkart.bean.PaymentReference;

/**
 * @author HP
 *
 */
public interface PaymentsDaoInterface {
	public int storePaymentReference(PaymentReference paymentReference);
    public void storeChallan(Challan challan);
}
