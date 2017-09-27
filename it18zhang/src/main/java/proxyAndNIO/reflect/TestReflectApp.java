package proxyAndNIO.reflect;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class TestReflectApp {

    public static void main(String[] args) throws Exception {
        //
        InputStream is = new FileInputStream("D:\\eclipse-workspace-bigdata4\\Java25\\src\\objects.properties");
//		byte[] bytes = new byte[is.available()];
//		is.read(bytes);
//		//is.close();
        //System.out.println(new String(bytes));
        Properties prop = new Properties();
        prop.load(is);

        //
        String objClass = prop.getProperty("object.class");
        Class clazz = Class.forName(objClass);
        Object obj = clazz.newInstance();

        //name
        String propName = prop.getProperty("object.prop1.name");
        String propValue = prop.getProperty("object.prop1.value");
        Field f = clazz.getDeclaredField(propName);
        if (f.getType() == String.class) {
            f.setAccessible(true);
            f.set(obj, propValue);
        }


        //name
        propName = prop.getProperty("object.prop2.name");
        propValue = prop.getProperty("object.prop2.value");
        f = clazz.getDeclaredField(propName);
        if (f.getType() == int.class) {
            f.setAccessible(true);
            Integer i = Integer.parseInt(propValue);
            f.set(obj, i);
        }

    }

}
