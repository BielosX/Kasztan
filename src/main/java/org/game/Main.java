package org.game;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(GameConfiguration.class);
        GameFrame frame = context.getBean(GameFrame.class);
        frame.setVisible(true);
    }
}