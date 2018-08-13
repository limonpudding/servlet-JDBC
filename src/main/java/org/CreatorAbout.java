package org;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreatorAbout extends AbstractPageFactory {

    public CreatorAbout(HttpServletRequest req, HttpServletResponse resp) {
        super(req, resp);
    }

    public void build() throws Exception {
        page.getValue().getRequest().getRequestDispatcher("about.jsp").forward(page.getValue().getRequest(), page.getValue().getResponse());
    }
}
