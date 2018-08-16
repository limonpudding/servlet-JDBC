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


        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.151:1521:gmudb", "internship", "internship")) {
            Statement statement = connection.createStatement();
            String sqlFormat = "yyyy.MM.dd HH24:mi:ss";
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            if (req.getSession().isNew()) {
                statement.execute("insert into SESSIONS (ID, IP, TIMESTART, TIMEEND) values ('" + req.getSession().getId() + "','" + req.getRemoteAddr() + "', to_date('" + formatForDateNow.format(new Date(req.getSession().getCreationTime())) + "','" + sqlFormat + "')," + "to_date('" + formatForDateNow.format(new Date(req.getSession().getCreationTime())) + "','" + sqlFormat + "')" + ")");
            } else {
                statement.execute("update SESSIONS set TIMEEND=" + "to_date('" + formatForDateNow.format(new Date(req.getSession().getLastAccessedTime())) + "','" + sqlFormat + "')" + " where SESSIONS.ID = '" + req.getSession().getId() + "'");
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
