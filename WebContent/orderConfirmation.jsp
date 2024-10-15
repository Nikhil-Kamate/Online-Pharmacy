<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Confirmation</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <style>
        body {
            display: flex;
            flex-direction: column;
            min-height: 100vh;
            background-color: #f4f4f9;
            
        }
        
        .confirmation-container {
            padding: 30px;
            margin: 30px auto;
            max-width: 600px;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border: 1px solid #ddd;
        }

        .confirmation-header {
            margin-bottom: 20px;
        }

        .confirmation-header h1 {
            color: #28a745; /* Green color for success message */
        }

        .order-summary {
            margin-top: 20px;
        }

        .order-summary h3 {
            margin-bottom: 15px;
            color: #333;
            border-bottom: 2px solid #28a745;
            padding-bottom: 10px;
        }

        .order-summary p {
            margin: 10px 0;
            font-size: 1.1em;
        }

        .order-summary p strong {
            color: #555;
        }

        .success-message {
            color: #28a745;
            font-size: 1.5em;
            font-weight: bold;
        }

        .text-center a {
            text-decoration: none;
            background-color: #007bff;
            color: #ffffff;
            padding: 10px 20px;
            border-radius: 5px;
            font-size: 1.1em;
        }

        .text-center a:hover {
            background-color: #0056b3;
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
    <!-- Favicon Link -->
    <link rel="icon" type="image/x-icon" href="/path-to-your-favicon/favicon.ico">
</head>
<body>
    <header>
        <jsp:include page="insideuserheader.jsp"></jsp:include>
    </header>
    <br><br><br><br>
    <div class="container">
        <div class="confirmation-container">
            <div class="confirmation-header text-center">
                <h1 class="success-message"><i class="fa-solid fa-check-circle"></i> Thank You for Your Order!</h1>
                <p>Your order has been successfully placed. Below are the details of your order.</p>
            </div>

            <div class="order-summary">
                <h3>Order Summary</h3>
                <p><strong>Order ID:</strong> ${param.orderId}</p>
                <p><strong>Total Amount:</strong> Rs ${param.totalAmount}</p>
                <p><strong>Payment Method:</strong> ${param.paymentMethod}</p>
                <p><strong>Payment Status:</strong> ${param.paymentStatus}</p>                
            </div>

            <div class="text-center mt-4">
                <a href="user-home" class="btn btn-primary">Return to Home</a>
            </div>
        </div>
    </div>
    <footer>
        <jsp:include page="footer.jsp"></jsp:include>
    </footer>
</body>
</html>
