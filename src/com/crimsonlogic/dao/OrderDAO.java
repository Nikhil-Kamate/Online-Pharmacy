package com.crimsonlogic.dao;

import java.sql.SQLException;
import java.util.List;

import com.crimsonlogic.model.Order;
import com.crimsonlogic.model.OrderDetails;
import com.crimsonlogic.model.Payment;

public interface OrderDAO {
	    int createOrder(Order order, List<OrderDetails> orderDetailsList, Payment payment) throws SQLException;
	    List<Order> getOrdersByUserId(int userId);
	    Order getOrderById(int orderId);
	 //   List<Order> getOrderHistoryByUserId(int userId);
}
