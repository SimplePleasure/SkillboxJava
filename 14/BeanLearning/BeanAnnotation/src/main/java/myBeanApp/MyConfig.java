package myBeanApp;

import myBeanApp.entries.Engine;
import myBeanApp.entries.Transmission;
import myBeanApp.entries.Wheel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("myBeanApp")
@PropertySource("${classpath:application.properties}")

public class MyConfig {

//    @Bean
//    Detail engine () {
//        return new Engine();
//    }
//
//    @Bean
//    Detail transmission() {
//        return new Transmission();
//    }
//
//    @Bean
//    Detail wheel () {
//        return new Wheel();
//    }
//
//    @Bean
//    Car car() {
//        return new Car(engine(), transmission(), wheel());
//    }

}
