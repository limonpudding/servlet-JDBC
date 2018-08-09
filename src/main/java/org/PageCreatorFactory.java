package org;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;

public class PageCreatorFactory {
    public PageCreator build(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO код, который возвращает в зависимости от поступившего url (или чего-то ещё) нужную реализацию PageCreator
        URL url = new URL(req.getRequestURL().toString());
        String path = url.getPath();
        switch (path) {
            case "/answer":
                return new CreatorAnswer();
            case "/calc":
                return new CreatorInput();
            default:
                return new CreatorRootPage();
        }
    }
}
