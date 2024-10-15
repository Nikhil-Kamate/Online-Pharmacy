<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="com.crimsonlogic.model.OrderDetails"%>
<%@ page import="com.crimsonlogic.model.Medicine"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shopping Cart</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f4f4f4;
        display: flex;
        flex-direction: column;
        min-height: 100vh;
    }

    .container {
        width: 80%;
        margin: 0 auto;
        padding: 20px;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: 20px;
        background-color: white;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    }

    th, td {
        border: 1px solid #ddd;
        padding: 10px;
        text-align: center;
    }

    th {
        background-color: #f4f4f4;
    }

    .actions form {
        display: inline;
    }

    .actions button {
        background-color: #007bff;
        color: white;
        border: none;
        padding: 5px 10px;
        cursor: pointer;
        border-radius: 5px;
        margin: 0 2px;
    }

    .actions button:hover {
        background-color: #0056b3;
    }

    .remove-button {
        background-color: hsl(4, 82%, 62%);
    }

    .remove-button:hover {
        background-color: #c82333;
    }

    .total {
        text-align: right;
        font-size: 20px;
        margin-top: 20px;
        padding: 10px;
        background-color: white;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    }

    footer {
        color: white;
        padding: 20px;
        width: 100%;
        text-align: center;
        margin-top: auto;
    }

    .text-right {
        text-align: right;
    }

    .btn-success {
        background-color: #28a745;
        color: white;
        border: none;
        padding: 10px 20px;
        text-decoration: none;
        border-radius: 5px;
    }

    .btn-success:hover {
        background-color: #218838;
    }

    /* Popup Styles */
    .popup-overlay {
        display: none;
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background: rgba(0, 0, 0, 0.5);
        z-index: 1000;
        align-items: center;
        justify-content: center;
    }

    .popup-content {
        background-color: #fff;
        border-radius: 10px;
        padding: 20px;
        max-width: 400px;
        width: 90%;
        text-align: center;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
    }

    .popup-header {
        font-size: 18px;
        font-weight: bold;
        color: #dc3545;
        margin-bottom: 10px;
    }

    .popup-body {
        font-size: 16px;
        color: #333;
        margin-bottom: 20px;
    }

    .popup-footer {
        text-align: right;
    }

    .popup-button {
        background-color: #007bff;
        color: white;
        border: none;
        padding: 10px 20px;
        cursor: pointer;
        border-radius: 5px;
    }

    .popup-button:hover {
        background-color: #0056b3;
    }

    .btn-container {
        display: flex;
        justify-content: space-between;
        margin-top: 20px;
    }

    .btn-container a {
        background-color: #007bff;
        color: white;
        text-decoration: none;
        padding: 10px 20px;
        border-radius: 5px;
        margin-right: 10px;
        text-align: center;
    }

    .btn-container a:hover {
        background-color: #0056b3;
    }

    .btn-container .btn-secondary {
        background-color: #6c757d;
    }

    .btn-container .btn-secondary:hover {
        background-color: #5a6268;
    }
    
footer {
	bottom: 0;
	color: white;
	padding: 0px;
	width: 100%;
	text-align: center;
	margin-top: auto;
}
</style>
<script>
    function showPopup(message) {
        var overlay = document.createElement('div');
        overlay.className = 'popup-overlay';
        overlay.innerHTML = `
            <div class="popup-content">
                <div class="popup-header">Out of Stock</div>
                <div class="popup-body">${message}</div>
                <div class="popup-footer">
                    <button class="popup-button" onclick="closePopup()">OK</button>
                </div>
            </div>
        `;
        document.body.appendChild(overlay);
        overlay.style.display = 'flex';
    }

    function closePopup() {
        var overlay = document.querySelector('.popup-overlay');
        if (overlay) {
            overlay.style.display = 'none';
            document.body.removeChild(overlay);
        }
    }

    function validateQuantity(form) {
        var stockQuantity = parseInt(form.dataset.stock, 10); // Fetch stock quantity from data attribute
        var quantityInput = form.querySelector('input[name="quantity"]');
        var quantity = parseInt(quantityInput.value, 10);

        if (quantity > stockQuantity) {
            showPopup("Cannot add more than " + stockQuantity + " items to the cart. Only " + stockQuantity + " items are in stock.");
            quantityInput.value = stockQuantity; // Set the quantity to max stock quantity
            return false; // Prevent form submission
        }
        return true; // Allow form submission
    }

    document.addEventListener("DOMContentLoaded", function() {
        var forms = document.querySelectorAll('form[action$="/cart"]');
        forms.forEach(function(form) {
            form.addEventListener('submit', function(event) {
                if (!validateQuantity(form)) {
                    event.preventDefault(); // Prevent form submission if validation fails
                }
            });
        });
    });
</script>
</head>
<body>
    <header>
        <jsp:include page="insideuserheader.jsp"></jsp:include>
    </header><br><br><br><br>
    <div class="container">
        <h1>Your Cart</h1>

        <c:choose>
            <c:when test="${not empty sessionScope.cart}">
                <table>
                    <thead>
                        <tr>
                            <th style="text-align: center;">Medicine Name</th>
                            <th style="text-align: center;">Price</th>
                            <th style="text-align: center;">Quantity</th>
                            <th style="text-align: center;">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${sessionScope.cart}">
                            <tr>
                                <td><c:out value="${item.medicine.medicineName}" /></td>
                                <td>Rs <c:out value="${item.price}" /></td>
                                <td>
                                    <!-- Decrease Quantity -->
                                    <form action="${pageContext.request.contextPath}/cart" method="get" style="display: inline;" data-stock="${item.medicine.medicineStockQuantity}">
                                        <input type="hidden" name="action" value="update">
                                        <input type="hidden" name="medicineId" value="${item.medicineId}">
                                        <input type="hidden" name="quantity" value="${item.quantity - 1}">
                                        <button type="submit" ${item.quantity <= 1 ? 'disabled' : ''}>-</button>
                                    </form>
                                    <!-- Quantity Display -->
                                    <input type="number" value="${item.quantity}" readonly>
                                    <!-- Increase Quantity -->
                                    <form action="${pageContext.request.contextPath}/cart" method="get" style="display: inline;" data-stock="${item.medicine.medicineStockQuantity}">
                                        <input type="hidden" name="action" value="update">
                                        <input type="hidden" name="medicineId" value="${item.medicineId}">
                                        <input type="hidden" name="quantity" value="${item.quantity + 1}">
                                        <button type="submit">+</button>
                                    </form>
                                </td>
                                <td class="actions">
                                    <!-- Remove Item -->
                                    <form action="${pageContext.request.contextPath}/cart" method="get" style="display: inline;">
                                        <input type="hidden" name="action" value="remove">
                                        <input type="hidden" name="medicineId" value="${item.medicineId}">
                                        <button type="submit" class="remove-button">Remove</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="total">
                    <c:set var="totalAmount" value="0" />
                    <c:forEach var="item" items="${sessionScope.cart}">
                        <c:set var="totalAmount" value="${totalAmount + item.price * item.quantity}" />
                    </c:forEach>
                    <p>Total Amount: Rs <c:out value="${totalAmount}" /></p>
                </div>
            </c:when>
            <c:otherwise>
                <p>Your cart is empty.</p>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="container">
        <div class="btn-container">
            <a href="user-home" class="btn-secondary">Medicine Page</a>
            <a href="payment.jsp" class="btn-success">Proceed to Payment</a>
        </div>
    </div>
    <footer>
        <jsp:include page="footer.jsp"></jsp:include>
    </footer>
</body>
</html>
