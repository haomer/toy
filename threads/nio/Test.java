package com.zhanghao.threads.nio;

import java.util.Scanner;

/**
 * Created by zhanghao on 16/11/28.
 */
public class Test {
    public static void main(String... args) throws Exception {
        Server.start();
        Thread.sleep(1000);

        Client.start();
//        Client.sendMsg("shkhjkhjkhjkhjk");
    }
}
