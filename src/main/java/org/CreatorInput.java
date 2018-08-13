package org;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreatorInput extends AbstractPageFactory {

    public CreatorInput(HttpServletRequest req, HttpServletResponse resp) {
        super(req, resp);
    }

    public void build() throws Exception {
        page.getValue().getRequest().getRequestDispatcher("input.jsp").forward(page.getValue().getRequest(), page.getValue().getResponse());
    }
}
