package com.crimsonlogic.controller;

import com.crimsonlogic.dao.CartDAO;
import com.crimsonlogic.dao.CartDAOImpl;
import com.crimsonlogic.dao.MedicineDAO;
import com.crimsonlogic.dao.MedicineDAOImpl;
import com.crimsonlogic.model.Medicine;
import com.crimsonlogic.model.OrderDetails;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private CartDAO cartDAO = new CartDAOImpl();
    private MedicineDAO medicineDAO = new MedicineDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        List<OrderDetails> cart = (List<OrderDetails>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();	
            session.setAttribute("cart", cart);
        }

        String action = request.getParameter("action");
        int medicineId;
        int quantity;
        double price;
        Medicine medicine;

        switch (action) {
            case "add":
                medicineId = Integer.parseInt(request.getParameter("medicineId"));
                quantity = Integer.parseInt(request.getParameter("quantity"));
                price = Double.parseDouble(request.getParameter("price"));

                // Check available stock
                int availableStock = cartDAO.getAvailableStock(medicineId);
                if (quantity > availableStock) {
                    request.setAttribute("error", "Stock not available");
                    request.getRequestDispatcher("cart.jsp").forward(request, response);
                    return;
                }

                // Fetch medicine details
                medicine = medicineDAO.getMedicineById(medicineId);

                // Add item to cart
                boolean itemExists = false;
                for (OrderDetails item : cart) {
                    if (item.getMedicineId() == medicineId) {
                        item.setQuantity(item.getQuantity() + quantity);
                        itemExists = true;
                        break;
                    }
                }
                if (!itemExists) {
                    OrderDetails newItem = new OrderDetails();
                    newItem.setMedicineId(medicineId);
                    newItem.setQuantity(quantity);
                    newItem.setPrice(price);
                    newItem.setMedicine(medicine);
                    cart.add(newItem);
                }
                break;

            case "update":
                medicineId = Integer.parseInt(request.getParameter("medicineId"));
                quantity = Integer.parseInt(request.getParameter("quantity"));

                // Check available stock
                availableStock = cartDAO.getAvailableStock(medicineId);
                if (quantity > availableStock) {
                    request.setAttribute("error", "Stock not available");
                    request.getRequestDispatcher("cart.jsp").forward(request, response);
                    return;
                }

                // Update item quantity
                for (OrderDetails item : cart) {
                    if (item.getMedicineId() == medicineId) {
                        item.setQuantity(quantity);
                        break;
                    }
                }
                break;

            case "increment":
                medicineId = Integer.parseInt(request.getParameter("medicineId"));
                for (OrderDetails item : cart) {
                    if (item.getMedicineId() == medicineId) {
                        availableStock = cartDAO.getAvailableStock(medicineId);
                        if (item.getQuantity() < availableStock) {
                            item.setQuantity(item.getQuantity() + 1);
                        }
                        break;
                    }
                }
                break;

            case "decrement":
                medicineId = Integer.parseInt(request.getParameter("medicineId"));
                for (OrderDetails item : cart) {
                    if (item.getMedicineId() == medicineId && item.getQuantity() > 1) {
                        item.setQuantity(item.getQuantity() - 1);
                        break;
                    }
                }
                break;

            case "remove":
                medicineId = Integer.parseInt(request.getParameter("medicineId"));
                cart.removeIf(item -> item.getMedicineId() == medicineId);
                break;

            default:
                // Invalid action or no action specified
                break;
        }

        // Set the updated cart and redirect
        session.setAttribute("cart", cart);
        response.sendRedirect("cart.jsp");
    }
}
