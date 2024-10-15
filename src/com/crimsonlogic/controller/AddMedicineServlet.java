package com.crimsonlogic.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.crimsonlogic.dao.MedicineDAOImpl;
import com.crimsonlogic.model.Medicine;

@MultipartConfig
@WebServlet("/AddMedicineServlet")
public class AddMedicineServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddMedicineServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form parameters
        String medicineName = request.getParameter("medicineName");
        String medicineCategory = request.getParameter("medicineCategory");
        String medicineDescription = request.getParameter("medicineDescription");
        double medicinePrice = Double.parseDouble(request.getParameter("medicinePrice"));
        int medicineStockQuantity = Integer.parseInt(request.getParameter("medicineStockQuantity"));
        String medicineCompany = request.getParameter("medicineCompany");
        String medicineManufacturedDate = request.getParameter("medicineManufacturedDate");
        String medicineExpireDate = request.getParameter("medicineExpireDate");

        // Handle image upload
        Part file = request.getPart("medicinePhoto");
        String imageFileName = file.getSubmittedFileName();  // Get selected image file name
        /*String uploadPath = "D:\\Login\\WebContent\\uploads\\" + imageFileName; */ // Upload path where the actual image will be stored
        String uploadPath = getServletContext().getRealPath("") + "uploads" + File.separator + imageFileName;
        System.out.println("Selected Image File Name: " + imageFileName);
        System.out.println("Upload Path: " + uploadPath);

        // Upload the image to the specified path
        try (FileOutputStream fos = new FileOutputStream(uploadPath); 
             InputStream is = file.getInputStream()) {
            byte[] data = new byte[is.available()];
            is.read(data);
            fos.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create a new Medicine object and set its properties
        Medicine medicine = new Medicine();
        medicine.setMedicineName(medicineName);
        medicine.setMedicineCategory(medicineCategory);
        medicine.setMedicineDescription(medicineDescription);
        medicine.setMedicinePrice(medicinePrice);
        medicine.setMedicineStockQuantity(medicineStockQuantity);
        medicine.setMedicineCompany(medicineCompany);
        medicine.setMedicineManufacturedDate(java.time.LocalDate.parse(medicineManufacturedDate));
        medicine.setMedicineExpireDate(java.time.LocalDate.parse(medicineExpireDate));
        medicine.setMedicinePhotoPath(imageFileName); // Set the path where the image is stored

        // Add the medicine to the database using DAO
        MedicineDAOImpl medicineDAO = new MedicineDAOImpl();
        boolean isAdded = medicineDAO.addMedicine(medicine);

        // Check if the medicine was added successfully
        if (isAdded) {
            System.out.println("Medicine added successfully!");
            response.sendRedirect("adminHome.jsp");  // Redirect to the admin home page or display medicines page
        } else {
            System.out.println("Failed to add medicine.");
            response.sendRedirect("addMedicine.jsp");  // Redirect back to the add medicine page if failed
        }
    }
}
