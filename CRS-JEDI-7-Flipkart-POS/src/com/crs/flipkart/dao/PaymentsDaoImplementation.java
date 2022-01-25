package com.crs.flipkart.dao;

import com.crs.flipkart.bean.Challan;
import com.crs.flipkart.bean.PaymentReference;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentsDaoImplementation implements PaymentsDaoInterface {

    private static PaymentsDaoImplementation instance = null;
    Connection conn = DBConnection.connectDB();

    private PaymentsDaoImplementation() {
    }

    public static PaymentsDaoImplementation getInstance() {
        if (instance == null) {
            synchronized (PaymentsDaoImplementation.class) {
                instance = new PaymentsDaoImplementation();
            }
        }
        return instance;
    }

    @Override
    public int storePaymentReference(PaymentReference paymentReference) {
        try {
            PreparedStatement stmt = null;
            String sql = "INSERT INTO paymentRefernce(referenceNo, payeeName, amount, paymentStatus) values(?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, paymentReference.getReferenceNo());
            stmt.setString(2, paymentReference.getPayeeName());
            stmt.setInt(3, paymentReference.getAmount());
            stmt.setString(4, paymentReference.getPaymentStatus().toString());
            int rs = stmt.executeUpdate();
            return 0; //TODO get payment reference number
        } catch (Exception se) {
            // Handle errors for JDBC
            se.printStackTrace();
        }
        return 0;
    }

    @Override
    public void storeChallan(Challan challan) {
        try {
            PreparedStatement stmt = null;
            String sql = "INSERT INTO challan(challanNo,paymentReferenceNo) values(?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, challan.getChallanNo());
            stmt.setInt(2, challan.getPaymentReference().getReferenceNo());
            int rs = stmt.executeUpdate();
        } catch (Exception se) {
            // Handle errors for JDBC
            se.printStackTrace();
        }
    }
}
