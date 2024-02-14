<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
</head>
<body>

 <script>
        // Check if the 'message' variable is present and not empty
        var message = "${message}";
        if (message.trim() !== "") {
            // Display an alert with the message
            alert(message);
        }
    </script>



<% if (session.getAttribute("userName")!=null) { %>

Welcome <%=session.getAttribute("userName")%> 
<a href="logout.do">logout</a>
<%} else {%>
	Welcome stranger
	<a href="loginMenu.do">login menu</a>	
<%} %>
<br>

<br>
<a href="showAllUserInfo">showAllUserInfo</a>

</body>
</html>
