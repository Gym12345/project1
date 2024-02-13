<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="userList.UserListDTO" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>User Registration Success</title>
</head>
<body>

<h2>User Registration Successful</h2>

<%
	
    // Retrieve the UserListDTO object from request attribute
    UserListDTO userListDTO = (UserListDTO) request.getAttribute("userListDTO");
	
    // Check if userListDTO is not null before accessing its properties
    if (userListDTO != null) {
%>
    <p>User Information:</p>
    <ul>
        <li>ULID: <%= userListDTO.getUlid() %></li>
        <li>User ID: <%= userListDTO.getUserId() %></li>
        <li>User Name: <%= userListDTO.getUserName() %></li>
        <li>Join Date: <%= userListDTO.getJoinDate() %></li>
        <li>Last Login Time: <%= userListDTO.getLastLoginTime() %></li>
    </ul>
<%
    } else {
%>
    <p>No user information available.</p>
<%
    }
%>

</body>
</html>
