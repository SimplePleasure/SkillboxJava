package com.session.session.model;

import javax.persistence.*;

@Entity
@Table(name = "notes")
public class VisitSaver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "user")
    private String name;
    @Column(name = "user_info")
    private String browserInfo;

    public VisitSaver(String name, String browserInfo) {
        this.name = name;
        this.browserInfo = browserInfo;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBrowserInfo() {
        return browserInfo;
    }
    public void setBrowserInfo(String browserInfo) {
        this.browserInfo = browserInfo;
    }
}
