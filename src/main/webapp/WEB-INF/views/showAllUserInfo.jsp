<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
    <title>All User Information</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            padding: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            border: 1px solid #ddd;
        }
        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #ddd;
        }
    </style>
</head>
<body>

    <div class="container">
        <h2>All User Information</h2>
        <table class="table table-striped">
            <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>User ID</th>
                    <th>Hashed User Pw</th>
                    <th>User Name</th>
                    <th>User Join Date</th>
                    <th>User Last Login Time</th>
                    <!-- Add more columns as needed -->
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${allUserInfo}">
                    <tr>
                        <td>${user.ulid}</td>
                        <td>${user.userId}</td>
                        <td>${user.userPw}</td>
                        <!-- Uncomment to show hashed password with asterisks -->
                        <!--
                        <td>
                            <c:set var="asterisks" value="" />
                            <c:forEach begin="1" end="${fn:length(user.userPw)}" var="i">
                                <c:set var="asterisks" value="${asterisks}*"/>
                            </c:forEach>
                            <c:out value="${asterisks}"/>
                        </td>
                        -->
                        <td>${user.userName}</td>
                        <td>${user.joinDate}</td>
                        <td>${user.lastLoginTime}</td>
                        <!-- Add more columns as needed -->
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

</body>
</html>
