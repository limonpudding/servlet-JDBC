package org;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreatorAbout extends AbstractPageFactory {

    public CreatorAbout(HttpServletRequest req, HttpServletResponse resp) {
        page = new Page(req, resp);
    }

    public void build() throws Exception {
        page.getRequest().getRequestDispatcher("about.jsp").forward(page.getRequest(), page.getResponse());
    }
}
