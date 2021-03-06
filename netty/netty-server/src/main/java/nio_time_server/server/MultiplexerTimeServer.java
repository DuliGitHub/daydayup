package nio_time_server.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class MultiplexerTimeServer implements Runnable{

    private Selector selector;

    private ServerSocketChannel socketChannel;

    private volatile boolean stop;

    public MultiplexerTimeServer(int  port) {
        try {
            selector = Selector.open();
            socketChannel = ServerSocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.socket().bind(new InetSocketAddress(port),1024);
            socketChannel.register(selector,SelectionKey.OP_ACCEPT);
            System.out.println("The time server is start in port : " + port);
        }catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop(){
        this.stop = true;
    }
    @Override
    public void run() {

        while (! stop){
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();
                SelectionKey key;
                while (it.hasNext()){
                    key = it.next();
                    it.remove();
                    try {
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

            }catch (Throwable t){
t.printStackTrace();
            }
        }
        /**多路复用器关闭后，所有注册在上面的Channel和pipe 等资源都会被自动去注册并关闭，所以不需要重复释放资源**/
        if(selector != null){
            try {
                selector.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    private void handleInput(SelectionKey key) throws IOException{
        if(key.isValid()){
            //处理新接入的请求消息
            if(key.isAcceptable()){
                //Accept the new connection
                ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
                SocketChannel sc = ssc.accept();//完成tcp3次握手
                sc.configureBlocking(false);
                //Add the new connection to the selector
                sc.register(selector,SelectionKey.OP_READ);
            }
            if(key.isReadable()){
                // Read the data
                SocketChannel sc = (SocketChannel)key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);
                if(readBytes > 0){
                    readBuffer.flip();/** 将缓冲区当前的limit 设置为position，position 设置为0，用于后续对缓冲区的读取操作 **/
                    byte[] bytes = new byte[readBuffer.remaining()];//??????
                    readBuffer.get(bytes);//复制到新数组
                    String body = new String(bytes,"UTF-8");
                    System.out.println("The time server receive order : " + body);
                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).toString():
                            "BAD ORDER";
                    doWrite(sc,currentTime);
                }else if(readBytes < 0){//返回-1，表示链路已经关闭，需要关闭SocketChannel，释放资源
                    // 对端链路关闭
                    key.cancel();
                    sc.close();
                }else ;//读到0字节，忽略
            }
        }
    }

    private void doWrite(SocketChannel channel,String response) throws IOException{
        if(response != null && response.trim().length() > 0){
            byte[] bytes = response.getBytes();// 编码成字节数组，然后根据字节数组的容量创建ByteBuffer
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);// put将字节数组复制到缓冲区中
            writeBuffer.flip();
            channel.write(writeBuffer);
        }
    }


}
