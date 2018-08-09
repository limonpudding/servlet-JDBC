package org;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Page {
    HttpServletResponse response;
    HttpServletRequest request;
    String action;


    public HttpServletRequest getRequest() {
        return request;
    }
    public HttpServletResponse getResponse() {
        return response;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public Page(HttpServletRequest req, HttpServletResponse resp) {
        request = req;
        response = resp;
    }
}
