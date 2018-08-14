package org;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreatorInput extends AbstractPageFactory {

    public CreatorInput(HttpServletRequest req, HttpServletResponse resp) {
        super(req, resp);
    }

    public void build() throws Exception {
        HttpSession session = page.getValue().getRequest().getSession();
        OperationsHistory operationsHistory = new OperationsHistory();
        page.getValue().getRequest().setAttribute("operationsHistory", operationsHistory.getHistory(session));
        page.getValue().getRequest().getRequestDispatcher("input.jsp").forward(page.getValue().getRequest(), page.getValue().getResponse());
    }
}
