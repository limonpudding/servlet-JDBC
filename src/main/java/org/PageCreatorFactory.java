package org;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageCreatorFactory {
    public PageCreator build(HttpServletRequest req, HttpServletResponse resp){
        //TODO код, который возвращает в зависимости от поступившего url (или чего-то ещё) нужную реализацию PageCreator
        return new CreatorAnswer();
    }
}
