package com.session.session.Beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import java.util.Map;


@SessionScope
@Component
public class SessionBean {

    @Autowired
    TextStorage storage;
    private String name;
    private Map<String, String> statistic;

    public Map<String, String> getStatistic() {
        return statistic;
    }
    public void setStatistic(Map<String, String> statistic) {
        this.statistic = statistic;
    }
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
