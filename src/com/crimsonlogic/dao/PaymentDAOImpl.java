package com.crimsonlogic.dao;

import com.crimsonlogic.model.Payment;
import com.crimsonlogic.dao.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentDAOImpl implements PaymentDAO {

    @Override
    public void createPayment(Payment payment, Connection conn) {
        PreparedStatement stmt = null;

        try {
            String sql = "INSERT INTO payment(order_id, payment_date, amount, payment_method, payment_status) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, payment.getOrderId());
            stmt.setDate(2, java.sql.Date.valueOf(payment.getPaymentDate()));
            stmt.setDouble(3, payment.getAmount());
            stmt.setString(4, payment.getPaymentMethod());
            stmt.setString(5, payment.getPaymentStatus());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.close(null, stmt, null);
        }
    }
}
