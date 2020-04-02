package main;


import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("BeanConfig.xml");

        Player player = context.getBean("player", Player.class);
        System.out.println(player);
        context.close();

    }
}
