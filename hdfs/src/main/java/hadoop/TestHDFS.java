package hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 完成hdfs操作 
 */
public class TestHDFS {
	/**
	 * 读取hdfs文件
	 */
	@Test
	public void readFile() throws Exception{
		//注册url流处理器工厂(hdfs)
		URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
		
		URL url = new URL("hdfs://cj01:9000/flowSum/input/HTTP_20130313143750.dat");
		URLConnection conn = url.openConnection();
		InputStream is = conn.getInputStream();
		byte[] buf = new byte[is.available()];
		is.read(buf);
		is.close();
		String str = new String(buf);
		System.out.println(str);
	}
	
	/**
	 * 通过hadoop API访问文件
	 */
	@Test
	public void readFileByAPI() throws Exception{
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://cj01:9000/");    //对应的是core-site.xml配置，默认的端口是8020
//		conf.set("HADOOP_USER_NMAE","chenjie");
		FileSystem fs = FileSystem.get(conf) ;
		Path p = new Path("/flowSum/input/HTTP_20130313143750.dat");
		FSDataInputStream fis = fs.open(p);
		byte[] buf = new byte[1024];
		int len = -1 ; 
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		while((len = fis.read(buf)) != -1){
			baos.write(buf, 0, len);
		}
		fis.close();
		baos.close();
		System.out.println(new String(baos.toByteArray()));
	}
	
	/**
	 * 通过hadoop API访问文件
	 */
	@Test
	public void readFileByAPI2() throws Exception{
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.231.201:8020/");
		FileSystem fs = FileSystem.get(conf) ;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Path p = new Path("/user/centos/hadoop/index.html");
		FSDataInputStream fis = fs.open(p);
		IOUtils.copyBytes(fis, baos, 1024);
		System.out.println(new String(baos.toByteArray()));
	}
	
	/**
	 * mkdir
	 */
	@Test
	public void mkdir() throws Exception{
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.231.201:8020/");
		FileSystem fs = FileSystem.get(conf) ;
		fs.mkdirs(new Path("/user/centos/myhadoop"));
	}
	
	/**
	 * putFile
	 */
	@Test
	public void putFile() throws Exception{
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.231.201:8020/");
		FileSystem fs = FileSystem.get(conf) ;
		FSDataOutputStream out = fs.create(new Path("/user/centos/myhadoop/a.txt"));
		out.write("helloworld".getBytes());
		out.close();
	}
	
	/**
	 * removeFile
	 */
	@Test
	public void removeFile() throws Exception{
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.231.201:8020/");
		FileSystem fs = FileSystem.get(conf) ;
		Path p = new Path("/user/centos/myhadoop");
		fs.delete(p, true);
	}
}
