package com.another;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Socket;

/**
 * Created by jet on 2017/5/16.
 */

    class mythread extends Thread{

        private Socket socket = null;
        private Server server = null;
        private InputStreamReader reader = null;
        char chars[] = new char[64];
        int len;
        private String temp = null;
        public mythread(Socket socket, Server server) {
            // TODO Auto-generated constructor stub
            this.socket = socket;
            this.server = server;
            init();
        }

        private void init(){
            try {
                reader = new InputStreamReader(socket.getInputStream());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            System.out.println("子线程开始工作");
            while(true){
                try {
                    System.out.println("线程"+this.getId()+":开始从客户端读取数据——>");
                    while ((len = ((Reader) reader).read(chars)) != -1) {
                        temp = new String(chars, 0, len);
                        System.out.println("来自客户端"+socket.getPort()+"的消息:" +temp);
                        server.apppendMsg("来自客户端"+socket.getPort()+"的消息:" +temp);
                        server.sendMsgToAll(this.socket, "客户端"+socket.getPort()+"的说:" +temp);
                    }
                    if(socket.getKeepAlive() == false){
                        ((Reader) reader).close();
//                  temp = "线程"+this.getId()+"——>关闭";
//                  System.out.println(temp);
                        temp = "客户端"+socket.getPort()+":退出";
                        server.apppendMsg(temp);
                        socket.close();
                        this.interrupt();
                       // this.stop();
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                    try {
                        ((Reader) reader).close();
                        socket.close();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                }
            }
        }
    }

