package com.thingtek.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PortConfig {
    private static String addr;
    private static int port;

    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("proerty\\proerty.properties"));
            addr = properties.getProperty("addr");
            port = Integer.parseInt(properties.getProperty("port"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getAddr() {
        return addr;
    }

    public static int getPort() {
        return port;
    }
}
