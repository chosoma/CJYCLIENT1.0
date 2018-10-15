package com.thingtek.view;

import com.thingtek.view.frame.login.Login;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;

public class Start {
    public static void main(String[] args) {
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        try {
            UIManager.setLookAndFeel(lookAndFeel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String config = "appcontext/applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        ac.getBean(Login.class).init();
    }
}
