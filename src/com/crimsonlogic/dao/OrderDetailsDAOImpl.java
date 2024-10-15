package com.crimsonlogic.dao;

import com.crimsonlogic.dao.OrderDetailsDAO;
import com.crimsonlogic.model.OrderDetails;
import com.crimsonlogic.dao.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {

    @Override
    public List<OrderDetails> getOrderDetailsByOrderId(int orderId) {
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnect.dbConnection();
            String sql = "SELECT * FROM order_details WHERE order_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, orderId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                OrderDetails orderDetails = new OrderDetails();
                orderDetails.setOrderDetailsId(rs.getInt("order_details_id"));
                orderDetails.setOrderId(rs.getInt("order_id"));
                orderDetails.setMedicineId(rs.getInt("medicine_id"));
                orderDetails.setQuantity(rs.getInt("quantity"));
                orderDetails.setPrice(rs.getDouble("price"));
                orderDetailsList.add(orderDetails);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.close(conn, stmt, rs);
        }

        return orderDetailsList;
    }
}