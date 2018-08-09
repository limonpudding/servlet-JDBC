package org;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreatorInput extends AbstractPageCreatorFactory {

    public CreatorInput(HttpServletRequest req, HttpServletResponse resp) {
        page = new Page(req, resp);
    }

    public void build() throws Exception {
        page.getRequest().getRequestDispatcher("input.jsp").forward(page.getRequest(), page.getResponse());
    }
}
