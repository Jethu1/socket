package com.many;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by jet on 2017/5/17.
 */
public class ServeSocket extends ServerSocket{
    private static final int SERVER_PORT =2014;

    public ServeSocket()throws IOException {
        super(SERVER_PORT);

        try {
            while (true) {
                Socket socket = accept();
                new ServerThread(socket);//当有请求时，启一个线程处理
            }
        } catch (IOException e) {
        } finally {
            close();
        }
    }
}