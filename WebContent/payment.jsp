<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Payment</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }
        .container {
            flex: 1;
        }
        .payment-container {
            padding: 20px;
            margin: 30px auto;
            max-width: 600px; /* Reduced the width of the container */
            background-color: #f9f9f9;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .total {
            font-size: 1.2em;
            font-weight: bold;
            margin-top: 15px;
        }
        .card-field {
            display: none; /* Hidden by default */
        }
        .cart-icon, .form-icon {
            margin-right: 5px;
        }
        footer {
            bottom: 0;
            color: white;
            padding: 1px 0;
            width: 100%;
            text-align: center;
            margin-top: auto; /* Pushes footer to the bottom dynamically */
        }
        .form-group label {
            font-weight: bold;
        }
        .form-control.error {
            border-color: red;
        }
        .error-message {
            color: red;
            font-size: 0.9em;
        }
        .custom-select {
            position: relative;
        }
        .custom-select .form-control {
            padding-right: 2.5rem;
        }
        .custom-select::after {
            content: "\f0d7"; /* FontAwesome down arrow */
            font-family: "Font Awesome 5 Free";
            font-weight: 900;
            position: absolute;
            right: 10px;
            top: 50%;
            transform: translateY(-50%);
            color: #495057;
        }
    </style>
    <script>
        function toggleCardFields() {
            var paymentMethod = document.getElementById('paymentMethod').value;
            var cardFields = document.getElementById('cardFields');
            if (paymentMethod === 'Credit Card' || paymentMethod === 'RuPay' || paymentMethod === 'Visa') {
                cardFields.style.display = 'block'; // Show card fields
            } else {
                cardFields.style.display = 'none'; // Hide card fields
            }
        }

        function validateForm() {
            var cardNumber = document.getElementById('cardNumber').value;
            var expiryDate = document.getElementById('expiryDate').value;
            var cvv = document.getElementById('cvv').value;
            var isValid = true;

            // Validate Card Number
            if (cardNumber.length !== 16 || isNaN(cardNumber)) {
                document.getElementById('cardNumber').classList.add('error');
                document.getElementById('cardNumberError').textContent = 'Card number must be 16 digits.';
                isValid = false;
            } else {
                document.getElementById('cardNumber').classList.remove('error');
                document.getElementById('cardNumberError').textContent = '';
            }

            // Validate Expiry Date
            var today = new Date();
            var [month, year] = expiryDate.split('/');
            var expiry = new Date('20' + year, month - 1);
            if (!expiryDate.match(/^(0[1-9]|1[0-2])\/([0-9]{2})$/) || expiry < today) {
                document.getElementById('expiryDate').classList.add('error');
                document.getElementById('expiryDateError').textContent = 'Expiry date must be in MM/YY format and not in the past and months should be [1-12].';
                isValid = false;
            } else {
                document.getElementById('expiryDate').classList.remove('error');
                document.getElementById('expiryDateError').textContent = '';
            }

            // Validate CVV
            if (cvv.length !== 3 || isNaN(cvv)) {
                document.getElementById('cvv').classList.add('error');
                document.getElementById('cvvError').textContent = 'CVV must be 3 digits.';
                isValid = false;
            } else {
                document.getElementById('cvv').classList.remove('error');
                document.getElementById('cvvError').textContent = '';
            }

            return isValid;
        }
    </script>
</head>
<body>
    <header>
        <jsp:include page="insideuserheader.jsp"></jsp:include>
    </header>
	<br><br><br>
    <div class="container">
        <div class="payment-container">
            <h2 class="text-center mb-4">Your Cart</h2>
            <!-- Display cart items -->
            <c:forEach var="item" items="${cart}">
                <div class="row mb-3">
                    <div class="col-md-12">
                        <h5><i class="fa fa-capsules cart-icon"></i>${item.medicine.medicineName}</h5>
                        <p><i class="fa fa-dollar-sign cart-icon"></i>Price: Rs ${item.getPrice()}</p>
                        <p><i class="fa fa-cube cart-icon"></i>Quantity: ${item.getQuantity()}</p>
                    </div>
                </div>
            </c:forEach>
            
            <hr>
            
            <!-- Display total amount -->
            <div class="total">
                <c:set var="totalAmount" value="0" />
                <c:forEach var="item" items="${cart}">
                    <c:set var="totalAmount" value="${totalAmount + item.getPrice() * item.getQuantity()}" />
                </c:forEach>
                <p>Total Amount: Rs <c:out value="${totalAmount}" /></p>
            </div>

            <!-- Payment form -->
            <form action="processPayment" method="post" class="payment-form mt-4" onsubmit="return validateForm()">
                <input type="hidden" name="amount" value="${totalAmount}" />
                
                <!-- Payment Method -->
                <div class="form-group">
                    <label for="paymentMethod">Payment Method</label>
                    <div class="custom-select">
                        <select id="paymentMethod" name="paymentMethod" class="form-control" onchange="toggleCardFields()">
                            <option value="">Select Payment Method</option>
                            <option value="Credit Card">Credit Card</option>
                            <option value="RuPay">RuPay</option>
                            <option value="Visa">Visa</option>
                        </select>
                    </div>
                </div>

                <!-- Card Details Fields -->
                <div id="cardFields" class="card-field">
                    <div class="form-group">
                        <label for="cardNumber"><i class="fa fa-credit-card form-icon"></i>Card Number</label>
                        <input type="text" id="cardNumber" name="cardNumber" class="form-control" placeholder="Enter your card number">
                        <div id="cardNumberError" class="error-message"></div>
                    </div>
                    <div class="form-group">
                        <label for="expiryDate"><i class="fa fa-calendar form-icon"></i>Expiry Date</label>
                        <input type="text" id="expiryDate" name="expiryDate" class="form-control" placeholder="MM/YY">
                        <div id="expiryDateError" class="error-message"></div>
                    </div>
                    <div class="form-group">
                        <label for="cvv"><i class="fa fa-lock form-icon"></i>CVV</label>
                        <input type="password" id="cvv" name="cvv" class="form-control" placeholder="CVV">
                        <div id="cvvError" class="error-message"></div>
                    </div>
                </div>

                <!-- Hidden field for payment status -->
                <input type="hidden" name="paymentStatus" value="Pending" />
                
                <!-- Submit Button -->
                <button type="submit" class="btn btn-primary btn-block">Proceed to Payment</button>
            </form>
        </div>
    </div>
<br>
    <footer>
        <jsp:include page="footer.jsp"></jsp:include>
    </footer>
</body>
</html>
