<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Medicine</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
           
            align-items: center;
            justify-content: center;
            min-height: 100vh;
            margin: 0;
            background-color: #f8f9fa;
            display: flex;
	flex-direction: column;
	min-height: 100vh;
        }
        .card {
            max-width: 800px; /* Adjust the width of the card */
            width: 100%;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            padding: 20px;
            background-color: #fff;
        }
        .form-group {
            margin-bottom: 1.5rem;
        }
        .form-row {
            margin-bottom: 1rem;
        }
        .form-control {
            border-radius: 4px;
        }
        .btn-container {
            display: flex;
            justify-content: space-between;
        }
        .btn-container .btn {
            flex: 1;
            margin: 0 5px;
        }
        .btn-update {
            background-color: #28a745;
            border-color: #28a745;
            color: #fff;
        }
        .btn-update:hover {
            background-color: #218838;
            border-color: #1e7e34;
        }
        .btn-cancel {
            background-color: #6c757d;
            border-color: #6c757d;
            color: #fff;
        }
        .btn-cancel:hover {
            background-color: #5a6268;
            border-color: #545b62;
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
</head>
<body >


<header>
    <jsp:include page="insiderheader.jsp"></jsp:include>
</header>
<br><br><br><br><br><br>
<div class="card">
    <h2 style="text-align: center; color : Blue; ">Edit Medicine</h2><br>
    
    <!-- Display error message if any -->
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <!-- Edit Medicine Form -->
    <form action="EditMedicineServlet" method="post">
        <input type="hidden" name="medicineId" value="${medicine.medicineId}">
        
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="medicineName"><b>Medicine Name:</b></label>
                <input type="text" class="form-control" id="medicineName" name="medicineName" value="${medicine.medicineName}" readonly>
            </div>
            <div class="form-group col-md-6">
                <label for="medicineCategory"><b>Category:</b></label>
                <input type="text" class="form-control" id="medicineCategory" name="medicineCategory" value="${medicine.medicineCategory}" readonly>
            </div>
        </div>

        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="medicineDescription"><b>Description:</b></label>
                <textarea class="form-control" id="medicineDescription" name="medicineDescription" readonly>${medicine.medicineDescription}</textarea>
            </div>
            <div class="form-group col-md-6">
                <label for="medicinePrice"><b>Price:</b></label>
                <input type="number" step="0.01" class="form-control" id="medicinePrice" name="medicinePrice" value="${medicine.medicinePrice}" required>
            </div>
        </div>

        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="medicineStockQuantity"><b>Stock Quantity:</b></label>
                <input type="number" class="form-control" id="medicineStockQuantity" name="medicineStockQuantity" value="${medicine.medicineStockQuantity}" required>
            </div>
            <div class="form-group col-md-6">
                <label for="medicineCompany"><b>Company:</b></label>
                <input type="text" class="form-control" id="medicineCompany" name="medicineCompany" value="${medicine.medicineCompany}" readonly>
            </div>
        </div>

        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="medicineManufacturedDate"><b>Manufactured Date:</b></label>
                <input type="date" class="form-control" id="medicineManufacturedDate" name="medicineManufacturedDate" value="${medicine.medicineManufacturedDate}" readonly>
            </div>
            <div class="form-group col-md-6">
                <label for="medicineExpireDate"><b>Expire Date:</b></label>
                <input type="date" class="form-control" id="medicineExpireDate" name="medicineExpireDate" value="${medicine.medicineExpireDate}" readonly>
            </div>
        </div>

        <!-- Photo is not included in this form to ensure it is not editable -->

        <div class="btn-container">
            <button type="submit" class="btn btn-success" >Update Medicine</button>
            <a href="DisplayMedicinesServlet" class="btn btn-warning">Cancel</a>
        </div>
    </form>
</div>
<br><br><br>
<footer>
    <jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>
</html>
