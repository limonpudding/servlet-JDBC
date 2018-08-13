package org;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CreatorAnswer extends AbstractPageFactory {

    public CreatorAnswer(HttpServletRequest req, HttpServletResponse resp) {
        page = new Page(req, resp);
    }

    public void build() throws Exception {

        HttpSession session = page.getRequest().getSession();


        String a = page.getRequest().getParameter("a");
        String b = page.getRequest().getParameter("b");
        String operation = page.getRequest().getParameter("operation");

        String ans = calc(a, b, operation);

        OperationsHistory operationsHistory = new OperationsHistory();
        operationsHistory.getHistory(session);

        org.Operation oper = new org.Operation(a,b,operation,ans);

        operationsHistory.addOperation(oper);

        session.setAttribute(session.getId(), operationsHistory.getHistory(session));

        page.getRequest().setAttribute("operationsHistory", operationsHistory.getHistory(session));
        page.getRequest().setAttribute("answer", ans);
        page.getRequest().getRequestDispatcher("answer.jsp").forward(page.getRequest(), page.getResponse());
    }

    private String calc(String strA, String strB, String operation) throws IOException {
        Injector injector = Guice.createInjector(new LongArithmeticModule());
        LongArithmethic res;
        LongArithmethic a = injector.getInstance(LongArithmethic.class);
        LongArithmethic b = injector.getInstance(LongArithmethic.class);
        a.setValue(strA);
        b.setValue(strB);
        //LongArithmethic res;
        //LongArithmethic a = new LongArithmethic(strA);
        //LongArithmethic b = new LongArithmeticImplList(strB);
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
