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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
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
                <div class="form-group">
                    <label for="exampleFormControlTextarea1">Ответ:</label>
                    <textarea class="form-control" id="exampleFormControlTextarea1" rows="10">${answer}</textarea>
                    <a href="http://localhost/calc">Калькулятор</a>
                </div>
                <div class="form-group">
                    <label for="exampleFormControlTextarea1">История операций:</label>
                    <textarea class="form-control" id="exampleForm" rows="5">${strOperationsHistory}</textarea>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</form>
<%--<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>--%>
<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>--%>
<%--<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>--%>
</body>
</html>
