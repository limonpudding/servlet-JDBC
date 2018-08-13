package org;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;

public class LoggerFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Я родился");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        System.out.println("Пользователь с IP: " + servletRequest.getRemoteAddr() + " зашёл на страницу "+req.getRequestURL());
        if (!req.getSession().isNew() && req.getRequestURL().toString().equals("http://localhost/")){
           req.getRequestDispatcher("input.jsp").forward(req, resp);
       }
       filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("Пока");
    }
}
