package org;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreatorInput implements PageCreator {
    public void buildPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.getRequestDispatcher("input.jsp").forward(req, resp);
    }
}
