<%--
  Created by IntelliJ IDEA.
  User: SMartyshenko
  Date: 08.08.2018
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style type="text/css">
        <%@include file="css/bootstrap.css" %>
        <%@include file="css/bootstrap-theme.css" %>
    </style>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Answer</title>
</head>
<body>
<form action="answer" method="get">
    <div style="max-width: 540px; margin: auto; text-align: center">
        <% String exception = request.getParameter("exception"); %>
        <c:choose>
            <c:when test="${not empty exception}">
                <h3>Error: ${exception}</h3>
            </c:when>
            <c:otherwise>
                <h3>Answer: ${answer}</h3>
            </c:otherwise>
        </c:choose>
    </div>
</form>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
