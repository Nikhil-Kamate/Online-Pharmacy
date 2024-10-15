package com.crimsonlogic.controller;

import com.crimsonlogic.dao.DBConnect;
import com.crimsonlogic.dao.MedicineDAOImpl;
import com.crimsonlogic.model.Medicine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/SearchMedicineServlet")
public class SearchMedicineServlet extends HttpServlet {
    private MedicineDAOImpl medicineDAO;

    @Override
    public void init() {
        try {
            // Initialize the MedicineDAOImpl with a database connection
            medicineDAO = new MedicineDAOImpl(DBConnect.dbConnection());
        } catch (Exception e) {
            e.printStackTrace(); // Handle initialization error
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the medicine name from the request parameter
        String medicineName = request.getParameter("medicineName");

        List<Medicine> medicines;
        if (medicineName != null && !medicineName.trim().isEmpty()) {
            // Fetch medicine details from the DAO
            medicines = medicineDAO.getMedicinesByName(medicineName);
        } else {
            // If no search term, fetch all medicines
            medicines = medicineDAO.getAllMedicines();
        }

        // Get the user role from session
        HttpSession session = request.getSession();
        String userRole = (String) session.getAttribute("user_role");

        // Set the list of medicines as a request attribute
        request.setAttribute("medicines", medicines);

        // Forward the request to the appropriate JSP page based on the user role
        if ("admin".equals(userRole)) {
            request.getRequestDispatcher("displayMedicines.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("user-home.jsp").forward(request, response);
        }
    }
}
