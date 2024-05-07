<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Form</title>

<!-- Bootstrap CSS -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">



<style>
    /* Custom CSS */
    .form-container {
        max-width: 400px;
        margin: 0 auto;
        padding: 20px;
        border: 1px solid #ccc;
        border-radius: 5px;
        background-color: #f9f9f9;
        margin-top: 50px;
    }
</style>



</head>

<body>
    <div class="container">
        <div class="form-container">
            <h2 class="text-center">Register Form</h2>
            
            <form action="registerCheck.do" method="post">
                <div class="form-group">
                    <label for="userId">ID:</label>
                    <div class="input-group">
                        <input type="text" name="userId" class="form-control" required >
                        <div class="input-group-append">
                            <button type="button" class="btn btn-primary" onclick="checkRedundancy()">Check Redundancy</button>
                        </div>
                    </div>
                </div>
                <div id="passwordSection">
                    <!-- Password input field will be dynamically updated here -->
                </div>
                <div class="form-group">
                    <label for="userName" >User Name:</label>
                    <input type="text" name="userName" class="form-control" required>
                </div>
                <button type="submit" id="signupButton" class="btn btn-success btn-block" disabled>Sign Up</button>
            </form>
            
            
            <a href="loginMenu" class="mt-3 d-block text-center">Go back</a>
        </div>
    </div>
    
    
    <!-- Include jQuery library for AJAX -->
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

<script>
    function checkRedundancy() {
        var userId = document.getElementsByName("userId")[0].value;
        var userIdInput = $("input[name='userId']");
        // Make AJAX request to the controller endpoint
        $.ajax({
            type: "POST",
            url: "userIdRedundancyCheck.do",
            data: { id: userId },
            success: function(response) {
                console.log("Redundancy Check Result: " + response);

                $("#tempCheck").text("RedundancyCheck: " + response);

                if (response === 'false') {
                    alert('RedundancyCheck passed');
                    $("#passwordSection").html('<label for="userPw">Password:</label>' +
                                               '<input type="password" name="userPw" class="form-control" required><br>');
                    userIdInput.prop('readonly', true);
                    $("#signupButton").prop('disabled', false);
                } else {
                    alert('try other id please');
                    $("#signupButton").prop('disabled', true);
                }
            },
            error: function(error) {
                console.error("Error during Redundancy Check: " + error);
            }
        });
    }
</script>
</body>
</html>
