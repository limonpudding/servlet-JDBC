<%--
  Created by IntelliJ IDEA.
  User: SMartyshenko
  Date: 08.08.2018
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <title>Calculator</title>
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

    input[type=number], select {
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

    input[type=button] {
        background-color: #4CAF50;
        color: white;
        padding: 6px 14px;
        margin: 4px 0;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    input[type=button]:active {
        background-color: #71c451;
        color: white;
        padding: 6px 14px;
        margin: 4px 0;
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
    <form action="answer" method="get">
        <script>
            function isEmpty(str) {
                if (str.trim() == '')
                    return true;

                return false;
            }

            function keyUp(e) {
                if (isEmpty(document.getElementById("a").value) && isEmpty(document.getElementById("b").value))
                    invisibleInterface();
                if (isEmpty(document.getElementById("a").value) || isEmpty(document.getElementById("b").value)||(document.getElementById("operation").value==='fib' && isEmpty(document.getElementById("a").value)))
                    document.getElementById("calcButton").style.display = 'none';
                else
                    document.getElementById("calcButton").style.display = 'block';
            }

            function visibleInterface() {
                document.getElementById("a").style.display = 'block';
                document.getElementById("b").style.display = 'block';
                document.getElementById("a").value = '';
                document.getElementById("b").value = '';
                document.getElementById("label2").style.display = 'block';
                document.getElementById("calcButton").style.display = 'block';
                document.getElementById("label1").style.display = 'block';
            }

            function invisibleInterface() {
                document.getElementById("labelOperation").innerHTML = '';
                document.getElementById("a").style.display = 'none';
                document.getElementById("b").style.display = 'none';
                document.getElementById("label2").style.display = 'none';
                document.getElementById("calcButton").style.display = 'none';
                document.getElementById("label1").style.display = 'none';
            }

            function summation() {
                document.getElementById("labelOperation").innerHTML = 'Суммирование';
                visibleInterface();
                document.getElementById("operation").value = 'sum';
                document.getElementById("calcButton").style.display = 'none';
            }

            function subtraction() {
                document.getElementById("labelOperation").innerHTML = 'Вычитание'
                visibleInterface();
                document.getElementById("operation").value = 'sub';
                document.getElementById("calcButton").style.display = 'none';
            }

            function multiplication() {
                document.getElementById("labelOperation").innerHTML = 'Умножение'
                visibleInterface();
                document.getElementById("operation").value = 'mul';
                document.getElementById("calcButton").style.display = 'none';
            }

            function division() {
                document.getElementById("labelOperation").innerHTML = 'Деление'
                visibleInterface();
                document.getElementById("operation").value = 'div';
                document.getElementById("calcButton").style.display = 'none';
            }

            function fibonacci() {
                document.getElementById("labelOperation").innerHTML = 'Фибоначчи'
                visibleInterface();
                document.getElementById("label2").style.display = 'none';
                document.getElementById("b").style.display = 'none';
                document.getElementById("b").value = '1';
                document.getElementById("operation").value = 'fib';
                document.getElementById("calcButton").style.display = 'none';
            }

            addEventListener("keyup", keyUp);
        </script>

        <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Выберите операцию
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <a class="dropdown-item" href="#" onclick="summation()">Summation!</a>
                <a class="dropdown-item" href="#" onclick="subtraction()">Subtraction!</a>
                <a class="dropdown-item" href="#" onclick="multiplication()">Multiplication!</a>
                <a class="dropdown-item" href="#" onclick="division()">Division!</a>
                <a class="dropdown-item" href="#" onclick="fibonacci()">Fibonacci!</a>
                <a class="dropdown-item" href="#" onclick="invisibleInterface()">Clear!</a>
            </div>
        </div>
        <label for="dropdownMenuButton" id="labelOperation"></label>
        <br>
        <label for="a" id="label1" style="display: none">Enter 1-st operator</label>
        <input type="number" id="a" name="a" style="display: none"><br>

        <label for="b" id="label2" style="display: none">Enter 2-nd operator</label>
        <input type="number" id="b" name="b" style="display: none"><br>

        <input type="text" id="operation" name="operation" style="display: none"><br>

        <input type="submit" id="calcButton" value="Calculate" style="display: none"><br>
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>