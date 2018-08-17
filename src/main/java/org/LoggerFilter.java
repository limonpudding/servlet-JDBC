package org;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class LoggerFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Я родился");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        DataBase db = new DataBase(Calc.getDBName());
        try (Connection connection = db.getConnection()) {
            Statement statement = connection.createStatement();
            String sqlFormat = "yyyy.MM.dd hh:mm:ss";
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss a");
            //update sessions set timeend=PARSEDATETIME( '2018.06.06 01:01:01 am','yyyy.MM.dd hh:mm:ss a','en')
            if (req.getSession().isNew()) {
                statement.execute("insert into SESSIONS (ID, IP, TIMESTART, TIMEEND) values ('" + req.getSession().getId() + "','" + req.getRemoteAddr() + "', PARSEDATETIME('" + formatForDateNow.format(new Date(req.getSession().getCreationTime())) + "','" + sqlFormat + " a','en')," + "PARSEDATETIME('" + formatForDateNow.format(new Date(req.getSession().getCreationTime())) + "','" + sqlFormat + " a','en')" + ")");
            } else {
                statement.execute("update SESSIONS set TIMEEND=" + "PARSEDATETIME('" + formatForDateNow.format(new Date(req.getSession().getLastAccessedTime())) + "','" + sqlFormat + " a','en')" + " where SESSIONS.ID = '" + req.getSession().getId() + "'");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        System.out.println("Пользователь с IP: " + servletRequest.getRemoteAddr() + " зашёл на страницу " + req.getRequestURL());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("Пока");
    }
}
