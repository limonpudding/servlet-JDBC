<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style type="text/css">
        <%@include file="css/bootstrap.css" %>
        <%@include file="css/bootstrap-theme.css" %>
    </style>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Start page</title>
</head>
<style>
    div {
        border-radius: 5px;
        background-color: #f2f2f2;
        padding: 20px;
    }
</style>
<body>
<div style="max-width: 540px; margin: auto; text-align: center">
    <h3>Hello! It is start page of our application server! Please, go to this link: <a href="http://localhost/calc">calculator</a></h3>
</div>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="bootstrap.js"></script>
</body>
</html>