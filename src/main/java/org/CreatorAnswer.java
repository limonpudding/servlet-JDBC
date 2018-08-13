package org;

import com.google.inject.Injector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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

        org.Operation oper = new org.Operation(a,b,operation,ans);

        operationsHistory.addOperation(oper);

        session.setAttribute(session.getId(), operationsHistory.getHistory(session));

        page.getValue().getRequest().setAttribute("operationsHistory", operationsHistory.getHistory(session));
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
