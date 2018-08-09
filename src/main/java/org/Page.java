package org;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Page {
    HttpServletResponse response;
    HttpServletRequest request;

    public HttpServletRequest getRequest() {
        return request;
    }
    public HttpServletResponse getResponse() {
        return response;
    }

    public Page(HttpServletRequest req, HttpServletResponse resp) {
        request = req;
        response = resp;
    }
}
