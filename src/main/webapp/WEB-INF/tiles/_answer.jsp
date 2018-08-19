<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="answer" method="get">
    <div style="margin: auto; text-align: center">
        <% String exception = request.getParameter("exception"); %>
        <c:choose>
            <c:when test="${not empty exception}">
                <h3>Ошибка: ${exception}</h3>
            </c:when>
            <c:otherwise><%--@elvariable id="answer" type="java"--%>
                <div class="form-group">
                    <label for="exampleFormControlTextarea1">Ответ:</label>
                    <textarea class="form-control" id="exampleFormControlTextarea1" rows="8" readonly>${answer}</textarea>
                    <a href="calc">Калькулятор</a>
                </div>
                <div class="form-group">
                    <label for="exampleFormControlTextarea1">История операций:</label>
                    <textarea class="form-control" id="exampleForm" rows="4" readonly>
                        <c:forEach var="operation" items="${operationsHistory}">
                            ${operation.toString()}
                        </c:forEach>
                            <%--${strOperationsHistory}--%>
                    </textarea>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</form>