package hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
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
	public void readFile() throws Exception {
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
	public void readFileByAPI() throws Exception {
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://cj01:9000/");    //对应的是core-site.xml配置，默认的端口是8020
//		conf.set("HADOOP_USER_NMAE","chenjie");
		FileSystem fs = FileSystem.get(conf);
		Path p = new Path("/flowSum/input/HTTP_20130313143750.dat");
		FSDataInputStream fis = fs.open(p);
		byte[] buf = new byte[1024];
		int len = -1;

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		while ((len = fis.read(buf)) != -1) {
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
	public void readFileByAPI2() throws Exception {
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://cj01:9000/");
		FileSystem fs = FileSystem.get(conf);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Path p = new Path("/flowSum/input/HTTP_20130313143750.dat");
		FSDataInputStream fis = fs.open(p);
		IOUtils.copyBytes(fis, baos, 1024);
		System.out.println(new String(baos.toByteArray()));
	}

	/**
	 * mkdir
	 */
	@Test
	public void mkdir() throws Exception {
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.231.201:8020/");
		FileSystem fs = FileSystem.get(conf);
		fs.mkdirs(new Path("/user/centos/myhadoop"));
	}

	/**
	 * putFile
	 */
	@Test
	public void putFile() throws Exception {
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.231.201:8020/");
		FileSystem fs = FileSystem.get(conf);
		FSDataOutputStream out = fs.create(new Path("/user/centos/myhadoop/a.txt"));
		out.write("helloworld".getBytes());
		out.close();
	}

	/**
	 * removeFile
	 */
	@Test
	public void removeFile() throws Exception {
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.231.201:8020/");
		FileSystem fs = FileSystem.get(conf);
		Path p = new Path("/user/centos/myhadoop");
		fs.delete(p, true);
	}

	/**
	 * put文件指定副本数和块大小
	 */
	@Test
	public void putFile2() throws Exception {
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.231.201:8020/");
		FileSystem fs = FileSystem.get(conf);
		FSDataOutputStream out = fs.create(new Path("/user/centos/hadoop/a.txt"), true, 1024, (short) 2, 1024);
		IOUtils.copyBytes(new FileInputStream("D:\\README.txt"), out, 1024);
		System.out.println("over");
	}

	@Test
	public void listFS() throws Exception {
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://cj01:9000/");
		FileSystem fs = FileSystem.get(conf);
		printlnPath(fs, new Path("/"));
	}

	/**
	 * 递归输出hdfs目录结构
	 */
	private void printlnPath(FileSystem fs, Path p) {
		try {
			//输出路径
			System.out.println(p.toUri().toString());
			if (fs.isDirectory(p)) {
				FileStatus[] files = fs.listStatus(p);
				if (files != null && files.length > 0) {
					for (FileStatus f : files) {
						Path p0 = f.getPath();
						printlnPath(fs, p0);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
