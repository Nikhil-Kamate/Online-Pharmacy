package com.crimsonlogic.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.crimsonlogic.dao.DBConnect;
import com.crimsonlogic.dao.MedicineDAO;
import com.crimsonlogic.dao.MedicineDAOImpl;
import com.crimsonlogic.model.Medicine;

@WebServlet("/EditMedicineServlet")
public class EditMedicineServlet extends HttpServlet {
    private MedicineDAO medicineDao;

    @Override
    public void init() {
        
        medicineDao = new MedicineDAOImpl();
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int medicineId = Integer.parseInt(request.getParameter("medicineId"));

        // Fetch the medicine details from the database
        Medicine medicine = medicineDao.getMedicineById(medicineId);

       
        request.setAttribute("medicine", medicine);

       
        RequestDispatcher dispatcher = request.getRequestDispatcher("editMedicine.jsp");
        dispatcher.forward(request, response);
    }

    // Handle POST requests to update the medicine data
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int medicineId = Integer.parseInt(request.getParameter("medicineId"));
        String medicineName = request.getParameter("medicineName");
        String medicineCategory = request.getParameter("medicineCategory");
        String medicineDescription = request.getParameter("medicineDescription");
        double medicinePrice = Double.parseDouble(request.getParameter("medicinePrice"));
        int medicineStockQuantity = Integer.parseInt(request.getParameter("medicineStockQuantity"));
        String medicineCompany = request.getParameter("medicineCompany");
        LocalDate medicineManufacturedDate = LocalDate.parse(request.getParameter("medicineManufacturedDate"));
        LocalDate medicineExpireDate = LocalDate.parse(request.getParameter("medicineExpireDate"));

       
        String existingPhotoPath = medicineDao.getMedicineById(medicineId).getMedicinePhotoPath();

        // Create a new Medicine object with the updated details
        Medicine medicine = new Medicine(medicineId, medicineName, medicineCategory, medicineDescription, 
                                         medicinePrice, medicineStockQuantity, medicineCompany, 
                                         medicineManufacturedDate, medicineExpireDate, existingPhotoPath);

        // Call the DAO to update the medicine in the database
        boolean updateSuccess = medicineDao.updateMedicine(medicine);
        if (updateSuccess) {
            response.sendRedirect("DisplayMedicinesServlet");
        } else {
            request.setAttribute("error", "Failed to update medicine.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("editMedicine.jsp");
            dispatcher.forward(request, response);
        }
    }
}
