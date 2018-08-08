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
    <title>Ответ</title>
</head>
<body>
    <h1>Ответ : <%= request.getParameter("answer") %></h1>
</body>
</html>
