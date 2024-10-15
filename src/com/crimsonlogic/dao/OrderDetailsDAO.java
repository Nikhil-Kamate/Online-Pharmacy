package com.crimsonlogic.dao;

import java.util.List;

import com.crimsonlogic.model.OrderDetails;

public interface OrderDetailsDAO {
    List<OrderDetails> getOrderDetailsByOrderId(int orderId);
}
