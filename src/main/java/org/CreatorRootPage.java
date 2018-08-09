package org;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreatorRootPage extends AbstractPageCreatorFactory {

    public CreatorRootPage(HttpServletRequest req, HttpServletResponse resp) {
        page = new Page(req, resp);
    }

    public void build() throws Exception {
        page.getRequest().getRequestDispatcher("rootPage.jsp").forward(page.getRequest(), page.getResponse());
    }
}
