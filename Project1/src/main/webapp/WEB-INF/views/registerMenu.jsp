<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Register Form</title>
</head>
<body>
	enter your info to sign up
	<form action="registerCheck.do" method="post">
        <label for="userId">id:</label>
        <input type="text"  name="userId"><br>
        
        <label for="userPw">pw:</label>
        <input type="password"  name="userPw"><br>
        
         <label for="userName">userName:</label>
        <input type="password"  name="userName"><br>
        
        <input type="submit" value="sign up">
    </form>
    <a href="loginMenu">going back</a>
</body>
</html>