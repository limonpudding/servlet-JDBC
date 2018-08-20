package org;

import com.google.inject.Injector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.text.Annotation;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class CreatorAnswer extends AbstractPageFactory {


    public CreatorAnswer(HttpServletRequest req, HttpServletResponse resp) {
        super(req, resp);
    }

    public void build() throws Exception {

        HttpSession session = page.getValue().getRequest().getSession();

        String a = page.getValue().getRequest().getParameter("a");
        String b = page.getValue().getRequest().getParameter("b");
        String operation = page.getValue().getRequest().getParameter("operation");
        String ans = calc(a, b, operation);

        OperationsHistory operationsHistory = new OperationsHistory();
        operationsHistory.getHistory(session);

        org.Operation oper = new org.Operation(new Date(), a, b, operation, ans, UUID.randomUUID().toString());

        putDataInBD(oper, page.getValue().getRequest());

        operationsHistory.addOperation(oper);

        session.getServletContext().setAttribute(session.getId(), operationsHistory.getHistory());
/*
    SELECT
      HISTORY.OPERATION,
      HISTORY.FIRSTOPERAND,
      HISTORY.SECONDOPERAND,
      HISTORY.ANSWER,
      HISTORY.TIME
    FROM HISTORY
    WHERE HISTORY.ID = idSession;
 */
        page.getValue().getRequest().setAttribute("operationsHistory", operationsHistory.getHistory());
        page.getValue().getRequest().setAttribute("answer", ans);
        page.getValue().getRequest().getRequestDispatcher("answer.jsp").forward(page.getValue().getRequest(), page.getValue().getResponse());
    }

    public static String calc(String strA, String strB, String operation) throws IOException {
        LongArithmethic res;
        LongArithmethic a = injector.getValue().getInstance(LongArithmethic.class);
        LongArithmethic b = injector.getValue().getInstance(LongArithmethic.class);
        a.setValue(strA);
        b.setValue(strB);
        switch (operation) {
            case "sum":
                res = LongArithmeticMath.sum(a, b);
                break;
            case "sub":
                res = LongArithmeticMath.sub(a, b);
                break;
            case "mul":
                res = LongArithmeticMath.mul(a, b);
                break;
            case "div":
                res = LongArithmeticMath.div(a, b);
                break;
            case "fib":
                res = new Fibonacci(Integer.parseInt(strA)).number;
                break;
            default:
                throw new IOException("Unexpected operation!");
        }
        return res.toString();
    }

    private void putDataInBD(Operation operation, HttpServletRequest req) {
        try (Connection connection = dataBase.getValue().getConnection()) {
            switch (operation.operation) {
                case "fib":
                    PreparedStatement fibonacci = connection.prepareStatement("insert into " + operation.operation + " (ID, FIRSTOPERAND, ANSWER, IDSESSION, TIME) values (?,?,?,?,?)");
                    fibonacci.setString(1, operation.idOperation);
                    fibonacci.setString(2, operation.a);
                    fibonacci.setString(3, operation.result);
                    fibonacci.setString(4, req.getSession().getId());
                    fibonacci.setTimestamp(5, new Timestamp(operation.date.getTime()));
                    fibonacci.executeUpdate();
                    break;
                default:
                    PreparedStatement arithmetic = connection.prepareStatement("insert into " + operation.operation + " (ID, FIRSTOPERAND, SECONDOPERAND, ANSWER, IDSESSION, TIME) values (?,?,?,?,?,?)");
                    arithmetic.setString(1, operation.idOperation);
                    arithmetic.setString(2, operation.a);
                    arithmetic.setString(3, operation.b);
                    arithmetic.setString(4, operation.result);
                    arithmetic.setString(5, req.getSession().getId());
                    arithmetic.setTimestamp(6, new Timestamp(operation.date.getTime()));
                    arithmetic.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
