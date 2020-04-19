package com.session.session;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
@Scope("prototype")
public class TextStorage {

    ConcurrentLinkedQueue<String> list = new ConcurrentLinkedQueue<>();
//    private List<String> list = new ArrayList<>();

    boolean addLine(String str) {
        return list.add(str);
    }
    int getSize() {
        return list.size();
    }
    ConcurrentLinkedQueue<String> getList() {
        return list;
    }


}
