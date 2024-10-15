<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="com.crimsonlogic.model.Order"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order History</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	display: flex;
	flex-direction: column;
	min-height: 100vh;
}

.container {
	margin-top: 80px;
}

footer {
	bottom: 0;
	color: white;
	padding: 0px;
	width: 100%;
	text-align: center;
	margin-top: auto;
}

.table thead th {
	background-color: #007bff;
	color: white;
}
</style>
</head>
<body>
	<header>
		<jsp:include page="insideuserheader.jsp"></jsp:include>
	</header>

	<div class="container">
    <h1 style="text-align: center; color: blue; margin-top: 2rem; margin-bottom: 2rem;">
        Order History
    </h1>

    <!-- Display any error messages -->
    <% 
        if (request.getAttribute("error") != null) {
    %>
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        <%=request.getAttribute("error")%>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
    <% 
        }
    %>

    <!-- Check if there are orders to display -->
    <% 
        List<Order> orderHistory = (List<Order>) request.getAttribute("orderHistory");
        if (orderHistory != null && !orderHistory.isEmpty()) {
    %>
    <table class="table table-striped table-bordered">
        <thead>
            <tr>
                <th>Order ID</th>
                <th>Order Date</th>
                <th>Total Amount</th>
            </tr>
        </thead>
        <tbody>
            <% 
                for (Order order : orderHistory) {
            %>
            <tr>
                <td><%=order.getOrderId()%></td>
                <td><%=order.getOrderDate()%></td>
                <td>Rs <%=String.format("%.2f", order.getTotalAmount())%></td>
            </tr>
            <% 
                }
            %>
        </tbody>
    </table>
    <% 
        } else {
    %>
    <div class="alert alert-info">You have no orders in your history.</div>
    <% 
        }
    %>

    <!-- Navigation Links -->
    <div class="mt-4 d-flex justify-content-end">
        <a href="user-home" class="btn btn-primary me-2">
        <i class="fas fa-home"></i> Back to Home</a>
        <a href="cart.jsp" class="btn btn-warning">
         <i class="fas fa-shopping-cart"></i> View Cart</a>
    </div>
</div>
	

	<footer>
		<jsp:include page="footer.jsp"></jsp:include>
	</footer>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
