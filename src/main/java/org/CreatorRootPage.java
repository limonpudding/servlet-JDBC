package org;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreatorRootPage implements PageCreator{
    public void buildPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.getRequestDispatcher("rootPage.jsp").forward(req, resp);
    }
}
