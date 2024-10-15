package com.crimsonlogic.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crimsonlogic.dao.UserDAOImpl;
import com.crimsonlogic.dao.DBConnect;
import com.crimsonlogic.model.User;

@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        // Get form parameters
    	String userIdStr = request.getParameter("userId");
        int userId = userIdStr != null && !userIdStr.isEmpty() ? Integer.parseInt(userIdStr) : 0; // Or handle it differently if userId is optional
        
        String user_firstName = request.getParameter("user_firstName");
        String user_lastName = request.getParameter("user_lastName");
        String user_email = request.getParameter("user_email");
        String user_password = request.getParameter("user_password");
        
        String userPhoneNoStr = request.getParameter("user_phoneNo");
        long user_phoneNo = userPhoneNoStr != null && !userPhoneNoStr.isEmpty() ? Long.parseLong(userPhoneNoStr) : 0;
        
        String user_address = request.getParameter("user_address");
        String user_dateofBirthStr = request.getParameter("user_dateofBirth");
        LocalDate user_dateofBirth = user_dateofBirthStr != null && !user_dateofBirthStr.isEmpty() ? LocalDate.parse(user_dateofBirthStr) : null;
       

        
        
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        
        if (!Pattern.matches(passwordPattern, user_password)) {
            // If password does not match the pattern, redirect with an error message
            response.sendRedirect("register.jsp?error=Invalid password format");
            return; 
        }

        // Create a User object
        User newUser = new User(
        	    userId, 
        	    user_firstName, 
        	    user_lastName, 
        	    user_email, 
        	    user_password, 
        	    user_phoneNo, 
        	    user_address, 
        	    user_dateofBirth
        	);


        
        Connection connection = null;
        UserDAOImpl userDAO = null;

        try {
            // Get connection from DBConnect
            connection = DBConnect.dbConnection();
            userDAO = new UserDAOImpl(connection);

            // Add user to the database
            boolean isUserAdded = userDAO.addUser(newUser);

            // Redirect based on registration success or failure
            if (isUserAdded) {
                response.sendRedirect("login.jsp"); // Redirect to login page on success
            } else {
                response.sendRedirect("register.jsp?error=Registration failed"); // Redirect back to registration on failure
            }

        } finally {
            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
