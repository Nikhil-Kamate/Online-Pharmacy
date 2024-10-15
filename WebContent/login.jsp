<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet"> 
</head>
<body>
    <!-- Header Section -->
    <header>
        <jsp:include page="header.jsp"></jsp:include>
    </header>
    
    <!-- Main Content Section -->
    <div class="container d-flex justify-content-center align-items-center" style="min-height: 100vh;">
        <main class="py-5">
            <section class="py-5">
                <div class="container">
                    <div id="loginBox" class="boxContainer text-center p-3 p-md-5 rounded-2">
                        <h1 class="my-5" style="color: white;">Login</h1>
                       
                        <!-- Display error message if present -->
                        <% 
                            String errorMessage = request.getParameter("error"); 
                            if (errorMessage != null) { 
                        %>
                            <div class="alert alert-danger" role="alert">
                                <%= errorMessage %>
                            </div>
                        <% 
                            } 
                        %>

                        <!-- Login form -->
                        <form action="Login" method="post" class="inputsForm">
                            <div class="mb-3">
                                <!-- <label for="user_email" style="color: white; text-align: left; display: block;">Enter Email :</label> -->
                                <div class="input-group">
                                    <span class="input-group-text"><i class="fas fa-envelope"></i></span>
                                    <input
                                        type="email"
                                        id="user_email"
                                        name="user_email"
                                        class="form-control"
                                        placeholder="Enter your email"
                                        required
                                    />
                                </div>
                            </div>
                            <div class="mb-3">
                               <!--  <label for="user_password" style="color: white; text-align: left; display: block;">Enter Password :</label> -->
                                <div class="input-group">
                                    <span class="input-group-text"><i class="fas fa-lock"></i></span>
                                    <input
                                        type="password"
                                        id="user_password"
                                        name="user_password"
                                        class="form-control"
                                        placeholder="Enter your password"
                                        required
                                    />
                                </div>
                            </div>
                            
                            <button type="submit" id="loginBtn" class="btn btn-main my-4 w-100">
                                <b>Log in</b>
                            </button>
                            <div class="signupText d-flex justify-content-center gap-2 mt-1">
                                <p style="color: white;">Do not have an account?</p>
                                <a class="text-decoration-none" href="register.jsp">Sign up</a>
                            </div>
                        </form>
                    </div>
                </div>
            </section>
        </main>
    </div>
    <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
