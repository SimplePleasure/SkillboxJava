package com.session.session;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class TextStorage {

    private List<String> list = new ArrayList<>();

    boolean addLine(String str) {
        return list.add(str);
    }
    int getSize() {
        return list.size();
    }
    List getList() {
        return list;
    }


}
