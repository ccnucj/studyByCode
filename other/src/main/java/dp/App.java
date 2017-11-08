package dp;


import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

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
     * 可以利用此机制，实现权限的控制，即位相或
     */
    @Test
    public void m1() {

        Modifier.isPublic(15);
    }

    @Test
    public void m2() throws Exception {
        Class clazz = Class.forName("dp.AAA");
        Constructor declaredConstructor = clazz.getDeclaredConstructor(int.class);
        AAA aaa = (AAA) declaredConstructor.newInstance(123);
        System.out.println(aaa.getAge());



    }

}
