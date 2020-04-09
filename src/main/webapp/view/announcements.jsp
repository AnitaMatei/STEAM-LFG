<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <!-- Static content -->
    <link rel="stylesheet" href="../../resources/static/css/index-style.css">
    <script type="text/javascript" src="../../resources/static/js/app.js"></script>

    <title>STEAM LFG</title>
</head>
<body><table border="1">
    <tr>
        <td>name</td>
        <td>description</td>
    </tr>
    <c:forEach var="announcement" items="${announcements}">
        <tr>
            <td>${announcement.opUsername}</td>
            <td>${announcement.announcementDescription}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>