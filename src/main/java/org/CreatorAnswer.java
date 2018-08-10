package org;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class CreatorAnswer extends AbstractPageFactory {

    public CreatorAnswer(HttpServletRequest req, HttpServletResponse resp) {
        page = new Page(req, resp);
    }

    public void build() throws Exception {
        String ans = calc(
                page.getRequest().getParameter("a"),
                page.getRequest().getParameter("b"),
                page.getRequest().getParameter("operation")
        );
        StringBuilder operationsHistory = new StringBuilder();

        page.getResponse().addCookie(new Cookie(
                UUID.randomUUID().toString(),page.getRequest().getParameter("a") +
                "_"+page.getRequest().getParameter("operation")+
                "_"+page.getRequest().getParameter("b")+
                "="+ans
                )
        );

        for(Cookie cookie:page.getRequest().getCookies()){
            operationsHistory.append(cookie.getValue()).append("\n");
        }
        page.getRequest().setAttribute("strOperationsHistory", operationsHistory.toString());

        page.getRequest().setAttribute("answer", ans);
        page.getRequest().getRequestDispatcher("answer.jsp").forward(page.getRequest(), page.getResponse());
    }

    private String calc(String strA, String strB, String operation) throws IOException {
        LongArithmethic res;
        LongArithmethic a = new LongArithmeticImplList(strA);
        LongArithmethic b = new LongArithmeticImplList(strB);
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
