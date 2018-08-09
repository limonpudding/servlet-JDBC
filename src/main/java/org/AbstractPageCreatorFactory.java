package org;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URL;

public abstract class AbstractPageCreatorFactory {
    protected Page page = null;

    public Page getPage() {
        return page;
    }

    public AbstractPageCreatorFactory() {}

    public static AbstractPageCreatorFactory getFactory(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        URL url = new URL(req.getRequestURL().toString());
        String path = url.getPath();
        switch (path) {
            case "/answer":
                return new CreatorAnswer(req, resp);
            case "/calc":
                return new CreatorInput(req, resp);
            default:
                return new CreatorRootPage(req, resp);
        }
    }

    public abstract void build() throws Exception;
}
