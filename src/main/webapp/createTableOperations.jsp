<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="operationsHistory" scope="request" type="java.util.List"/>
<c:forEach var="row" items="${operationsHistory}">
    <tr>
        <td class="col hidden" title="${row.operationName()}">
                ${row.operationName()}
        </td>
        <td class="col hidden" title="${row.op1()}">
                ${row.op1()}
        </td>
        <td class="col hidden" title="${row.op2()}">
                ${row.op2()}
        </td>
        <td class="col hidden" title="${row.answer()}">
                ${row.answer()}
        </td>
        <td class="col hidden" title="${row.time()}">
                ${row.time()}
        </td>
    </tr>
</c:forEach>