package org;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;


public class Calc extends HttpServlet {

    public static String globalURL = "http://localhost/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        try {
            String res;
            URL url = new URL(req.getRequestURL().toString());
            if (url.getPath().equals("/calc")) {
                req.getRequestDispatcher("input.jsp").forward(req, resp);
                return;
            } else if (url.getPath().equals("/answer")) {
                String a = req.getParameter("a");
                String b = req.getParameter("b");
                String operation = req.getParameter("operation");
                res = calc(a, b, operation);
                req.setAttribute("answer", res);
                req.getRequestDispatcher("answer.jsp").forward(req, resp);
                return;
            } else {
                req.getRequestDispatcher("rootPage.jsp").forward(req, resp);//Это типа стартовой страницы, если не подходит под остальные юрлы
            }
        } catch (IOException e) {
            req.setAttribute("exception", e.getMessage());
        } catch (ArithmeticException e) {
            req.setAttribute("exception", e.getMessage());
        } catch (Exception e) {
            req.setAttribute("exception", "Unknown error!");
        }
        req.getRequestDispatcher("answer.jsp").forward(req, resp);//Сюда уходит, если какя-то ошибка
        //PrintWriter out = resp.getWriter();
        //out.print("<h1>Hello Servlet</h1>");
        //resp.sendRedirect("/calc/answer.jsp?answer=" + strRes);
    }
    //http://localhost/calc?a=74237423846&b=89797897897987788&operation=mul

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
            default:
                throw new IOException("Unexpected operation!");
        }
        return res.toString();
    }
}


//PrintWriter out = resp.getWriter();
//        out.print(req.getRequestURL());