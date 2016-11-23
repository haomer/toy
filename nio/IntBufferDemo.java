package com.zhanghao.nio;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * Created by zhanghao on 16/11/23.
 */
public class IntBufferDemo {
    private static final int BSIZE = 1024;
    public static void main(String... args){
        ByteBuffer bb = ByteBuffer.allocate(BSIZE);
        IntBuffer ib = bb.asIntBuffer();
        ib.put(new int[]{11, 42, 47, 99, 143, 811, 1016});
        System.out.println(ib.get(3));
        ib.put(3, 1811);
        ib.clear();
        ib.put(new int[]{0, 0});
        ib.flip();
        while (ib.hasRemaining()){
            int i = ib.get();
            System.out.println(i);
        }
    }
}
