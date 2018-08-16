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
    function openSecondPage() {
        $('#idSessii').val(idSessii);
        $('#sortForm').submit();
    }

    function slidePrev() {
        $('#carouselExampleControls').carousel('prev');
    }

    function slideNext() {
        $('#carouselExampleControls').carousel('next');
    }

    function createTable(id) {
        $('#secondTable').html('');

        window['idSessii'] = id;
        slideNext();
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
<div class="container" style="height: 80%;overflow-y: auto">
    <div id="carouselExampleControls" class="carousel slide" data-ride="false" data-pause="true">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <h4>Сортировка: </h4>
                <form action="ophistory" method="get">
                    <div class="form-row">
                        <div class="form-group col-auto">
                            <select class="custom-select" id="mode" name="mode">
                                <option value="time" selected>Столбец</option>
                                <option value="idSession">ID</option>
                                <option value="ip">IP</option>
                                <option value="timeStart">Время создания сессии</option>
                                <option value="timeEnd">Время завершения сессии</option>
                            </select>
                        </div>
                        <div class="form-group col-auto">
                            <select class="custom-select" id="order" name="order">
                                <option value="asc" selected>Порядок</option>
                                <option value="asc">По возрастанию</option>
                                <option value="desc">По убыванию</option>
                            </select>
                        </div>
                        <div class="col">
                            <input class="btn btn-primary" type="submit" value="Выбрать">
                        </div>
                    </div>
                </form>
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
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="row" items="${fullSessionsHistory}">
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
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="carousel-item">
                <h4>Сортировка: </h4>
                <form action="ophistory" method="get" id="sortForm">
                    <div class="form-row">
                        <div class="form-group col-auto">
                            <select class="custom-select" name="mode">
                                <option value="operation">Операция</option>
                                <option value="time">Время операции</option>
                            </select>
                        </div>
                        <div class="form-group col-auto">
                            <select class="custom-select" name="order">
                                <option value="asc">По возрастанию</option>
                                <option value="desc">По убыванию</option>
                            </select>
                        </div>
                        <div class="col">
                            <input type='hidden' name='idSessii' value='' id='idSessii'/>
                            <input class="btn btn-primary" type="button" onclick="openSecondPage()" value="Выбрать">
                            <input class="btn btn-secondary" type="button" onclick="slidePrev()" value="Назад">
                        </div>
                    </div>
                </form>
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
        <script>
            var str = '${idSessii}';
            if (str != null && typeof str !== "undefined") {
                str = str.trim();
            }
            if (str!=="") {
                createTable();
            }
        </script>
    </div>
</div>



