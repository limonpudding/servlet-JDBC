package org;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class ListenerHttpSessionAttributes implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("Атрибут сессии добавлен:{"+httpSessionBindingEvent.getName()+","+httpSessionBindingEvent.getValue()+"}");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("Атрибут сессии убран:{"+httpSessionBindingEvent.getName()+","+httpSessionBindingEvent.getValue()+"}");
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("Атрибут сессии изменён:{"+httpSessionBindingEvent.getName()+","+httpSessionBindingEvent.getValue()+"}");
    }
}
