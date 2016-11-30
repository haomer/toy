package com.zhanghao.threads.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhanghao on 16/11/28.
 */
public class ServerBetter {
    private static int DEFAULT_PORT = 12345;
    //单例ServerSocket
    private static ServerSocket server;
    //线程池 懒汉式的单例

    private static ExecutorService executorService = Executors.newFixedThreadPool(60);
    public static void start() throws IOException {
        start(DEFAULT_PORT);
    }
    public static void start(int port) throws IOException {
        if(server != null) return;
        try {
            server = new ServerSocket(port);
            Socket socket;
            System.out.println("服务器已启动，端口号：" + port);
            while (true){
                socket = server.accept();
                executorService.execute(new ServerHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(server != null){
                System.out.println("服务器已关闭");
                server.close();
                server = null;
            }
        }

    }
}
