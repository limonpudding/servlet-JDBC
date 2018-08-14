package org;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;

public class ListenerAttributes implements ServletRequestAttributeListener {

    @Override
    public void attributeAdded(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        if("answer".equals(servletRequestAttributeEvent.getName()))
            System.out.println("Последний ответ: "+servletRequestAttributeEvent.getValue());
        System.out.println("Атрибут сервлета добавлен:{"+servletRequestAttributeEvent.getName()+","+servletRequestAttributeEvent.getValue()+"}");
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        System.out.println("Атрибут сервлета убран:{"+servletRequestAttributeEvent.getName()+","+servletRequestAttributeEvent.getValue()+"}");
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        System.out.println("Атрибут сервлета изменён:{"+servletRequestAttributeEvent.getName()+","+servletRequestAttributeEvent.getValue()+"}");
    }
}