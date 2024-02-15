<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Register Form</title>

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

                // Update the content of tempCheck element with the new value
                $("#tempCheck").text("RedundancyCheck: " + response);
                
                // Check the response and update the password input field accordingly
                if (response === 'false') {
                    // Redundancy check passed, show the password input field
                    
                    alert('RedundancyCheck passed');
                    $("#passwordSection").html('<label for="userPw">Password:</label>' +
                                               '<input type="password" name="userPw"><br>');
                    userIdInput.prop('readonly', true);
                    // Enable the "Sign Up" button
                    $("#signupButton").prop('disabled', false);
                    
                } else {
                	 alert('try other id please');
                    
                    // Disable the "Sign Up" button
                    $("#signupButton").prop('disabled', true);
                }
            },
            error: function(error) {
                console.error("Error during Redundancy Check: " + error);
            }
        });
    }
    
</script>

</head>

<body>
    Enter your info to sign up
    <form action="registerCheck.do" method="post">
        <label for="userId">ID:</label>
        <input type="text" name="userId">
        <button type="button" onclick="checkRedundancy()">Check Redundancy</button>
        <div id="tempCheck"></div>
        
        <div id="passwordSection">
            <!-- Password input field will be dynamically updated here -->
        </div>
        
        <br>
        
        <label for="userName">User Name:</label>
        <input type="text" name="userName"><br>
        
        <input type="submit" id="signupButton" value="Sign Up" disabled>
    </form>
    <a href="loginMenu">Go back</a>
</body>
</html>
