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
    private String userInfo;

    public VisitSaver(String name, String browserInfo) {
        this.user = name;
        this.userInfo = browserInfo;
    }
    VisitSaver() {}

    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getUserInfo() {
        return userInfo;
    }
    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }
}
