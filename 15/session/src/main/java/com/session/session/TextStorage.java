package com.session.session;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentLinkedQueue;

@Component
@Scope("prototype")
public class TextStorage {

    ConcurrentLinkedQueue<String> list = new ConcurrentLinkedQueue<>();

    boolean addLine(String str) {
        return list.add(str);
    }
    ConcurrentLinkedQueue<String> getList() {
        return list;
    }
}
