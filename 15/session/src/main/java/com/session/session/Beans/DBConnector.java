package com.session.session.Beans;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;


public class DBConnector {

    String dbname;
    String user;
    String pass;

    DBConnector(String db, String name, String password) {
        dbname = db;
        user = name;
        pass = password;
    }

    public ConcurrentHashMap<String, String> getStatistics() {
        ConcurrentHashMap<String, String> uses = new ConcurrentHashMap<>();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbname +
                    "?user=" + user + "&password=" + pass + "&useUnicode=true&serverTimezone=UTC");
            ResultSet c = connection.createStatement().executeQuery("SELECT COUNT(*) AS `count` from sessions.notes");
            c.next();
            double p = ((double)c.getInt("count"))/100;

            ResultSet rs = connection.createStatement().executeQuery("SELECT user_info, COUNT(*) as `count` FROM" +
                    " sessions.notes Group BY user_info ");
            while (rs.next()) {
                String browser = rs.getString("user_info");
                int amount = rs.getInt("count");
                String s = String.format("%.1f", amount / p);
                s+="%";
                uses.put(browser, s);
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return uses;
    }
}
