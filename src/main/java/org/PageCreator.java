package org;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class PageCreator {
    private AbstractPageCreatorFactory pcf;

    public void setAbstractPageCreatorFactory(AbstractPageCreatorFactory pcf) {
        this.pcf = pcf;
    }

    public Page getPage() {
        return pcf.getPage();
    }

    public void constructPage() throws Exception {
        pcf.build();
    }
}
