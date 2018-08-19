<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="fullSessionsHistory" scope="request" type="java.util.List"/>
<c:forEach var="row" items="${fullSessionsHistory}">
    <tr>
        <td class="col hidden" title="${row.id()}">
            <c:choose>
                <c:when test="${row.operation()=='false'}">
                    ${row.id()}
                </c:when>
                <c:otherwise>
                    <a href="#" onclick="openSecondTable('${row.id()}')">${row.id()}</a>
                </c:otherwise>
            </c:choose>
        </td>
        <td class="col hidden" title="${row.ip()}">
                ${row.ip()}
        </td>
        <td class="col">
                ${row.sessionStartTime()}
        </td>
        <td class="col">
                ${row.sessionEndTime()}
        </td>
    </tr>
</c:forEach>