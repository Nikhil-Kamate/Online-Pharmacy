<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Add New Medicine</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            min-height: 100vh; /* Ensure the body takes up at least the full height of the viewport */
        }
        header {
            width: 100%;
            background-color: #007bff;
            color: #fff;
            padding: 15px 0;
            text-align: center;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            position: fixed;
            top: 0;
            left: 0;
            z-index: 1000;
        }
        .content {
            margin-top: 80px; /* Space for the fixed header */
            width: 100%;
            display: flex;
            justify-content: center;
            padding: 20px;
            flex: 1; /* Allow the content to expand and push footer down */
        }
        .container {
            background-color: #fff;
            padding: 20px; /* Reduced padding */
            border-radius: 8px;
            box-shadow: 0 12px 24px rgba(0, 0, 0, 0.3); /* Enhanced shadow effect */
            max-width: 350px; /* Further reduced width */
            width: 100%;
        }
        h2 {
            text-align: center;
            color: #333;
            margin: 0 0 20px;
        }
        .form-row {
            display: flex;
            flex-wrap: wrap;
            gap: 20px; /* Increased gap between fields */
        }
        .form-group {
            flex: 1;
            min-width: calc(50% - 20px); /* Adjusted to fit two columns with gap */
            margin-bottom: 20px; /* Increased space between rows */
            position: relative;
        }
        .form-group input {
            width: 100%;
            padding: 12px; /* Increased padding for better touch */
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
            padding-left: 40px; /* Space for icons */
        }
        .form-group i {
            position: absolute;
            left: 10px;
            top: 50%;
            transform: translateY(-50%);
            color: #007bff;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #333;
        }
        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px; /* Adjusted padding */
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px; /* Increased font size */
            width: auto; /* Adjust width to content */
            display: block;
            margin: 20px auto 0; /* Center the button */
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        footer {
            bottom: 0;
            width: 100%;
        }
    </style>
    <script>
        document.addEventListener('DOMContentLoaded', () => {
            const form = document.querySelector('form');
            
            form.addEventListener('submit', function(event) {
                const price = parseFloat(document.querySelector('input[name="medicinePrice"]').value);
                const quantity = parseInt(document.querySelector('input[name="medicineStockQuantity"]').value);

                if (price <= 0 || quantity <= 0) {
                    event.preventDefault(); // Prevent form submission
                    alert('Price and Stock Quantity must be greater than zero.');
                }
            });
        });
    </script>
</head>
<body>
    <header>
        <jsp:include page="insiderheader.jsp"></jsp:include>
    </header>
    <div class="content">
        <div class="container">
            <h2 style="color : Blue; ">Add Medicine</h2><br>
            <form action="AddMedicineServlet" method="post" enctype="multipart/form-data">
                <div class="form-row">
                    <div class="form-group">
                        <i class="fa fa-capsules"></i>
                        <input type="text" name="medicineName" placeholder="Name" required>
                    </div>
                    <div class="form-group">
                        <i class="fa fa-tag"></i>
                        <input type="text" name="medicineCategory" placeholder="Category" required>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <i class="fa fa-info-circle"></i>
                        <input type="text" name="medicineDescription" placeholder="Description" required>
                    </div>
                    <div class="form-group">
                        <i class="fa fa-rupee-sign"></i>
                        <input type="number" step="0.01" name="medicinePrice" placeholder="Price" required min="0">
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <i class="fa fa-archive"></i>
                        <input type="number" name="medicineStockQuantity" placeholder="Stock Quantity" required min="0">
                    </div>
                    <div class="form-group">
                        <i class="fa fa-building"></i>
                        <input type="text" name="medicineCompany" placeholder="Company" required>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label>Manufactured Date</label>
                        <i class="fa fa-calendar-day"></i>
                        <input type="date" name="medicineManufacturedDate" placeholder="Manufactured Date" required min="1900-01-01" max="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>">
                    </div>
                    <div class="form-group">
                        <label>Expire Date</label>
                        <i class="fa fa-calendar-alt"></i>
                        <input type="date" name="medicineExpireDate" placeholder="Expire Date" required min="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>">
                    </div>
                </div>

                <div class="form-group">
                    <label>Upload Medicine Photo</label>
                    <i class="fa fa-image"></i>
                    <input type="file" name="medicinePhoto" accept="image/*" placeholder="Upload Photo">
                </div>

                <input type="submit" value="Add Medicine">
            </form>
        </div>
    </div>
    <footer>
        <jsp:include page="footer.jsp"></jsp:include>
    </footer>
</body>
</html>
