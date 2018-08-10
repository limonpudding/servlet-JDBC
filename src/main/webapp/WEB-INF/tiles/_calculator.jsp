<%@ page contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8" language="java" %>
<div style="max-width: 540px; margin: auto; ">
    <form action="answer" method="get">
        <script>
            function isEmpty(str) {
                if (str.trim() == '')
                    return true;

                return false;
            }

            function keyUp(e) {
                if (isEmpty($("#a").val()) && isEmpty($("#b").val()))
                    invisibleInterface();
                if (isEmpty($("#a").val()) || isEmpty($("#b").val()) || ($("#operation").val() === 'fib' && isEmpty($("#a").val())))
                    $("#calcButton").css('display', 'none');
                else
                    $("#calcButton").css('display', 'block');
            }

            function visibleInterface() {
                $("#a").css('display', 'block');
                $("#b").css('display', 'block');
                $("#a").val('');
                $("#b").val('');
                $("#label2").css('display', 'block');
                $("#calcButton").css('display', 'block');
                $("#label1").css('display', 'block');
            }

            function invisibleInterface() {
                $("#labelOperation").html('');
                $("#a").css('display', 'none');
                $("#b").css('display', 'none');
                $("#label2").css('display', 'none');
                $("#calcButton").css('display', 'none');
                $("#label1").css('display', 'none');
            }

            function summation() {
                $("#labelOperation").html('Суммирование');
                visibleInterface();
                $("#operation").val('sum');
                $("#calcButton").css('display', 'none');
            }

            function subtraction() {
                $("#labelOperation").html('Вычитание')
                visibleInterface();
                $("#operation").val('sub');
                $("#calcButton").css('display', 'none');
            }

            function multiplication() {
                $("#labelOperation").html('Умножение')
                visibleInterface();
                $("#operation").val('mul');
                $("#calcButton").css('display', 'none');
            }

            function division() {
                $("#labelOperation").html('Деление');
                visibleInterface();
                $("#operation").val('div');
                $("#calcButton").css('display', 'none');
            }

            function fibonacci() {
                $("#labelOperation").html('Фибоначчи')
                visibleInterface();
                $("#label2").css('display', 'none');
                $("#b").css('display', 'none');
                $("#b").val('1');
                $("#operation").val('fib');
                $("#calcButton").css('display', 'none');
            }

            function confirmFibonacci() {
                if ($("#operation").val() === 'fib') {
                    if (+$("#a").val() > 50000) {
                        alert('Вы ввели слишком большое число фибоначчи, ' +
                            'его рассчёт может занять продолжительное время. ')
                    }
                }
            }

            addEventListener("keyup", keyUp);
        </script>

        <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton"
                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Выберите операцию
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <a class="dropdown-item" href="#" onclick="summation()">Summation!</a>
                <a class="dropdown-item" href="#" onclick="subtraction()">Subtraction!</a>
                <a class="dropdown-item" href="#" onclick="multiplication()">Multiplication!</a>
                <a class="dropdown-item" href="#" onclick="division()">Division!</a>
                <a class="dropdown-item" href="#" onclick="fibonacci()">Fibonacci!</a>
                <a class="dropdown-item" href="#" onclick="invisibleInterface()">Clear!</a>
            </div>
        </div>
        <label for="dropdownMenuButton" id="labelOperation"></label>
        <br>
        <label for="a" id="label1" style="display: none">Enter 1-st operator</label>
        <input type="number" id="a" name="a" style="display: none"><br>

        <label for="b" id="label2" style="display: none">Enter 2-nd operator</label>
        <input type="number" id="b" name="b" style="display: none"><br>

        <input type="text" id="operation" name="operation" style="display: none"><br>

        <input type="submit" id="calcButton" value="Calculate" style="display: none" onclick="confirmFibonacci()"><br>
    </form>
</div>