<%--
  Created by IntelliJ IDEA.
  User: SMartyshenko
  Date: 08.08.2018
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Калькулятор</title>
</head>
<style>
    input[type=text], select {
        width: 100%;
        padding: 12px 20px;
        margin: 8px 0;
        display: inline-block;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }

    input[type=submit] {
        width: 100%;
        background-color: #4CAF50;
        color: white;
        padding: 14px 20px;
        margin: 8px 0;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    input[type=submit]:hover {
        background-color: #45a049;
    }

    div {
        border-radius: 5px;
        background-color: #f2f2f2;
        padding: 20px;
    }
</style>
<body>
<div style="max-width: 540px; margin: auto;">
<form action = "answer" method="get">
    <label for="a">Enter 1-st operator</label>
    <input type="text" id="a" name="a"><br>

    <label for="b">Enter 2-nd operator</label>
    <input type="text" id="b" name="b"><br>

    <label for="operation">Enter operation</label>
    <input type="text"  id="operation" name="operation"><br>

    <input type="submit" value="Calculate"><br>
</form>
</div>
</body>
</html>