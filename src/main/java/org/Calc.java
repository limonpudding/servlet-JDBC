package org;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;


public class Calc extends HttpServlet {

    public static String globalURL = "http://localhost/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            PageCreator pc = new PageCreatorImpl();
            pc.PageBuilder(req, resp);
        } catch (IOException | ArithmeticException e) {
            req.setAttribute("exception", e.getMessage());
        } catch (Exception e) {
            req.setAttribute("exception", "Unknown error!");
        }
        req.getRequestDispatcher("answer.jsp").forward(req, resp);//Сюда уходит, если какя-то ошибка
        //PrintWriter out = resp.getWriter();
        //out.print("<h1>Hello Servlet</h1>");
        //resp.sendRedirect("/calc/answer.jsp?answer=" + strRes);
    }
}