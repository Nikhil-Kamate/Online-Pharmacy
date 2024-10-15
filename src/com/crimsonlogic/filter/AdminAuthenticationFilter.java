package com.crimsonlogic.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// Update the annotation to apply the filter to specific servlet URLs
@WebFilter({"/AddMedicineServlet", "/DisplayMedicinesServlet", "/EditMedicineServlet"})
public class AdminAuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("AdminAuthenticationFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Get the session, do not create a new one if it doesn't exist
        HttpSession session = httpRequest.getSession(false);

        // Check if user is logged in as admin
        if (session != null && "admin".equals(session.getAttribute("user_role"))) {
            // User is authenticated, continue the request
            chain.doFilter(request, response);
        } else {
            // User is not authenticated, redirect to login
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp?error=Please login as an admin");
        }
    }

    @Override
    public void destroy() {
        System.out.println("AdminAuthenticationFilter destroyed");
    }
}

