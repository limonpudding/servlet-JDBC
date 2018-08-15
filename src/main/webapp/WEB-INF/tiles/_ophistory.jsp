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
            <th class="col" width="60px">
                <div class="hidden">
                    ID678678678678678
                </div>
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
                <td class="col hidden">
                    <div class="" >
                            ${row.id()}
                    </div>
                </td>
                <td class="col">
                        ${row.ip()}
                </td>
                <td class="col">
                        ${row.sessionStartTime()}
                </td>
                <td class="col">
                        ${row.sessionEndTime()}
                </td>
                <td class="col">
                        ${row.operationName()}
                </td>
                <td class="col hide">
                        ${row.op1()}
                </td>
                <td class="col hide">
                        ${row.op2()}
                </td>
                <td class="col hide">
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
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script>
    var size = 7;

    $('.hidden').each(function() {
       var tmp = $(this).text();
        $(this).text(tmp.trim().substr(0, size) + '...');
    });

</script>

