package org;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Calc extends HttpServlet {
    public static String globalURL = "http://localhost/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            AbstractPageFactory.getFactory(req, resp).build();
        } catch (IOException | ArithmeticException e) {
            req.setAttribute("exception", e.getMessage());
            resp.getWriter().println("Error: " + e.getMessage());

        } catch (Exception e) {
            req.setAttribute("exception", "Unknown error!");
            resp.getWriter().println("Error: unknown error");
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        StringBuilder sb = new StringBuilder();
        BufferedReader reader = req.getReader();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } finally {
            reader.close();
        }
        System.out.println(sb.toString());

        try {
            DataBase db = new DataBase("jdbc/db");
            Connection connection = db.getConnection();
            System.out.println(connection.getMetaData());
            //connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            statement.execute(sb.toString());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        System.out.println("Я сервайс");

    }
}