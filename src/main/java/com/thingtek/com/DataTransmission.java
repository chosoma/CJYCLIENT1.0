package com.thingtek.com;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

@Component
public @Data
class DataTransmission implements Runnable {

    private Socket socket;

    private InputStream in;
    private OutputStream out;

    public void init() {
        try {
            in = socket.getInputStream();
            out = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            int com;
            byte[] b = new byte[1024 * 1024];
            while ((com = in.read(b)) != -1) {
                byte[] datas = Arrays.copyOf(b, com);


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(byte[] bytes) {
        try {
            out.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
