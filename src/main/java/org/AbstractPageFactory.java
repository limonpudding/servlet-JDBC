package org;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URL;

public abstract class AbstractPageFactory {
    protected Page page = null;

    //public AbstractPageFactory() {}

    public static AbstractPageFactory getFactory(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        URL url = new URL(req.getRequestURL().toString());
        String path = url.getPath();
        switch (path) {
            case "/answer":
                return new CreatorAnswer(req, resp);
            case "/calc":
                return new CreatorInput(req, resp);
            case "/about":
                return new CreatorAbout(req, resp);
            default:
                return new CreatorHomePage(req, resp);
        }
    }

    public abstract void build() throws Exception;
}
