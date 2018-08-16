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

    .hidden {
        white-space: nowrap; /* Отменяем перенос текста */
        overflow: hidden; /* Обрезаем содержимое */
        width: 100px;
        text-overflow:ellipsis;
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

            <table class="table" style="table-layout: fixed">
                <thead>
                <tr>
                    <th class="col hidden">
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
                        Операция
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
                <c:forEach var="row" items="${fullOperationsHistory}">
                    <tr>
                        <td class="col hidden" title="${row.id()}">
                                ${row.id()}
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
                        <td class="col">
                                ${row.time()}
                        </td>
                    </tr>


                </c:forEach>
                </tbody>
            </table>
        </div>
