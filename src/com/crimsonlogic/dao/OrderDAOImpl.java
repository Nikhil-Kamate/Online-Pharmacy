package com.crimsonlogic.dao;

import com.crimsonlogic.model.Order;


import com.crimsonlogic.model.OrderDetails;
import com.crimsonlogic.model.Payment;
import com.crimsonlogic.dao.DBConnect;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public int createOrder(Order order, List<OrderDetails> orderDetailsList, Payment payment) throws SQLException {
        Connection conn = null;
        PreparedStatement orderStmt = null;
        PreparedStatement orderDetailsStmt = null;
        ResultSet rs = null;
        int orderId = 0;

        try {
            conn = DBConnect.dbConnection();
            conn.setAutoCommit(false);

            // Insert into orders table
            String orderSQL = "INSERT INTO orders(user_id, order_date, total_amount) VALUES (?, ?, ?)";
            orderStmt = conn.prepareStatement(orderSQL, Statement.RETURN_GENERATED_KEYS);
            orderStmt.setInt(1, order.getUserId());
            orderStmt.setDate(2, new Date(order.getOrderDate().getTime()));
            orderStmt.setDouble(3, order.getTotalAmount());
            orderStmt.executeUpdate();

            rs = orderStmt.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);
                order.setOrderId(orderId);

                // Insert into order_details table
                String orderDetailsSQL = "INSERT INTO order_details(order_id, medicine_id, quantity, price) VALUES (?, ?, ?, ?)";
                orderDetailsStmt = conn.prepareStatement(orderDetailsSQL);
                
                //batch operation , ensures multiple orderDetails inserted
                for (OrderDetails orderDetails : orderDetailsList) {
                    orderDetailsStmt.setInt(1, orderId);
                    orderDetailsStmt.setInt(2, orderDetails.getMedicineId());
                    orderDetailsStmt.setInt(3, orderDetails.getQuantity());
                    orderDetailsStmt.setDouble(4, orderDetails.getPrice());
                    orderDetailsStmt.addBatch();
                }

                orderDetailsStmt.executeBatch();

                // Insert into payment table
                payment.setOrderId(orderId);
                PaymentDAO paymentDAO = new PaymentDAOImpl();
                paymentDAO.createPayment(payment, conn); 

                conn.commit(); // Commit transaction
            } else {
                throw new SQLException("Failed to retrieve generated order_id");
            }

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); 
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw e; // 
        } finally {
            DBConnect.close(conn, orderStmt, rs);
            DBConnect.close(null, orderDetailsStmt, null);
        }

        return orderId;
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        List<Order> orders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnect.dbConnection();
            String sql = "SELECT * FROM orders WHERE user_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setOrderDate(rs.getDate("order_date"));
                order.setTotalAmount(rs.getDouble("total_amount"));
                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.close(conn, stmt, rs);
        }

        return orders;
    }

    @Override
    public Order getOrderById(int orderId) {
        Order order = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnect.dbConnection();
            String sql = "SELECT * FROM orders WHERE order_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, orderId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setOrderDate(rs.getDate("order_date"));
                order.setTotalAmount(rs.getDouble("total_amount"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.close(conn, stmt, rs);
        }

        return order;
    }
    

   /* @Override
    public List<Order> getOrderHistoryByUserId(int userId) {
        List<Order> orderHistory = new ArrayList<>();
        String query = "SELECT o.order_id, o.order_date, o.total_amount, " +
                       "p.payment_id, p.payment_date, p.amount AS payment_amount, " +
                       "p.payment_method, p.payment_status " +
                       "FROM orders o " +
                       "JOIN payment p ON o.order_id = p.order_id " +
                       "WHERE o.user_id = ?";

        try (Connection conn = DBConnect.dbConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setOrderDate(rs.getDate("order_date"));
                order.setTotalAmount(rs.getDouble("total_amount"));
                order.setPaymentId(rs.getInt("payment_id"));
                order.setPaymentDate(rs.getDate("payment_date"));
                order.setPaymentAmount(rs.getBigDecimal("payment_amount"));
                order.setPaymentMethod(rs.getString("payment_method"));
                order.setPaymentStatus(rs.getString("payment_status"));

                orderHistory.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderHistory;
    }*/
}
