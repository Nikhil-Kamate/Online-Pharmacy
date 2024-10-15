package com.crimsonlogic.controller;

import com.crimsonlogic.dao.OrderDAO;
import com.crimsonlogic.dao.OrderDAOImpl;
import com.crimsonlogic.model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/order-history")
public class OrderHistoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp?error=Please login to access this page");
            return;
        }

        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            response.sendRedirect("login.jsp?error=Invalid session");
            return;
        }

        try {
            OrderDAO orderDAO = new OrderDAOImpl();
            List<Order> orderHistory = orderDAO.getOrdersByUserId(userId);
            request.setAttribute("orderHistory", orderHistory);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while fetching order history.");
        }

        request.getRequestDispatcher("orderhistory.jsp").forward(request, response);
    }
}
