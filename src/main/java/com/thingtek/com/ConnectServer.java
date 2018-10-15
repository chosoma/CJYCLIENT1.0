package com.thingtek.com;

import com.thingtek.config.PortConfig;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.Socket;

@Component
public class ConnectServer {

    public void init() {
        try {
            Socket socket = new Socket(PortConfig.getAddr(), PortConfig.getPort());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
