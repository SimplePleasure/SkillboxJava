package com.session.session.Beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;


@SessionScope
@Component
public class SessionBean {

    @Autowired
    TextStorage storage;
    private String name;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public TextStorage getStorage() {
        return storage;
    }
}
