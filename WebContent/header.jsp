<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Header</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<link href="css/header.css" rel="stylesheet"/>

<!-- Font Awesome for Icons -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" integrity="sha384-5r7G0NU0qVs1VO9i3JErBz75wZ4q+HqWmi6kF/ThJxP8/7LPS8hWxRl02SeyckpD" crossorigin="anonymous">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
        <div class="container-fluid">
            <!-- Left side: Logo -->
            <a class="navbar-brand" href="login.jsp">
                <img src="image/logo.jpg" alt="Logo">
            </a>

            <!-- Right side: Sign In | Sign Up -->
            <div class="d-flex align-items-center">
                <a href="login.jsp" class="nav-link">
                    <i class="fas fa-sign-in-alt"></i> Sign In
                </a>
                <span class="mx-2">|</span>
                <a href="register.jsp" class="nav-link">
                    <i class="fas fa-user-plus"></i> Sign Up
                </a>
            </div>
        </div>
    </nav>
</body>
</html>
