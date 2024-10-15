<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Home</title>
<link rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
<style>
body {
    display: flex;
    flex-direction: column;
    min-height: 100vh;
}

.blurred-header-container {
    position: relative;
    width: 100%;
    height: 60vh; /* Adjust height as needed */
    overflow: hidden; /* Ensure no content overflows */
}

.blurred-header-container img {
    width: 100%;
    height: 100%;
    object-fit: cover; /* Ensure the image covers the container */
    filter: blur(2px);
    position: absolute;
    top: 0;
    left: 0;
}

.blurred-header-content {
    position: absolute;
    /* Positioned absolutely to be on top of the blurred image */
    width: 80%;
    max-width: 600px; /* Optional: max width for better appearance */
    height: auto;
    display: flex;
    align-items: center; /* Aligns items in the center vertically */
    justify-content: center;
    text-align: center;
    color: white;
    z-index: 2; /* Ensure content is above the image */
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background: rgba(0, 0, 0, 0.5);
    /* Semi-transparent background for better visibility */
    padding: 20px; /* Added padding for better spacing */
    box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.5); /* Added shadow effect */
    border-radius: 10px; /* Rounded corners */
}

.blurred-header-content form {
    display: flex;
    align-items: center;
    /* Aligns the button and search field on the same line */
}

.blurred-header-content input[type="search"] {
    width: 400px; /* Increased width of the search field */
    height: 50px; /* Increased height of the search field */
    margin-right: 10px; /* Spacing between the search field and button */
    border-radius: 5px;
    padding: 10px 40px 10px 40px; /* Extra padding on the left for icon */
    background: white;
    position: relative; /* For absolute positioning of the icon */
}

.blurred-header-content input[type="search"]::placeholder {
    color: #999;
}

.blurred-header-content input[type="search"]::before {
    content: '\f002'; /* Font Awesome search icon unicode */
    font-family: 'Font Awesome 5 Free'; /* Font Awesome 5 Free Font */
    font-weight: 900; /* Font Awesome 5 Free Font */
    position: absolute;
    left: 10px;
    top: 50%;
    transform: translateY(-50%);
    color: #6c757d;
    font-size: 1.2rem;
}

.blurred-header-content button {
    background-color: #007bff; /* Button background color */
    color: white;
    border: none;
    padding: 10px 20px;
    height: 50px; /* Matches the height of the search field */
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
    /* Smooth transition for hover effect */
}

.blurred-header-content button:hover {
    background-color: #0056b3; /* Button background color on hover */
}

.medicine-card {
    margin: 15px;
    padding: 20px;
    border: 1px solid #ddd;
    border-radius: 5px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    display: flex;
    flex-direction: column;
    align-items: center;
    text-align: center;
}

.medicine-card img {
    width: 100%; /* Ensure the image covers the width of the card */
    height: 200px; /* Set a consistent height for the image */
    object-fit: cover; /* Maintain aspect ratio and cover the area */
    border-radius: 5px; /* Optional: rounded corners for the image */
    margin-bottom: 15px; /* Space between the image and text */
}

.cart-icon {
    position: relative;
    display: inline-block;
}

.cart-count {
    position: absolute;
    top: -5px;
    right: -10px;
    background: red;
    color: white;
    border-radius: 50%;
    padding: 2px 6px;
    font-size: 12px;
}

.main-content {
    margin-top: 70px;
    /* Adjust this margin to match the height of your fixed header */
}

footer {
    bottom: 0;
    color: white;
    padding: 0px;
    width: 100%;
    text-align: center;
    margin-top: auto;
}

.out-of-stock {
    color: red;
    font-weight: bold;
}
</style>
</head>
<body>
    <header>
        <jsp:include page="insideuserheader.jsp"></jsp:include>
    </header>

    <div class="blurred-header-container">
        <img src="image/a.jpg" alt="Background Image">
        <div class="blurred-header-content">
            <form action="SearchMedicineServlet" method="get">
                <input class="form-control" type="search" name="medicineName"
                    placeholder="Search by Medicine Name" aria-label="Search">
                <button type="submit">
                    <i class="fa fa-search"></i> Search
                </button>
            </form>
        </div>
    </div>

    <div class="container mt-4 main-content">
        <h2 class="my-4" style="color: blue; text-align: center;">Available Medicines</h2>

        <div class="row">
            <c:forEach var="medicine" items="${medicines}">
                <div class="col-md-4">
                    <div class="medicine-card">
                        <img
                            src="${pageContext.request.contextPath}/uploads/${medicine.medicinePhotoPath}"
                            class="img-fluid" alt="${medicine.medicineName}">
                        <h5>${medicine.medicineName}</h5>
                        <p>${medicine.medicineDescription}</p>
                        <p>Price: Rs ${medicine.medicinePrice}</p>
                        <p>Medicines in stock : ${medicine.medicineStockQuantity}</p>

                        <c:choose>
                            <c:when test="${medicine.medicineStockQuantity > 0}">
                                <form action="cart?medicineId=${medicine.medicineId}" method="get">
                                    <input type="hidden" name="action" value="add"> 
                                    <input type="hidden" name="medicineId" value="${medicine.medicineId}">
                                    <input type="hidden" name="price" value="${medicine.medicinePrice}"> 
                                    <input type="number" name="quantity" value="1" min="1" style="width: 50px; padding: 5px;">
                                    <button type="submit" class="btn btn-primary">Add to Cart</button>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <p class="out-of-stock">Out of Stock</p>
                                <button class="btn btn-secondary" disabled>Add to Cart</button>
                            </c:otherwise>
                        </c:choose>

                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <footer>
        <jsp:include page="footer.jsp"></jsp:include>
    </footer>
</body>
</html>