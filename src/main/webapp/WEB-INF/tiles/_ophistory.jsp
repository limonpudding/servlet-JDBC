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
        text-overflow: ellipsis;
    }
</style>
<script>
    function openTable() {
        var str = '<%= request.getParameter("idSessii") %>';
        if (str.length > 0 && str !== 'null' && str !== 'undefined') {
            createTable(str);
        }
    }

    function createListFirstPage() {
        $(document).ready(function () {
            $("button").click(function () { // задаем функцию при нажатиии на элемент <button>
                $.ajax({
                    method: "POST", // метод HTTP, используемый для запроса
                    url: "localhost", // строка, содержащая URL адрес, на который отправляется запрос
                    data: { // данные, которые будут отправлены на сервер
                        table: '1',
                        mode: $('#firstSelectorMode').val(),
                        order: $('#firstSelectorDirection').val()
                        //id: window['idSessii']
                    },
                    success: [function (firstTableRows) {
                        $("p").text("User saved: " + msg);
                    }],
                    statusCode: {
                        200: function () { // выполнить функцию если код ответа HTTP 200
                            console.log("Ok");
                        }
                    }
                })
            });
        });
        // $('#idSessii').val(idSessii);
        // $('#sortForm').submit();
    }

    function openTable() {
        var str = '${idSessiiPar}';
        if (str != null && typeof str !== "undefined") {
            str = str.trim();
        }
        if (str !== "") {
            createTable();
        }
    }

    function fullCreateTable(id) {
        window['idSessii'] = id;
        createTable();
        slideNext();
    }

    function slidePrev() {
        $('#carouselExampleControls').carousel('prev');
    }

    function slideNext() {
        $('#carouselExampleControls').carousel('next');
    }

    function createFirstTable(tableRows) {
        $('#firstTable').html('');
        var row;
        var cell;
        tableRows.forEach(function (row) {
            row = document.getElementById('firstTable').insertRow();
            cell = row.insertCell();
            if (row['operation'] === 'false') {
                cell.innerHTML = row['id'];
            } else {
                cell.innerHTML ="<a href='#' onclick='fullCreateTable("+row['id']+")>"+row['id']+"</a>";
            }
            cell.classList.add('col');
            cell.classList.add('hidden');
            cell.setAttribute('title', row['id']);
            cell = row.insertCell();
            cell.innerHTML = row['ip'];
            cell.classList.add('col');
            cell.classList.add('hidden');
            cell.setAttribute('title', row['ip']);
            cell = row.insertCell();
            cell.innerHTML = row['sessionStartTime'];
            cell.classList.add('col');
            cell.classList.add('hidden');
            cell.setAttribute('title', row['sessionStartTime']);
            cell = row.insertCell();
            cell.innerHTML = row['sessionEndTime'];
            cell.classList.add('col');
            cell.classList.add('hidden');
            cell.setAttribute('title', row['sessionEndTime']);
        });
    }

    function createSecondTable(tableRows) {
        $('#secondTable').html('');
        tableRows.forEach(function (row) {
            if (id === row.id) {
                var row = document.getElementById('secondTable').insertRow();
                var cell = row.insertCell();
                cell.innerHTML = row['operationName'];
                cell.classList.add('col');
                cell.classList.add('hidden');
                cell.setAttribute('title', row['operationName']);
                cell = row.insertCell();
                cell.innerHTML = row['op1'];
                cell.classList.add('col');
                cell.classList.add('hidden');
                cell.setAttribute('title', row['op1']);
                cell = row.insertCell();
                cell.innerHTML = row['op2'];
                cell.classList.add('col');
                cell.classList.add('hidden');
                cell.setAttribute('title', row['op2']);
                cell = row.insertCell();
                cell.innerHTML = row['answer'];
                cell.classList.add('col');
                cell.classList.add('hidden');
                cell.setAttribute('title', row['answer']);
                cell = row.insertCell();
                cell.innerHTML = row['time'];
                cell.classList.add('col');
                cell.classList.add('hidden');
                cell.setAttribute('title', row['time']);
            }
        });
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
                            <select class="custom-select" id="mode" name="mode" id="firstSelectorMode">
                                <option value="idSession">ID</option>
                                <option value="ip">IP</option>
                                <option value="timeStart">Время создания сессии</option>
                                <option value="timeEnd">Время завершения сессии</option>
                            </select>
                        </div>
                        <div class="form-group col-auto">
                            <select class="custom-select" id="order" name="order" id="firstSelectorDirection">
                                <option value="asc">По возрастанию</option>
                                <option value="desc">По убыванию</option>
                            </select>
                        </div>
                        <div class="col">
                            <input class="btn btn-primary" type="button" onclick="createListFirstPage()"
                                   value="Выбрать">
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
                    <tbody id="firstTable">
                    <%--заполняется динамически createFirstTable js--%>
                    </tbody>
                </table>
            </div>
            <div class="carousel-item">
                <h4>Сортировка: </h4>
                <form action="ophistory" method="get" id="sortForm">
                    <div class="form-row">
                        <div class="form-group col-auto">
                            <select class="custom-select" name="mode" id="secondSelectorMode">
                                <option value="operation">Операция</option>
                                <option value="time">Время операции</option>
                            </select>
                        </div>
                        <div class="form-group col-auto">
                            <select class="custom-select" name="order" id="secondSelectorDirection">
                                <option value="asc">По возрастанию</option>
                                <option value="desc">По убыванию</option>
                            </select>
                        </div>
                        <div class="col">
                            <input type='hidden' name='idSessiiPar' value='' id='idSessii'/>
                            <input class="btn btn-primary" type="button" onclick="sortSecondPage()" value="Выбрать">
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
    </div>
</div>
<script>
    openTable();
</script>


