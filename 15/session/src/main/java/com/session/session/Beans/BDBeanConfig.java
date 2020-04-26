package com.session.session.Beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.session.session.Beans")
@PropertySource("${classpath:application.properties}")
public class BDBeanConfig {

    @Value("${db.name}")
    String DBNAME;
    @Value("${db.username}")
    String DBUSER;
    @Value("${db.password}")
    String DBPASS;

    @Bean
    DBConnector get() {
        return new DBConnector(DBNAME, DBUSER, DBPASS);
    }

}
