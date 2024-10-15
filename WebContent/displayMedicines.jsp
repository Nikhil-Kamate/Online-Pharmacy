<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Medicines List</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="css/displayMedicine.css" rel="stylesheet" />

<style>
    .actions-column {
        width: 150px; /* Adjust width as needed */
    }

    .btn-sm {
        width: 70px; /* Set a fixed width for buttons */
        height: 35px; /* Set a fixed height for buttons */
    }
    
    .search-container {
        max-width: 400px; /* Adjust the max width of the search bar */
        margin: 0 auto; /* Center the search bar horizontally */
    }
</style>
</head>
<body>

<header>
    <jsp:include page="insiderheader.jsp"></jsp:include>
</header>
<br><br><br><br><br>

<!-- Search Field and Button Centered -->
<div class="container search-container">
    <form class="d-flex" action="SearchMedicineServlet" method="get">
        <input class="form-control me-2" type="search" name="medicineName"
               placeholder="Search by Medicine Name" aria-label="Search">
        <button class="btn btn-outline-primary" type="submit">
            <i class="bi bi-search"></i> Search
        </button>
    </form>
</div>

<div class="container">
    <h2>Medicines List</h2>
    <br>
    <div class="table-responsive">
        <table class="table">
            <thead>
                <tr>
                    <th class="photo-column">Photo</th>
                    <th class="name-column">Name</th>
                    <th class="price-column">Price</th>
                    <th class="stock-column">Stock Quantity</th>
                    <th class="manufactured-date-column">Manufactured Date</th>
                    <th class="expire-date-column">Expire Date</th>
                    <th class="actions-column">Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="medicine" items="${medicines}">
                    <tr>
                        <td><img
                            src="${pageContext.request.contextPath}/uploads/${medicine.medicinePhotoPath}"
                            class="img-fluid" alt="${medicine.medicineName}"></td>
                        <td>${medicine.medicineName}</td>
                        <td>${medicine.medicinePrice}</td>
                        <td>${medicine.medicineStockQuantity}</td>
                        <td>${medicine.medicineManufacturedDate}</td>
                        <td>${medicine.medicineExpireDate}</td>
                        <td>
                            <div class="d-flex justify-content-between">
                                <a href="EditMedicineServlet?medicineId=${medicine.medicineId}"
                                    class="btn btn-warning btn-sm">Edit</a>
                                <%-- <a href="DeleteMedicineServlet?medicineId=${medicine.medicineId}"
                                    class="btn btn-danger btn-sm"
                                    onclick="return confirm('Are you sure you want to delete this medicine?');">Delete</a> --%>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<footer>
    <jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>
</html>
