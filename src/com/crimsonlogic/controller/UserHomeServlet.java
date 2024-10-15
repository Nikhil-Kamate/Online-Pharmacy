package com.crimsonlogic.controller;

import com.crimsonlogic.dao.MedicineDAO;
import com.crimsonlogic.dao.MedicineDAOImpl;
import com.crimsonlogic.dao.OrderDAO;
import com.crimsonlogic.dao.OrderDAOImpl;
import com.crimsonlogic.model.Medicine;
import com.crimsonlogic.model.Order;
import com.crimsonlogic.model.OrderDetails;
import com.crimsonlogic.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors; // Import for stream API

@WebServlet("/user-home")
public class UserHomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp?error=Please login to access this page");
            return;
        }

        // Fetch medicines
        MedicineDAO medicineDAO = new MedicineDAOImpl();
        List<Medicine> allMedicines = medicineDAO.getAllMedicines();
       
        request.setAttribute("medicines", allMedicines);
        
        // Set headers to prevent caching
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        
        request.getRequestDispatcher("user-home.jsp").forward(request, response);
    }
}
