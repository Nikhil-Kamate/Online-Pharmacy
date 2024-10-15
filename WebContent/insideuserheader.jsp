<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Header</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<link href="css/insiderheader.css" rel="stylesheet"/>
<!-- Font Awesome for Icons -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">

<!-- Custom CSS for Button Colors -->
<style>
    .btn-custom {
        color: white;
        border-radius: 4px;
        border: 2px solid transparent;
        padding: 10px 20px; /* Consistent padding */
        font-size: 16px; /* Consistent font size */
        font-weight: bold;
        text-decoration: none;
    }
    
    .btn-custom-add {
        background-color: #28a745; /* Green background */
        border: 2px solid #218838; /* Border before hover */
    }
    .btn-custom-add:hover {
        background-color: #218838; /* Darker Green on hover */
        border: 2px solid #1e7e34; /* Darker border on hover */
    }
    
    .btn-custom-display {
        background-color: #007bff; /* Blue background */
        border: 2px solid #0069d9; /* Border before hover */
    }
    .btn-custom-display:hover {
        background-color: #0056b3; /* Darker Blue on hover */
        border: 2px solid #004494; /* Darker border on hover */
    }
   
    .btn-custom-logout {
        background-color: #dc3545; /* Red background */
        border: 2px solid #a71d2a; /* Border before hover */
    }
    .btn-custom-logout:hover {
        background-color: #a71d2a; /* Darker Red on hover */
        border: 2px solid #7f1a20; /* Darker border on hover */
    }
</style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
        <div class="container-fluid">
            <!-- Left side: Logo -->
            <a class="navbar-brand" href="user-home">
                <img src="image/logo.jpg" alt="Logo">
            </a>

            <!-- Right side: Buttons -->
            <div class="d-flex align-items-center">
                <!-- Medicines Button -->
                <a href="user-home" class="btn btn-success mx-2">
                    <i class="fas fa-pills"></i> <!-- Medicine Icon -->
                    <b>Medicines</b>
                </a>

                <!-- Order History Button -->
                <a href="order-history" class="btn btn-primary mx-2 btn-custom-display">
                    <i class="fas fa-history"></i> <!-- History Icon -->
                    <b>Order History</b>
                </a>

                <!-- View Cart Button -->
                <a href="cart.jsp" class="btn btn-warning mx-2">
                    <i class="fas fa-shopping-cart"></i> <!-- Cart Icon -->
                    <b>View Cart</b>
                </a>

                <!-- Logout Button -->
                <a href="LogoutServlet" class="btn btn-danger mx-2">
                    <i class="fas fa-sign-out-alt"></i> <!-- Logout Icon -->
                    <b>Logout</b>
                </a>
            </div>
        </div>
    </nav>
</body>
</html>
