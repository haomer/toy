package com.zhanghao.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by zhanghao on 16/11/22.
 */
public class ChannelCopy {
    private static String path = "/Users/zhanghao/Documents/wxprogram-demo/src/test/java/com/zhanghao/nio/";

    private static final int BSIZE = 1024;
    public static void main(String... args) throws Exception{
        if(args.length != 2){
            System.out.println("arguments: sourcefile destfile");
            System.exit(1);
        }
        FileChannel in = new FileInputStream(args[0]).getChannel();
        FileChannel out = new FileOutputStream(args[1]).getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
        while (in.read(buffer) != -1){
            buffer.flip();
            out.write(buffer);
            buffer.clear();
        }
    }
}
