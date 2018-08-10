package org;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreatorHomePage extends AbstractPageFactory {

    public CreatorHomePage(HttpServletRequest req, HttpServletResponse resp) {
        page = new Page(req, resp);
    }

    public void build() throws Exception {
        page.getRequest().setAttribute("isFirstTime", page.getRequest().getSession().isNew());
        page.getRequest().getRequestDispatcher("home.jsp").forward(page.getRequest(), page.getResponse());
    }
}
