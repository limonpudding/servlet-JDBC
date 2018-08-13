package org;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreatorInput extends AbstractPageFactory {

    public CreatorInput(HttpServletRequest req, HttpServletResponse resp) {
        page = new Page(req, resp);
    }

    public void build() throws Exception {
        HttpSession session = page.getRequest().getSession();
        OperationsHistory operationsHistory = new OperationsHistory();
        page.getRequest().setAttribute("operationsHistory", operationsHistory.getHistory(session));
        page.getRequest().getRequestDispatcher("input.jsp").forward(page.getRequest(), page.getResponse());
    }
}
