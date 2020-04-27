package com.session.session.Beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

@Service
public class DBConnector {

    String dbname;
    String user;
    String pass;

    DBConnector(@Value("${spring.datasource.myDb}") String db,
                @Value("${spring.datasource.username}") String name,
                @Value("${spring.datasource.password}") String password) {

        dbname = db;
        user = name;
        pass = password;
    }


    public HashMap<String, String> getStatistics() {
        HashMap<String, String> uses = new HashMap<>();

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
