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