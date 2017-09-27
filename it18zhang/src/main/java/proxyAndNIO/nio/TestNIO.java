package proxyAndNIO.nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class TestNIO {
    /**
     * 测试堆内存缓冲区
     */
    @Test
    public void test1HeapByteBuffer() {
        int size = 1024 * 1024 * 450;
        ByteBuffer buf = ByteBuffer.allocate(size);
        System.out.println(buf.capacity());
        buf = null;
        System.gc();        //OK
        System.out.println("kkk");
    }

    /**
     * 测试堆内存缓冲区
     *
     * @throws Exception
     */
    @Test
    public void test1DirectByteBuffer() throws Exception {

        int size = 1024 * 1024 * 1024 * 1;
        ByteBuffer buf = ByteBuffer.allocateDirect(size);

        //通过反射，回收离堆内存
        Class clazz = Class.forName("java.nio.DirectByteBuffer");
        Method m = clazz.getDeclaredMethod("cleaner");
        m.setAccessible(true);
        Object cleaner = m.invoke(buf);

        Class cleanerClazz = cleaner.getClass();
        Method m2 = cleanerClazz.getDeclaredMethod("clean");
        m2.setAccessible(true);
        m2.invoke(cleaner);

        System.out.println(buf.get(1000));
        System.gc();
        System.out.println("dd");
    }

    /**
     * 测试垃圾回收
     */
    @Test
    public void testGC() {
        int size = Integer.MAX_VALUE;
        byte[] buf = new byte[size];
        byte[] buf2 = buf;
        List<byte[]> list = new ArrayList<byte[]>();
        list.add(buf2);
        buf = null;
        System.gc();    //进行垃圾回收
        buf2 = null;
        System.gc();    //进行垃圾回收
        System.out.println("xxx");
        list.clear();    //清空集合
        System.gc();    //进行垃圾回收
        System.out.println("xxx");
    }

    /**
     * 测试垃圾回收
     */
    @Test
    public void testChannel() throws Exception {
        FileInputStream fis = new FileInputStream("d:/a.txt");
        FileChannel srcFC = fis.getChannel();

        FileOutputStream fos = new FileOutputStream("d:/b.txt");
        FileChannel destFC = fos.getChannel();

        //定义缓冲区
        ByteBuffer buf = ByteBuffer.allocate(11);
        while (srcFC.read(buf) != -1) {
            buf.flip();
            destFC.write(buf);
            buf.clear();
        }
        fis.close();
        fos.close();
    }
}
