<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Header</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<link href="css/insiderheader.css" rel="stylesheet" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
	rel="stylesheet" />

<!-- Custom CSS for Button Styles -->
<style>
.btn-custom {
	color: white;
	border-radius: 4px;
	border: 2px solid transparent;
	padding: 10px 20px;
	font-size: 16px;
	font-weight: bold;
}
</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
	<div class="container-fluid">
		<!-- Left side: Logo -->
		<a class="navbar-brand" href="adminHome.jsp"> <img
			src="image/logo.jpg" alt="Logo">
		</a>

		<!-- Right side: Add Medicine, Display Medicine, Logout Buttons -->
		<div class="d-flex align-items-center">
			<!-- Add Medicine Button -->
			<a href="addMedicine.jsp" class="btn btn-success mx-2"> <i
				class="fas fa-pills"></i> <b>Add Medicine</b>
			</a>

			<!-- Display Medicine Button -->
			<a href="DisplayMedicinesServlet" class="btn btn-info mx-2"> <i
				class="fas fa-clipboard-list"></i> <b>Display Medicine</b>
			</a>

			<!-- Logout Button -->
			<a href="LogoutServlet" class="btn btn-danger mx-2"> <i
				class="fas fa-sign-out-alt"></i> <b>Logout</b>
			</a>
		</div>
	</div>
	</nav>
</body>
</html>