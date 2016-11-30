package com.zhanghao.threads.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zhanghao on 16/11/25.
 */
public final class ServerNormal {
    private static int DEFAULT_PORT = 12345;
    private static ServerSocket server;
    public static void start() throws IOException{
        start(DEFAULT_PORT);
    }

    public synchronized static void start(int port)throws IOException{
        if(server != null) return;
        try{
            server = new ServerSocket(port);
            System.out.println("服务器已启动，端口号："+ port);
            Socket socket;
            //通过无线循环监听客户端连接
            //如果没有客户端接入，将阻塞在accpet操作上
            while(true){
                socket = server.accept();
                //当有新的客户端接入时，会执行下面的代码
                //创建一个新的线程处理这图唉socket链路
                new Thread(new ServerHandler(socket)).start();
            }
        }finally {
            if(server != null){
                System.out.println("服务器已关闭。");
                server.close();
                server = null;
            }
        }
    }
}
