package com.session.session.Beans;

import com.session.session.TextStorage;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
public class SessionBean {


    SessionBean (TextStorage storage) {
        this.storage = storage;
    }

    private String name;
    private TextStorage storage;

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
