package com.zhanghao.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by zhanghao on 16/11/22.
 */
public class GetChannel {
    private static final int BSIZE = 1024;
    private static String path = "/Users/zhanghao/Documents/wxprogram-demo/src/test/java/com/zhanghao/nio/";

    public static void main(String[] args) throws IOException {

        FileChannel fc = new FileOutputStream(path + "data.txt").getChannel();
        fc.write(ByteBuffer.wrap("Some text".getBytes()));
        fc.close();
        //在文件的最后添加内容
        fc = new RandomAccessFile(path +"data.txt","rw").getChannel();
        fc.position(fc.size()); //移动到最后
        fc.write(ByteBuffer.wrap("Some more".getBytes()));
        fc.close();

        //读取文件
        fc = new FileInputStream(path +"data.txt").getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(BSIZE);
        fc.read(byteBuffer);
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()){
            System.out.print((char) byteBuffer.get());
        }
    }
}
