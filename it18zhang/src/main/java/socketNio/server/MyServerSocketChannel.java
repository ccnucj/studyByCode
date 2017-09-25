package socketNio.server;

import java.io.ByteArrayOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * Server,NIO本质是非阻塞。 Selector SelectionKey ServerSocketChannel SocketChannel
 */
public class MyServerSocketChannel {
    public static void main(String[] args) {
        Selector sel = null;
        //
        try {
            sel = Selector.open();                                            //开启挑选器
            ServerSocketChannel ssc = ServerSocketChannel.open();            //开启服务器SocketChannel
            InetSocketAddress addr = new InetSocketAddress("0.0.0.0", 8888);
            ssc.bind(addr);
            ssc.configureBlocking(false);                                    //配置非阻塞
            ssc.register(sel, SelectionKey.OP_ACCEPT);                        //***** 在挑选器中注册ssc通道 *****
        } catch (Exception e) {
            e.printStackTrace();
        }

        SocketChannel sc0 = null;
        ByteBuffer buf = null;
        // CharBuffer cbuf = CharBuffer.allocate(1024);
        while (true) {
            // 开始挑选
            try {
                sel.select();                                                //开始挑选
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 处理发生的事件
            Set<SelectionKey> keys = sel.selectedKeys();                    //获得selectedKey集合
            for (SelectionKey key : keys) {
                try {
                    // 是否是accept事件
                    if (key.isAcceptable()) {
                        // 得到服务器通道
                        ServerSocketChannel ssc0 = (ServerSocketChannel) key.channel();
                        // 接受客户端连接，返回SocketChannel
                        sc0 = ssc0.accept();
                        System.out.println(getClientInfo(sc0.socket()) + " : 上线了!");
                        // 注册非阻塞
                        sc0.configureBlocking(false);
                        // 在挑选器中注册新产生的SocketChannel
                        sc0.register(sel, SelectionKey.OP_READ | SelectionKey.OP_WRITE | SelectionKey.OP_CONNECT);
                        //
                    }
                    // 可以读取数据了。
                    if (key.isReadable()) {
                        // 得到SocketChannel
                        sc0 = (SocketChannel) key.channel();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();

                        //
                        buf = ByteBuffer.allocate(1024);
                        // 读取客户端发送的数据
                        while (sc0.read(buf) != 0) {
                            buf.flip();
                            baos.write(buf.array(), 0, buf.limit());
                            buf.clear();                        //重置buf
                        }
                        // 构造缓冲区，写回去client.
                        String str = "hello : " + new String(baos.toByteArray());
                        // 输出内容
                        System.out.println(getClientInfo(sc0.socket()) + str);

                        //回传信息
                        buf = ByteBuffer.allocate(str.getBytes().length);
                        buf.put(str.getBytes());
                        buf.flip();
                        sc0.write(buf);
                        buf.clear();
                    }
                } catch (Exception e) {
                    //从selector注销通道
                    key.cancel();
                    //
                    if (key.channel() instanceof SocketChannel) {
                        Socket s = ((SocketChannel) key.channel()).socket();
                        System.out.println(getClientInfo(s) + " : 下线了！");
                    }
                }
            }
            keys.clear();
        }
    }

    /**
     * 获得客户端信息
     */
    private static String getClientInfo(Socket socket) {
        InetSocketAddress addr = (InetSocketAddress) socket.getRemoteSocketAddress();
        String ip = addr.getAddress().getHostAddress();
        String port = addr.getPort() + "";
        return "[" + ip + ":" + port + "] ";
    }
}
