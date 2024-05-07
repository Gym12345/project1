<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test Page</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
  <h1>Test Page</h1>
  <p>유저이름: <input type="text" id="userName" name="username"></p>
  <p>데이터: <input type="text" id="data" name="d"></p>
  
  <button id="generateSessionButton">Generate JDBC Session</button>
  <button id="ShowSessionButton">show Sessions</button>
  <script>
  $(document).ready(function(){
	    $("#generateSessionButton").click(function(){
	        let userName = $("#userName").val();
	        let data = $("#data").val();    
	        
	        $.ajax({
	            url: './generateJDBCSession.do',
	            type: "POST",
	            data: {    
	                userName: userName,
	                data: data
	            },
	            success: function(responseData) {
	                console.log("Session data received:", responseData); // Log the response data
	            },
	            error: function(xhr, status, error) {
	                console.error("Error:", error);
	            }
	        });
	    });
	});
  
  
  
  
  $(document).ready(function(){
	    $("#ShowSessionButton").click(function(){
	       
	        
	        $.ajax({
	            url: './showSessions.do',
	            type: "POST",
	           
	            success: function() {
	                console.log("signal delivered"); 
	            },
	            error: function(xhr, status, error) {
	                console.error("Error:", error);
	            }
	        });
	    });
	});

  </script>
</body>
</html>
