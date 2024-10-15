package com.crimsonlogic.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.crimsonlogic.dao.DBConnect;
import com.crimsonlogic.dao.MedicineDAO;
import com.crimsonlogic.dao.MedicineDAOImpl;
import com.crimsonlogic.model.Medicine;

@WebServlet("/DisplayMedicinesServlet")
public class DisplayMedicinesServlet extends HttpServlet {
    private MedicineDAO medicineDAO;

    @Override
    public void init() {
        Connection connection = null;
        try {
            connection = DBConnect.dbConnection();
            medicineDAO = new MedicineDAOImpl(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if the user is logged in as an admin
        HttpSession session = request.getSession(false);
        if (session == null || !"admin".equals(session.getAttribute("user_role"))) {
            response.sendRedirect("login.jsp?error=Please login as an admin");
            return;
        }

        // Fetch and display medicines if the user is an admin
        List<Medicine> medicines = medicineDAO.getAllMedicines();
        System.out.println("Fetched medicines count: " + medicines.size());
        request.setAttribute("medicines", medicines);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/displayMedicines.jsp");
        rd.forward(request, response);
    }
}
