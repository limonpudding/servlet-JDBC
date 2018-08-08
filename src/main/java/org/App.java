package org;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class App extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String strA = req.getParameter("a");
        String strB = req.getParameter("b");
        String operation = req.getParameter("operation");
        LongArithmethic a = new LongArithmeticImplList(strA);
        LongArithmethic b = new LongArithmeticImplList(strB);
        LongArithmethic res;
        try {
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
                default:
                    throw new IOException("Несуществующая операция!");
            }
            String strRes = res.toString();
            req.setAttribute("answer", strRes);
        } catch (IOException e) {
            req.setAttribute("exception", e.getMessage());
        } catch (ArithmeticException e) {
            req.setAttribute("exception", e.getMessage());
        } catch (Exception e){
            req.setAttribute("exception", "Неизвестная ошибка!");
        }


        req.getRequestDispatcher("answer.jsp").forward(req, resp);
        //PrintWriter out = resp.getWriter();
        //out.print("<h1>Hello Servlet</h1>");
        //resp.sendRedirect("/calc/answer.jsp?answer=" + strRes);
    }
    //http://localhost/calc?a=74237423846&b=89797897897987788&operation=mul
}
