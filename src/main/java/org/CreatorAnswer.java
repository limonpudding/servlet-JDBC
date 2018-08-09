package org;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.List;

public class CreatorAnswer implements PageCreator{

    public void buildPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String ans = calc(req.getParameter("a"), req.getParameter("b"), req.getParameter("operation"));
        req.setAttribute("answer", ans);
        req.getRequestDispatcher("answer.jsp").forward(req, resp);
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
