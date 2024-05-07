<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login Form</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            padding-top: 50px;
            padding-bottom: 20px;
        }
        .container {
            max-width: 400px;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .btn-login {
            width: 100%;
        }
    </style>
</head>
<script>
    var message = "${message}";
    if (message.trim() !== "") {
        alert(message);
    }
    
    // Function to invalidate locker after 5 seconds
    function invalidateLocker() {
        setTimeout(function() {
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "invalidateLocker.do", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.send();
        }, 5000);
    }
    
    // Call invalidateLocker function
    <% if (session.getAttribute("locker") == "locked") { %>
        invalidateLocker();
    <% } %>
</script>
<body>
<div class="container">
    <p>Fail count: <%=session.getAttribute("loginFailCnt") %></p>
    <% if (session.getAttribute("locker") == "locked") { %>
        <div>
            <p>You are locked for 5 sec. Please wait...</p>
        </div>
    <% } else { %>
        <% if (session.getAttribute("userName") == null) { %>
            <!-- Login Form -->
            <h2>Login Form</h2>
            <form action="loginCheck.do" method="post">
                <div class="form-group">
                    <label for="userId">ID:</label>
                    <input type="text" class="form-control" name="userId" required>
                </div>
                <div class="form-group">
                    <label for="userPw">Password:</label>
                    <input type="password" class="form-control" name="userPw" required>
                </div>
                <button type="submit" class="btn btn-primary btn-login">Login</button>
            </form>
            <a href="registerMenu">Don't have an account? Sign up now</a>
        <% } else { %>
            <!-- User is logged in -->
            <p>Current User: <%=session.getAttribute("userName")%></p>
            <a href="logout.do" class="btn btn-danger">Logout</a>
        <% } %>
    <% } %>
    <br>
    <a href="home" class="btn btn-secondary">Go to Home</a>
</div>

<!-- Bootstrap JS -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
