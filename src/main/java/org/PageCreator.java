package org;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PageCreator {
    void buildePage(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
