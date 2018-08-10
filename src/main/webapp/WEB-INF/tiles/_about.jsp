<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style="margin: auto; text-align: center; border-radius: 5px; padding: 20px">
    <h3>Калькулятор предназначен для работы с числами, количество знаков, в которых может быть больше 10000, при этом все вычисления выполняются крайне быстро. Также наш калькулятор умеет вычислять число Фибоначчи с преемлемой скоростью (не рекомендуется вводить числа больше 50000!) <h3>
    <c:choose>
        <c:when test="${isFirstTime=='true'}">
            <h3>Добро пожаловать, новый посетитель!</h3>
        </c:when>
    </c:choose>
</div>
