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
        text-overflow: ellipsis;
    }
</style>
<script>
    function createTable(id) {
        $('#secondTable').html('');
        $('#carouselExampleControls').carousel('next');
        <c:forEach var="row" items="${fullOperationsHistory}">
        if (id === '${row.id()}') {
            $('#secondTable').append(
                '<tr>' +
                '<td class="col hidden" title="${row.operationName()}">' +
                '${row.operationName()}' +
                '</td>' +
                '<td class="col hidden" title="${row.op1()}">' +
                '${row.op1()}' +
                '</td>' +
                '<td class="col hidden" title="${row.op2()}">' +
                '${row.op2()}' +
                '</td>' +
                '<td class="col hidden" title="${row.answer()}">' +
                '${row.answer()}' +
                '</td>' +
                '<td class="col" title="${row.time()}">' +
                '${row.time()}' +
                '</td>' +
                '</tr>'
            )
        }
        </c:forEach>
    }
</script>
<div class="container" style="height: 85%;overflow-y: auto">
    <div class="row">
        <p>
            <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#filtres"
                    aria-expanded="false" aria-controls="filtres">
                Button with data-target
            </button>
        </p>
    </div>
    <div class="collapse row" id="filtres">
        <div class="card card-body">
            <form action="ophistory" method="GET">
                <div class="col-6">
                    <p>Сортировать по столбцу:</p>
                    <p><input name="mode" type="radio" value="idSession">ID</p>
                    <p><input name="mode" type="radio" value="ip">IP</p>
                    <p><input name="mode" type="radio" value="timeStart">Время создания сессии</p>
                    <p><input name="mode" type="radio" value="timeEnd">Время завершения сессии</p>
                    <p><input name="mode" type="radio" value="operation">Операция</p>
                    <p><input name="mode" type="radio" value="firstOper">Первый операнд</p>
                    <p><input name="mode" type="radio" value="secondOper">Второй операнд</p>
                    <p><input name="mode" type="radio" value="answer">Ответ</p>
                    <p><input name="mode" type="radio" value="time">Время операции</p>
                </div>
                <div class="col-6">
                    <p>Порядок сортировки:</p>
                    <p><input name="order" type="radio" value="asc">По возрастанию</p>
                    <p><input name="order" type="radio" value="desc">По убыванию</p>
                </div>
                <p><input type="submit" value="Выбрать"></p>
            </form>
        </div>
    </div>

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

    <div id="carouselExampleControls" class="carousel slide" data-ride="false" data-pause="true">
        <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <table class="table" style="table-layout: fixed">
                    <thead>
                    <tr>
                        <th class="col hidden">
                            ID
                        </th>
                        <th class="col">
                            IP
                        </th>
                        <th class="col" onclick="">
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
                                <a href="#" onclick="createTable('${row.id()}')">${row.id()}</a>
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
            <div class="carousel-item">
                <table class="table" style="table-layout: fixed">
                    <thead>
                    <tr>
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
                    <tbody id="secondTable">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>



