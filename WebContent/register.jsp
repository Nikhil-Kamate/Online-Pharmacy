<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Registration</title>
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
    crossorigin="anonymous">
<script
    src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
    crossorigin="anonymous"></script>
<link
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"
    rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
</head>
<body>
    <!-- Header Section -->
    <header> <jsp:include page="header.jsp"></jsp:include> </header>

    <!-- Main Content Section -->
    <div class="container d-flex justify-content-center align-items-center"
        style="min-height: 100vh;">
        <main class="py-5"> 
            <section class="py-5">
                <div class="container">
                    <div id="registerBox"
                        class="boxContainer text-center p-3 p-md-5 rounded-2">
                        <h1 class="my-5" style="color: white;">Register Yourself</h1>

                        <!-- Display error message if present -->
                        <%
                            String errorMessage = request.getParameter("error");
                            if (errorMessage != null) {
                        %>
                        <div class="alert alert-danger" role="alert">
                            <%=errorMessage%>
                        </div>
                        <%
                            }
                        %>

                        <!-- Registration form -->
                        <form action="Register" method="post" class="inputsForm"
                            id="registrationForm" novalidate>
                            <div class="row">
                                <div class="col-md-6 mb-4">
                                    <div class="input-group">
                                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                                        <input type="text" id="user_firstName" name="user_firstName"
                                            class="form-control" placeholder="Enter your first name"
                                            required pattern="[A-Za-z]{3,}" />
                                        <div class="invalid-feedback">First name must be at least
                                            3 characters long and contain only letters.</div>
                                    </div>
                                </div>

                                <div class="col-md-6 mb-4">
                                    <div class="input-group">
                                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                                        <input type="text" id="user_lastName" name="user_lastName"
                                            class="form-control" placeholder="Enter your last name"
                                            required />
                                    </div>
                                </div>
                            </div>

                            <div class="mb-4">
                                <div class="input-group">
                                    <span class="input-group-text"><i class="fas fa-envelope"></i></span>
                                    <input type="email" id="user_email" name="user_email"
                                        class="form-control" placeholder="Enter your email" required
                                        pattern="[a-zA-Z0-9._%+-]+@gmail\.com" />
                                </div>
                                <div class="invalid-feedback">Please enter a valid Gmail
                                    address.</div>
                            </div>

                            <div class="mb-4">
                                <div class="input-group">
                                    <span class="input-group-text"><i class="fas fa-lock"></i></span>
                                    <input type="password" id="user_password" name="user_password"
                                        class="form-control" placeholder="Enter your password" required
                                        pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" />
                                </div>
                                <div class="invalid-feedback">Password must be at least 6
                                    characters long and include at least one number, one lowercase
                                    letter, and one uppercase letter.</div>
                            </div>

                            <div class="mb-4">
                                <div class="input-group">
                                    <span class="input-group-text"><i class="fas fa-phone"></i></span>
                                    <input type="tel" id="user_phoneNo" name="user_phoneNo"
                                        class="form-control" placeholder="Enter your phone number"
                                        required pattern="[6-9][0-9]{9}" />
                                </div>
                                <div class="invalid-feedback">Phone number must be 10 digits
                                    long and start with 6-9.</div>
                            </div>

                            <div class="mb-4">
                                <div class="input-group">
                                    <span class="input-group-text"><i class="fas fa-map-marker-alt"></i></span>
                                    <textarea id="user_address" name="user_address"
                                        class="form-control" placeholder="Enter your address" rows="3"></textarea>
                                </div>
                            </div>

                            <div class="mb-4">
                                <div class="input-group">
                                    <span class="input-group-text"><i class="fas fa-calendar-alt"></i></span>
                                    <input type="date" id="user_dateofBirth" name="user_dateofBirth"
                                           class="form-control" required
                                           max="<%=new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date())%>" />
                                </div>
                                <div id="ageRestrictionMsg" class="invalid-feedback">
                                    Age must be 18 or above.
                                </div>
                            </div>

                            <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                                <button type="submit" class="btn btn-main btn-lg"
                                    style="background-color: #007bff; color: white;">
                                    <b>Register</b>
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </section> 
        </main>
    </div>

    <!-- Footer Section -->
    <jsp:include page="footer.jsp"></jsp:include>

    <script>
        document.getElementById('registrationForm').addEventListener('submit', function(event) {
            var form = event.target;
            var dobInput = document.getElementById('user_dateofBirth').value;
            var dob = new Date(dobInput);
            var today = new Date();
            var age = today.getFullYear() - dob.getFullYear();
            var monthDiff = today.getMonth() - dob.getMonth();
            if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < dob.getDate())) {
                age--;
            }

            var isValid = true; // Flag to track the validity status

            // Check if user is under 18 years
            if (age < 18) {
                event.preventDefault();
                isValid = false;
                document.getElementById('user_dateofBirth').classList.add('is-invalid');
                document.getElementById('ageRestrictionMsg').style.display = 'block';
            } else {
                document.getElementById('user_dateofBirth').classList.remove('is-invalid');
                document.getElementById('ageRestrictionMsg').style.display = 'none';
            }

            // Perform standard form validation
            if (!form.checkValidity()) {
                event.preventDefault();
                isValid = false;
            }

            if (isValid) {
                // Only add 'was-validated' class if all checks are passed
                form.classList.add('was-validated');
            }
        });
    </script>
</body>
</html>
