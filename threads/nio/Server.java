package com.zhanghao.threads.nio;

/**
 * Created by zhanghao on 16/11/28.
 */
public class Server {
    private static int DEFAULT_PORT = 12345;
    private static ServerHandle serverHandle;

    public static void start(){
        start(DEFAULT_PORT);
    }
    public static synchronized void start(int port){
        if(serverHandle != null){
            serverHandle.stop();
        }
        serverHandle = new ServerHandle(port);
        new Thread(serverHandle, "server").start();
    }

    public static void main(String... args){
        start();
    }

}
