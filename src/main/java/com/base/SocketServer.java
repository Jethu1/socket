package com.base;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by jet on 2017/5/17.
 */
public class SocketServer {
    public static void main(String[] args) {
        try{
            ServerSocket serverSocket=new ServerSocket(2013);

            while(true) {
                Socket socket=serverSocket.accept();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String result = bufferedReader.readLine();
                System.out.println("Client say: "+result);
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                printWriter.print("hello Client, I am server");
                printWriter.flush();

                printWriter.close();
                bufferedReader.close();
                socket.close();
                serverSocket.close();
            }

        }catch(Exception e){
            System.out.println("Exception"+ e);
        }
    }
}
