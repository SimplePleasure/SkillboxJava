package com.session.session.Beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;


@SessionScope
@Component
public class SessionBean {




    private String name;
    private Map<String, String> statistic = Collections.emptyMap();
    ConcurrentLinkedQueue<String> list = new ConcurrentLinkedQueue<>();

    public Map<String, String> getStatistic() {
        return statistic;
    }

    public void setStatistic(Map<String, String> statistic) {
        this.statistic = Objects.requireNonNullElseGet(statistic, HashMap::new);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Queue<String> getStorage() {
        return list;
    }
}
