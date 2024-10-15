package com.crimsonlogic.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/user-home", "/cart","/processPayment"}) // Update with user-specific pages
public class UserAuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("UserAuthenticationFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Get the session, do not create a new one if it doesn't exist
        HttpSession session = httpRequest.getSession(false);

        // Check if user is logged in
        if (session != null && session.getAttribute("user") != null) {
            // User is authenticated, continue the request
            chain.doFilter(request, response);
        } else {
            // User is not authenticated, redirect to login
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp?error=Please login to access this page");
        }
    }

    @Override
    public void destroy() {
        System.out.println("UserAuthenticationFilter destroyed");
    }
}

