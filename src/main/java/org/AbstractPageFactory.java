package org;

import com.google.inject.Guice;
import com.google.inject.Injector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URL;

public abstract class AbstractPageFactory {
    private HttpServletRequest request;
    private HttpServletResponse response;

    protected Lazy<Page> page = new Lazy<Page>() {
        @Override
        protected Page setValue() {
            return new Page(request, response);
        }
    };


    private static Injector injector = Guice.createInjector(new LongArithmeticModule());


    //public AbstractPageFactory() {}
    protected AbstractPageFactory(HttpServletRequest req, HttpServletResponse resp){
        this.request = req;
        this.response = resp;
    }

    protected AbstractPageFactory(){}

    public static AbstractPageFactory getFactory(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        URL url = new URL(req.getRequestURL().toString());
        String path = url.getPath();
        switch (path) {
            case "/answer":
                return new CreatorAnswer(req, resp, injector);
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
