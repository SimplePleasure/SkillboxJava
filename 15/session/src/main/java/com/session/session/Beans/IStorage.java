package com.session.session.Beans;

import java.util.Queue;

public interface IStorage {
    boolean addLine(String str);
    Queue<String> getList();
}
