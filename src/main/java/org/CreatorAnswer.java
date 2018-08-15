package org;

import com.google.inject.Injector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
        ServletContext context = session.getServletContext();

        String a = page.getValue().getRequest().getParameter("a");
        String b = page.getValue().getRequest().getParameter("b");
        String operation = page.getValue().getRequest().getParameter("operation");
        String ans = calc(a, b, operation);
        OperationsHistory operationsHistory = new OperationsHistory();
        operationsHistory.getHistory(session);

        SimpleDateFormat formatForDateNow = new SimpleDateFormat("hh:mm:ss");
        org.Operation oper = new org.Operation(formatForDateNow.format(new Date()),a,b,operation,ans, UUID.randomUUID().toString());

        operationsHistory.addOperation(oper);

        session.getServletContext().setAttribute(session.getId(), operationsHistory.getHistory());

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
}
