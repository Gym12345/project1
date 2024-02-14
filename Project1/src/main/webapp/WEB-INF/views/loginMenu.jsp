<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login Form</title>
</head>

<script>
        // Check if the 'message' variable is present and not empty
        var message = "${message}";
        if (message.trim() !== "") {
            // Display an alert with the message
            alert(message);
        }
    </script>

<body>




	fail count=<%=session.getAttribute("loginFailCnt") %>

<%if (session.getAttribute("locker") == "locked") {%>

	 <script>
        // Set a timer to trigger an AJAX call after 1 minute
        setTimeout(function() {
            // Make an AJAX call to invalidate the session attribute "locker"
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "invalidateLocker.do", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.send();
        }, 5000); // 1 minute in milliseconds
        
    </script>
    you are locked for 5 sec ( you have to stay in this page), remaining time:      
	
<%  
} else {%>
	


<% if (session.getAttribute("userName")==null ){  %>
    <h2>Login Form</h2>
    <form action="loginCheck.do" method="post">
        <label for="userId">id:</label>
        <input type="text"  name="userId"><br>
        
        <label for="userPw">pw:</label>
        <input type="password"  name="userPw"><br>
        
        <input type="submit" value="login">
    </form>
    
    <a href="registerMenu">no account? sign up now</a>
    
<% }else { %>
	현재 사용자:<%=session.getAttribute("userName")%>	
	<a href="logout.do">logout</a>
<%} %> 
<%} %>
<br>
<a href="home">홈 화면</a>



</body>
</html>
