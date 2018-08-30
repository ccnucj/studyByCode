package vip;

import org.junit.Test;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

	/**
	 * 声明式代码
	 */
	@Test
    public void m1() {
    	int[] iArr = {1,2,3,4,5};
	    Arrays.stream(iArr).forEach(System.out::println);
    }
}
