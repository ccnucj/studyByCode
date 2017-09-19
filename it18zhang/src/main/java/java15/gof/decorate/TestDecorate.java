package java15.gof.decorate;

import java.io.BufferedWriter;
import java.io.FileWriter;

import org.junit.Test;

public class TestDecorate {
	
	@Test
	public void testFileWriter() throws Exception{
		//非缓冲区writer
		FileWriter fw = new FileWriter("d:/arch/a.txt");
		fw.write("hello");
		fw.close();
	}
	
	@Test
	public void testBufferedWriter() throws Exception{
		FileWriter fw = new FileWriter("d:/arch/a.txt");
		//非缓冲区writer
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("hello");
		//bw.flush();
		bw.write("world");
		//bw.flush();
		
		bw.close();
		fw.close();
	}
}
