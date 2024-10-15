package com.crimsonlogic.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.crimsonlogic.dao.AdminDAOImpl;
import com.crimsonlogic.dao.UserDAOImpl;
import com.crimsonlogic.dao.DBConnect;
import com.crimsonlogic.model.User;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user_email = request.getParameter("user_email");
        String user_password = request.getParameter("user_password");

        Connection connection = null;
        AdminDAOImpl adminDAO = null;
        UserDAOImpl userDAO = null;

        try {
            connection = DBConnect.dbConnection();
            adminDAO = new AdminDAOImpl(connection);
            userDAO = new UserDAOImpl(connection);

            // Check for admin credentials
            if (adminDAO.validateAdmin(user_email, user_password)) {
                HttpSession session = request.getSession();
                session.setAttribute("user_role", "admin");
                response.sendRedirect("adminHome.jsp");
                return;
            }

            // Check for regular user credentials
            User user = userDAO.login(user_email, user_password);

            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("userId", user.getUserId()); // Make sure userId is set
                response.sendRedirect("user-home");
            } else {
                response.sendRedirect("login.jsp?error=Invalid username or password");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=Something went wrong");
        } finally {
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
