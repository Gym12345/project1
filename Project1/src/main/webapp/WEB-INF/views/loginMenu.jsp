<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login Form</title>
</head>
<body>
    <h2>Login Form</h2>
    <form action="loginCheck.do" method="post">
        <label for="userId">id:</label>
        <input type="text"  name="userId"><br>
        
        <label for="userPw">pw:</label>
        <input type="password"  name="userPw"><br>
        
        <input type="submit" value="login">
    </form>
</body>
</html>
