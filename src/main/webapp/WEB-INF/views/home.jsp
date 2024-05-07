
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            padding-top: 50px;
            padding-bottom: 20px;
        }
        .container {
            max-width: 600px;
        }
        .alert {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>


	<div class="container">
    <%-- Display Alert Message --%>
    <div class="alert alert-info" id="alertMessage" style="display: none;">
        <button type="button" class="close" data-dismiss="alert">¡¿</button>
        <span id="message"></span>
    </div>
    
    
 

    <% if (session.getAttribute("userName")!=null) { %>
        <!-- User is logged in -->
        <h2>Welcome <%=session.getAttribute("userName")%></h2>
        <a href="logout.do" class="btn btn-primary">Logout</a> 
        <a href="showAllUserInfo" class="btn btn-secondary">Show All User Info</a>
        <a href="test" class="btn btn-success">test function</a>
    <% } else { %>
        <!-- User is not logged in -->
        <h2>Welcome stranger</h2>
        <a href="loginMenu.do" class="btn btn-primary">Login</a>
    <% } %>
    <br><br>
    
    
    
	</div>




  <script>
   
 
    var message = "${message}";
    // Check if the message is not empty and has not been displayed before
    if (message.trim() !== "" && sessionStorage.getItem("messageIsDisplayed") !== "true") {
       
        alert(message);
        // Set the session storage variable to indicate that the message has been displayed
        sessionStorage.setItem("messageIsDisplayed", "true");
    }
	</script>

<!-- Bootstrap JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

