package com.crimsonlogic.dao;

import com.crimsonlogic.model.Payment;

import java.sql.Connection;

public interface PaymentDAO {
    void createPayment(Payment payment, Connection conn);
}
