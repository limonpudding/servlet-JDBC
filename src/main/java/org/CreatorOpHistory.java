package org;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreatorOpHistory extends AbstractPageFactory {

    public CreatorOpHistory(HttpServletRequest req, HttpServletResponse resp) {
        super(req, resp);
    }

    public void build() throws Exception {
        page.getValue().getRequest().getRequestDispatcher("ophistory.jsp").forward(page.getValue().getRequest(), page.getValue().getResponse());
    }
}
