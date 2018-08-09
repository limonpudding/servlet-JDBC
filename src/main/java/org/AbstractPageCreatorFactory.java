package org;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractPageCreatorFactory {
    //TODO можно добавить HttpServletRequest и HttpServletResponse в качестве атрибутов, тогда buid без параметров...
    public static AbstractPageCreatorFactory getFactory(HttpServletRequest request, HttpServletResponse response){
        //TODO код, который вернёт нужную фабрику
        return null;//new PageCreatorFactory();
    }

    public abstract PageCreator build(HttpServletRequest req, HttpServletResponse resp);
}
