package socketNio.server;

import java.io.ByteArrayOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * 客户端
 */
public class MyClientSocketChannel {
    public static void main(String[] args) throws Exception {
        Selector sel = Selector.open();                //挑选器
        SocketChannel sc = SocketChannel.open();    //开启通道
        InetSocketAddress addr = new InetSocketAddress("localhost", 8888);//服务器地址
        sc.connect(addr);                            //连接

        sc.configureBlocking(false);                //*****非阻塞模式
        sc.register(sel, SelectionKey.OP_READ);        //注册read事件

        new Sender(sc).start();                        //开启线程发送消息

        //
        ByteBuffer buf = ByteBuffer.allocate(1024);
        //开始挑选
        while (true) {
            sel.select();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while (sc.read(buf) != 0) {
                buf.flip();
                baos.write(buf.array(), 0, buf.limit());
                buf.clear();
            }
            String str = new String(baos.toByteArray());
            System.out.println(str);
        }
    }
}
