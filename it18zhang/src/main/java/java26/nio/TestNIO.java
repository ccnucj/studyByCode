package java26.nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestNIO {

    /**
     * 测试堆内存缓冲区
     *
     * @throws Exception
     */
    @Test
    public void testFileCopy() throws Exception {
        //源文件
        FileInputStream fis = new FileInputStream("d:/a.txt");
        FileChannel srcFc = fis.getChannel();

        //目标文件
        FileOutputStream fos = new FileOutputStream("d:/b.txt");
        FileChannel destFc = fos.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(1024);

        while (srcFc.read(buf) != -1) {
            buf.limit(buf.position());
            buf.position(0);
            destFc.write(buf);
            buf.clear();
        }
        srcFc.close();
        fis.close();

        destFc.close();
        fos.close();
    }
}