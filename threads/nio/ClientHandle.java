package com.zhanghao.threads.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by zhanghao on 16/11/28.
 */
public class ClientHandle implements Runnable{
    private String host;
    private int port;
    private Selector selector;
    private SocketChannel socketChannel;
    private volatile boolean started;

    public ClientHandle(String host, int port){
        this.host = host;
        this.port = port;

        try {
            //创建选择器
            selector = Selector.open();
            //打开监听通道
            socketChannel = SocketChannel.open();
            //如果为 true，则此通道将被置于阻塞模式；如果为 false，则此通道将被置于非阻塞模式
            socketChannel.configureBlocking(false);
            started = true;
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop(){
        started = false;
    }

    @Override
    public void run() {
        try{
            doConnect();
        }catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
        //循环遍历selector
        while (started){
            try {
                //无论是否有读写事件发生，selector每隔1s被唤醒一次
                selector.select(1000);
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();
                SelectionKey key = null;
                while (it.hasNext()){
                    key = it.next();
                    it.remove();
                    try{
                        handleInput(key);
                    }catch (Exception e){
                        if(key != null){
                            key.cancel();
                            if(key.channel() != null){
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        //selector关闭后会自动释放里面管理的资源
        if(selector != null){
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException {
        if(key.isValid()){
            SocketChannel sc = (SocketChannel) key.channel();
            System.out.println("key.isAcceptable():"+key.isAcceptable());
            System.out.println("key.isReadable():"+key.isReadable());
            System.out.println("key.isWritable():"+key.isWritable());
            System.out.println("key.isValid():"+key.isValid());
            if(key.isConnectable()){
                if(sc.finishConnect()){
//                    System.out.println("key.isConnectable(): "+ key.isConnectable());
//                    System.out.println("sc.finishConnect(): "+ sc.finishConnect());
//                    System.out.println("socketChannel.finishConnect(): "+ socketChannel.finishConnect());
                    doWrite(sc,"sjkaljsklajskljaks");
                }
                sc.register(selector, SelectionKey.OP_READ);

            }
            if(key.isReadable()){
                //创建ByteBufffer，并开辟出一个1M的缓冲区
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                //读取请求码流，返回读取到的字节数
                int readBytes = sc.read(buffer);
                //读取到字节，对字节进行编解码
                if(readBytes > 0){
                    //将缓冲区当前的limit设置为position = 0, 用于后续对缓冲区的读取操作
                    buffer.flip();
                    //根据穿冲去可读字节数创建字节数组
                    byte[] bytes = new byte[buffer.remaining()];
                    //将缓冲区可读字节数组复制到新建的数组中
                    buffer.get(bytes);
                    String result = new String(bytes, "UTF-8");
                    System.out.println("客户端收到的消息：" + result);
                }else if(readBytes < 0){
                    key.cancel();
                    sc.close();
                }
            }
        }
    }
    //异步发送消息
    private void doWrite(SocketChannel channel, String request) throws IOException {
        //将消息编码为字节数组
        byte[] bytes = request.getBytes();
        //根据数组容量创建ByteBuffer
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        //将字节数组复制到缓冲区
        writeBuffer.put(bytes);
        //flip操作
        writeBuffer.flip();
        //发送缓冲区的字节数组
        channel.write(writeBuffer);
        //此处不含处理"写半包"的代码
    }

    private void doConnect() throws IOException {
//        socketChannel.register(selector, SelectionKey.OP_CONNECT);
//        System.out.println(socketChannel.connect(new InetSocketAddress(host, port)));    //false
//        System.out.println(socketChannel.finishConnect());                              //true
//        System.out.println(socketChannel.isConnectionPending());                        //false
        boolean success = false;
        try {
            boolean connected = socketChannel.connect(new InetSocketAddress(host, port));
            if(!connected) {
                socketChannel.register(selector, SelectionKey.OP_CONNECT);
            }
            success = true;
        }finally {
            if(!success){
                stop();
            }
        }


    }
    public void sendMsg(String msg) throws IOException {
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.finishConnect();
//        System.out.println(socketChannel.connect(new InetSocketAddress(host, port)));    //false
//        System.out.println(socketChannel.isConnectionPending());                        //false
//        System.out.println(socketChannel.finishConnect());                              //true
//        System.out.println(socketChannel.isConnected());
        doWrite(socketChannel, msg);
    }
}
