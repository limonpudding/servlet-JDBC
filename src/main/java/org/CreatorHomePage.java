package org;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreatorHomePage extends AbstractPageFactory {

    public CreatorHomePage(HttpServletRequest req, HttpServletResponse resp) {
        super(req, resp);
    }

    public void build() throws Exception {
        page.getValue().getRequest().setAttribute("isFirstTime", page.getValue().getRequest().getSession().isNew());
        page.getValue().getRequest().getRequestDispatcher("home.jsp").forward(page.getValue().getRequest(), page.getValue().getResponse());
    }
}
