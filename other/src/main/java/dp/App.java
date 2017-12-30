package dp;


import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static dp.Shape.findMax;

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
    }

    /**
     * 使用函数对象
     */
    @Test
    public void m11() {
        Shape[] shapes = {
                new Shape(123,123),
                new Shape(1234,1234),
                new Shape(1235,1235),
        };
        Shape max = findMax(shapes, new CompareImpl());
        System.out.println(max.getArea() + "," + max.getLength());

    }

}
