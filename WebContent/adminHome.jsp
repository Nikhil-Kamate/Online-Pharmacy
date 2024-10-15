<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html">
<title>Admin Panel</title>
<link href="css/adminHome.css" rel="stylesheet" />
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
<style>
    body {
        background-image: url("image/a.jpg");
        background-size: cover;
        background-position: center;
        background-repeat: no-repeat; 
        font-family: Arial, sans-serif;
        text-align: center;
        margin: 0;
        padding: 0;
        display: flex;
        flex-direction: column;
        min-height: 100vh;
    }

    /* Applying blur effect to the background image */
    body::before {
        content: '';
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background-image: inherit;
        background-size: cover;
        background-position: center;
        background-repeat: no-repeat;
        filter: blur(2px); /* Adds blur to the background */
        z-index: -1;
    }

    .header {
        width: 100%;
        height: 80px;
        background-color: #fff;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 0 20px;
        position: fixed;
        top: 0;
        left: 0;
        z-index: 1000;
    }

    .main-container {
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
        height: calc(100vh - 120px);
        position: relative;
        z-index: 100; /* To ensure content appears above the blurred background */
    }

    .button-container {
        display: flex;
        align-items: center;
        justify-content: center;
        background-color: hsl(220, 90%, 27%); /* Slightly transparent white background */
        padding: 30px; /* Padding inside the box */
        border-radius: 10px; /* Rounded corners for the box */
        box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1); /* Shadow effect for the box */
        margin-top: 5px; /* Margin above the box */
    }

    .button {
        display: flex;
        align-items: center;
        justify-content: center;
        padding: 30px 70px; /* Increased button height */
        font-size: 20px;
        border: none;
        cursor: pointer;
        border-radius: 8px;
        box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
        transition: transform 0.2s, box-shadow 0.2s;
        margin: 0 20px;
    }

    .button .fa {
        margin-right: 10px; /* Space between the icon and the text */
        font-size: 24px; /* Adjust size of the icon */
    }

    .button:hover {
        transform: scale(1.05);
        box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
    }

    .add-medicine {
        background-color: #28a745;
        color: white;
    }

    .display-medicines {
        background-color: #007bff;
        color: white;
    }

    .vertical-divider {
        width: 4px;
        height: 100px; /* Height of the divider line */
        background-color: #ddd;
        margin: 0 20px;
        border-radius: 2px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* Shaded effect */
    }

    .welcome-text {
        font-size: 28px;
        margin-bottom: 40px; /* Adds more space between the text and the buttons */
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
<body>
    <header class="header">
        <jsp:include page="insiderheader.jsp"></jsp:include>
    </header><br><br><br>

    <div class="main-container">
        <h1 class="welcome-text">Welcome, Admin</h1>
        
        <div class="button-container"> 
            <a href="addMedicine.jsp" class="button add-medicine">
                <i class="fa fa-plus"></i> Add Medicine
            </a>
            <!-- Vertical divider line -->
            <div class="vertical-divider"></div>
            <a href="DisplayMedicinesServlet" class="button display-medicines">
                <i class="fa fa-list"></i> Display Medicines
            </a>
        </div>
    </div>
    <footer>
        <jsp:include page="footer.jsp"></jsp:include>
    </footer>
</body>
</html>
