package org;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreatorRootPage extends AbstractPageFactory {

    public CreatorRootPage(HttpServletRequest req, HttpServletResponse resp) {
        page = new Page(req, resp);
    }

    public void build() throws Exception {
        page.getRequest().setAttribute("isFirstTime", page.getRequest().getSession().isNew());
        page.getRequest().getRequestDispatcher("rootPage.jsp").forward(page.getRequest(), page.getResponse());
    }
}
