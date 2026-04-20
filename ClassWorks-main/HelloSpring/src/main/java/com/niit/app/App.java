package com.niit.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {

        // 1. Start the Container
        ApplicationContext context =
                new ClassPathXmlApplicationContext("beans.xml");

        // 2. Get the bean
        Greetings greet = (Greetings) context.getBean("myGreetings");

        // 3. Use it
        greet.sayHello();
    }
}