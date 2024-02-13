<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
    <title>All User Information</title>
</head>
<body>

    <h2>All User Information</h2>

    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>User ID</th>
                <th>Masked User Pw</th>
                <th>User Name</th>
                <th>User JoinDate</th>
                <th>User lastLogin Time</th>
                
                <!-- Add more columns as needed -->
            </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${allUserInfo}">
                <tr>
                    <td>${user.ulid}</td>
                    <td>${user.userId}</td>
                    
                    
                    <td>
                       
                        <c:set var="asterisks" value="" />
                        
                        <c:forEach begin="1" end="${fn:length(user.userPw)}" var="i">
                            <c:set var="asterisks" value="${asterisks}*"/>
                        </c:forEach>
                        
                        <c:out value="${asterisks}"/>
                    </td>
                    
                    
                    <td>${user.userName}</td>
                    <td>${user.joinDate}</td>
                    <td>${user.lastLoginTime}</td>
                    
                    <!-- Add more columns as needed -->
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>
