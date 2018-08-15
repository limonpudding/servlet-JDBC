<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<style>
    .table-fixed thead {
        width: 97%;
    }

    .table-fixed tbody {
        height: 230px;
        overflow-y: auto;
        width: 100%;
    }

    .table-fixed thead, .table-fixed tbody, .table-fixed tr, .table-fixed td, .table-fixed th {
        display: block;
    }

    .table-fixed tbody td, .table-fixed thead > tr > th {
        float: left;
        border-bottom-width: 0;
    }
</style>
<div class="container">
    <div class="row">
        Фильтры:<br>
        <div class="input-group mb-3">
            <div class="input-group-prepend" style="margin-top: 8px;">
                <label class="input-group-text" for="inputGroupSelect01">Операции</label>
            </div>
            <select class="custom-select" id="inputGroupSelect01">
                <option selected>Choose...</option>
                <option value="1">Только мои</option>
                <option value="2">Все</option>
            </select>
        </div>
    </div>
    <div class="row">
        <table class="table">
            <thead>
            <tr>
                <th class="col">
                    ID
                </th>
                <th class="col">
                    IP
                </th>
                <th class="col">
                    Время создания сессии
                </th>
                <th class="col">
                    Время завершения сессии
                </th>
                <th class="col">
                    Первый операнд
                </th>
                <th class="col">
                    Второй операнд
                </th>
                <th class="col">
                    Ответ
                </th>
                <th class="col">
                    Время операции
                </th>
            </tr>
            </thead>
            <tbody>

                <tr>
                    <td class="col">
                        ID
                    </td>
                    <td class="col">
                        IP
                    </td>
                    <td class="col">
                        Время создания сессии
                    </td>
                    <td class="col">
                        Время завершения сессии
                    </td>
                    <td class="col">
                        Первый операнд
                    </td>
                    <td class="col">
                        Второй операнд
                    </td>
                    <td class="col">
                        Ответ
                    </td>
                    <td class="col">
                        Время операции
                    </td>
                </tr>

                <tr>
                    <td class="col">
                        ID
                    </td>
                    <td class="col">
                        IP
                    </td>
                    <td class="col">
                        Время создания сессии
                    </td>
                    <td class="col">
                        Время завершения сессии
                    </td>
                    <td class="col">
                        Первый операнд
                    </td>
                    <td class="col">
                        Второй операнд
                    </td>
                    <td class="col">
                        Ответ
                    </td>
                    <td class="col">
                        Время операции
                    </td>
                </tr>

                <tr>
                    <td class="col">
                        ID
                    </td>
                    <td class="col">
                        IP
                    </td>
                    <td class="col">
                        Время создания сессии
                    </td>
                    <td class="col">
                        Время завершения сессии
                    </td>
                    <td class="col">
                        Первый операнд
                    </td>
                    <td class="col">
                        Второй операнд
                    </td>
                    <td class="col">
                        Ответ
                    </td>
                    <td class="col">
                        Время операции
                    </td>
                </tr>

                <tr>
                    <td class="col">
                        ID
                    </td>
                    <td class="col">
                        IP
                    </td>
                    <td class="col">
                        Время создания сессии
                    </td>
                    <td class="col">
                        Время завершения сессии
                    </td>
                    <td class="col">
                        Первый операнд
                    </td>
                    <td class="col">
                        Второй операнд
                    </td>
                    <td class="col">
                        Ответ
                    </td>
                    <td class="col">
                        Время операции
                    </td>
                </tr>

                <tr>
                    <td class="col">
                        ID
                    </td>
                    <td class="col">
                        IP
                    </td>
                    <td class="col">
                        Время создания сессии
                    </td>
                    <td class="col">
                        Время завершения сессии
                    </td>
                    <td class="col">
                        Первый операнд
                    </td>
                    <td class="col">
                        Второй операнд
                    </td>
                    <td class="col">
                        Ответ
                    </td>
                    <td class="col">
                        Время операции
                    </td>
                </tr>


                <c:forEach var="operation" items="${operationsHistory}">
                    ${operation.toString()}
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
