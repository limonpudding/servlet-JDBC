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
            function visibleInterface(){
                document.getElementById("a").style.display = 'block';
                document.getElementById("b").style.display = 'block';
                document.getElementById("label2").style.display = 'block';
                document.getElementById("calcButton").style.display = 'block';
                document.getElementById("label1").style.display='block';
            }
            function invisibleInterface(){
                document.getElementById("a").style.display = 'none';
                document.getElementById("b").style.display = 'none';
                document.getElementById("label2").style.display = 'none';
                document.getElementById("calcButton").style.display = 'none';
                document.getElementById("label1").style.display='none';
            }
            function summation() {
                visibleInterface();
                document.getElementById("operation").value = 'sum';
            }
            function subtraction() {
                visibleInterface();
                document.getElementById("operation").value = 'sub';
            }
            function multiplication() {
                visibleInterface();
                document.getElementById("operation").value = 'mul';
            }
            function division() {
                visibleInterface();
                document.getElementById("operation").value = 'div';
            }
            function fibonacci() {
                visibleInterface();
                document.getElementById("label2").style.display = 'none';
                document.getElementById("b").style.display = 'none';
                document.getElementById("b").value = '1';
                document.getElementById("operation").value = 'fib';
            }
        </script>
        <input type="button" value="Summation!" OnClick="summation();">
        <input type="button" value="Subtraction!" OnClick="subtraction();">
        <input type="button" value="Multiplication!" OnClick="multiplication();">
        <input type="button" value="Division!" OnClick="division();">
        <input type="button" value="Fibonacci!" OnClick="fibonacci();">
        <input type="button" value="Clear!" OnClick="invisibleInterface();">
        <br>
        <label for="a" id="label1" style="display: none">Enter 1-st operator</label>
        <input type="text" id="a" name="a" style="display: none"><br>

        <label for="b" id="label2" style="display: none">Enter 2-nd operator</label>
        <input type="text" id="b" name="b" style="display: none"><br>

        <input type="text" id="operation" name="operation" style="display: none"><br>

        <input type="submit" id="calcButton" value="Calculate" style="display: none"><br>
    </form>
</div>
</body>
</html>