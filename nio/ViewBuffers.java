package com.zhanghao.nio;

import java.nio.*;

/**
 * Created by zhanghao on 16/11/23.
 */
public class ViewBuffers {
    public static void main(String... args){
        ByteBuffer bb = ByteBuffer.wrap(new byte[]{0, 0, 0, 0, 0, 0, 0, 'a'});
        bb.rewind();
        System.out.println("Byte Buffer ");
        while (bb.hasRemaining()){
            System.out.print(bb.position() + " -> " + bb.get() + ", ");
        }
        System.out.println();
        CharBuffer cb = ((ByteBuffer)bb.rewind()).asCharBuffer();
        System.out.println("Char Buffer ");
        while (cb.hasRemaining())
            System.out.print(cb.position() + " -> " + cb.get() + ", ");

        System.out.println();
        FloatBuffer fb = ((ByteBuffer)bb.rewind()).asFloatBuffer();
        System.out.println("Float Buffer ");
        while (fb.hasRemaining())
            System.out.print(fb.position() + " -> " + fb.get() + ", ");

        System.out.println();
        IntBuffer ib = ((ByteBuffer)bb.rewind()).asIntBuffer();
        System.out.println("Int Buffer ");
        while (ib.hasRemaining())
            System.out.print(ib.position() + " -> " + ib.get() + ", ");

        System.out.println();
        LongBuffer lb = ((ByteBuffer)bb.rewind()).asLongBuffer();
        System.out.println("Long Buffer ");
        while (lb.hasRemaining())
            System.out.print(lb.position() + " -> " + lb.get() + ", ");

        System.out.println();
        ShortBuffer sb = ((ByteBuffer)bb.rewind()).asShortBuffer();
        System.out.println("Short Buffer ");
        while (sb.hasRemaining())
            System.out.print(sb.position() + " -> " + sb.get() + ", ");

        System.out.println();
        DoubleBuffer db = ((ByteBuffer)bb.rewind()).asDoubleBuffer();
        System.out.println("Double Buffer ");
        while (db.hasRemaining())
            System.out.print(db.position() + " -> " + db.get() + ", ");
    }
}
