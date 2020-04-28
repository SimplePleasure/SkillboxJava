package com.session.session.model;

import javax.persistence.*;

@Entity
@Table(name = "notes")
public class VisitSaver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "user")
    private String user;
    @Column(name = "user_info")
    private String user_info;

    public VisitSaver(String name, String browserInfo) {
        this.user = name;
        this.user_info = browserInfo;
    }
    VisitSaver() {}

    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getUser_info() {
        return user_info;
    }
    public void setUser_info(String user_info) {
        this.user_info = user_info;
    }
}
