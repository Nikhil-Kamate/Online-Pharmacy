package com.crimsonlogic.controller;

import com.crimsonlogic.dao.CartDAO;
import com.crimsonlogic.dao.CartDAOImpl;
import com.crimsonlogic.dao.OrderDAO;
import com.crimsonlogic.dao.OrderDAOImpl;
import com.crimsonlogic.model.Order;
import com.crimsonlogic.model.OrderDetails;
import com.crimsonlogic.model.Payment;
import com.crimsonlogic.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/processPayment")
public class PaymentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CartDAO cartDAO = new CartDAOImpl();
    private OrderDAO orderDAO = new OrderDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        @SuppressWarnings("unchecked")
        List<OrderDetails> cart = (List<OrderDetails>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            response.sendRedirect("cart.jsp?error=Cart is empty");
            return;
        }

        String amountParam = request.getParameter("amount");
        double totalAmount = 0;
        if (amountParam != null && !amountParam.isEmpty()) {
            try {
                totalAmount = Double.parseDouble(amountParam);
            } catch (NumberFormatException e) {
                response.sendRedirect("payment.jsp?error=Invalid amount format");
                return;
            }
        } else {
            response.sendRedirect("payment.jsp?error=Amount is missing");
            return;
        }

        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp?error=User not logged in");
            return;
        }
        Integer userId = user.getUserId();

        Order order = new Order();
        order.setUserId(userId);
        order.setOrderDate(new java.util.Date());
        order.setTotalAmount(totalAmount);

        Payment payment = new Payment();
        payment.setAmount(totalAmount);
        payment.setPaymentMethod(request.getParameter("paymentMethod"));
        payment.setPaymentStatus("successful"); // Set payment status to "successful"
        payment.setPaymentDate(LocalDate.now());

        try {
            // Create order, order details, and payment
            int orderId = orderDAO.createOrder(order, cart, payment);

            // Update stock quantities
            for (OrderDetails item : cart) {
                int newStock = cartDAO.getAvailableStock(item.getMedicineId()) - item.getQuantity();
                cartDAO.updateStock(item.getMedicineId(), newStock);
            }

            // Clear the cart
            cart.clear();
            session.setAttribute("cart", cart);

            // Redirect to success page with all necessary parameters
            response.sendRedirect("orderConfirmation.jsp?orderId=" + orderId
                + "&totalAmount=" + totalAmount
                + "&paymentMethod=" + payment.getPaymentMethod()
                + "&paymentStatus=" + payment.getPaymentStatus());

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("payment.jsp?error=Payment failed");
        }
    }

}