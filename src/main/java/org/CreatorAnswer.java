package org;

import com.google.inject.Injector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.Annotation;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class CreatorAnswer extends AbstractPageFactory {

    private static Injector injector;

    public static Injector getInjector() {
        return injector;
    }

    public CreatorAnswer(HttpServletRequest req, HttpServletResponse resp, Injector injector) {
        super(req, resp);
        this.injector = injector;
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

    private String calc(String strA, String strB, String operation) throws IOException {
        LongArithmethic res;
        LongArithmethic a = injector.getInstance(LongArithmethic.class);
        LongArithmethic b = injector.getInstance(LongArithmethic.class);
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
            Statement statement = connection.createStatement();
            String sqlFormat = "yyyy.MM.dd HH24:mi:ss";
            String sessionId = req.getSession().getId();
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            statement.execute("update SESSIONS set TIMEEND=" + "to_date('" + formatForDateNow.format(new Date(req.getSession().getLastAccessedTime())) + "','" + sqlFormat + "')" + " where SESSIONS.ID = '" + req.getSession().getId() + "'");
            switch (operation.operation) {
                case "sum":
                    statement.execute("insert into SUM (ID, FIRSTOPERAND, SECONDOPERAND, ANSWER, IDSESSION, TIME) values" +
                            " ('" + operation.idOperation + "','" + operation.a + "','" + operation.b + "','" + operation.result + "','" + sessionId + "'," + "to_date('" + operation.date() + "','" + sqlFormat + "')" + ")");
                    break;
                case "sub":
                    statement.execute("insert into SUB (ID, FIRSTOPERAND, SECONDOPERAND, ANSWER, IDSESSION, TIME) values" +
                            " ('" + operation.idOperation + "','" + operation.a + "','" + operation.b + "','" + operation.result + "','" + sessionId + "'," + "to_date('" + operation.date() + "','" + sqlFormat + "')" + ")");
                    break;
                case "mul":
                    statement.execute("insert into MUL (ID, FIRSTOPERAND, SECONDOPERAND, ANSWER, IDSESSION, TIME) values" +
                            " ('" + operation.idOperation + "','" + operation.a + "','" + operation.b + "','" + operation.result + "','" + sessionId + "'," + "to_date('" + operation.date() + "','" + sqlFormat + "')" + ")");
                    break;
                case "div":
                    statement.execute("insert into DIV (ID, FIRSTOPERAND, SECONDOPERAND, ANSWER, IDSESSION, TIME) values" +
                            " ('" + operation.idOperation + "','" + operation.a + "','" + operation.b + "','" + operation.result + "','" + sessionId + "'," + "to_date('" + operation.date() + "','" + sqlFormat + "')" + ")");
                    break;
                case "fib":
                    statement.execute("insert into FIB (ID, FIRSTOPERAND, ANSWER, IDSESSION, TIME) values" +
                            " ('" + operation.idOperation + "','" + operation.a + "','" + operation.result + "','" + sessionId + "'," + "to_date('" + operation.date() + "','" + sqlFormat + "')" + ")");
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
