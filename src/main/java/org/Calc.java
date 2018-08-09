package org;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Calc extends HttpServlet {
    private final PageCreatorFactory pageCreatorFactory = new PageCreatorFactory();

    public static String globalURL = "http://localhost/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //AbstractPageCreatorFactory factory = AbstractPageCreatorFactory.getFactory(req, resp);
        try {
            PageCreator builder = pageCreatorFactory.build(req, resp);
            builder.buildPage(req, resp);
        } catch (IOException | ArithmeticException e) {
            req.setAttribute("exception", e.getMessage());
        } catch (Exception e) {
            req.setAttribute("exception", "Unknown error!");
        }

    }
}