package com.crimsonlogic.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crimsonlogic.dao.DBConnect;
import com.crimsonlogic.dao.MedicineDAO;
import com.crimsonlogic.dao.MedicineDAOImpl;

@WebServlet("/DeleteMedicineServlet")
public class DeleteMedicineServlet extends HttpServlet {
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
        int medicineId = Integer.parseInt(request.getParameter("medicineId"));

        boolean isDeleted = medicineDAO.deleteMedicine(medicineId);
        System.out.println(isDeleted);
        if (isDeleted) {
            response.sendRedirect("DisplayMedicinesServlet");
        } else {
            request.setAttribute("error", "Failed to delete medicine.");
            request.getRequestDispatcher("displayMedicines.jsp").forward(request, response);
        }
    }
}
