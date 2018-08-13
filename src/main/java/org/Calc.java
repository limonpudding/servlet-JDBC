package org;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Calc extends HttpServlet {
    public static String globalURL = "http://localhost/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            System.out.println(req.getRemoteAddr());
            System.out.println(req.getLocalAddr());
            AbstractPageFactory.getFactory(req, resp).build();
        } catch (IOException | ArithmeticException e) {
            req.setAttribute("exception", e.getMessage());
            resp.getWriter().println("Error: " + e.getMessage());

        } catch (Exception e) {
            req.setAttribute("exception", "Unknown error!");
            resp.getWriter().println("Error: unknown error");
        }

    }
}