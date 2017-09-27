package proxyAndNIO.proxy.duration;




import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *  代理多个接口
 *  public class MyServiceImpl implements MyService, YourService
 */
public class ProxyMain {
    public static void main(String[] args) {
        System.out.println("ProxyMain.hashcode : " + ProxyMain.class.hashCode());
        MyServiceImpl s = new MyServiceImpl();    //目标对象
        System.out.println("s.hashcode : " + s.hashCode());
        TimeHandler h = new TimeHandler(s);        //处理器对象
        Class[] clazz = {MyService.class, YourService.class};//代理接口数组
        MyService ms = (MyService) Proxy.newProxyInstance(ProxyMain.class.getClassLoader(), clazz, h);
        System.out.println(ms.add(2, 2));
        System.out.println(((YourService) ms).multiply(2, 2));
        System.out.println(ms.sub(2, 2));
        System.out.println(((YourService) ms).shiftLeft(1, 2));
        System.out.println(((YourService) ms).shiftRight(4, 2));
        System.out.println(((YourService) ms).singleAnd(4, 2));
        System.out.println(((YourService) ms).doubleAnd(4, 2));
        System.out.println(((YourService) ms).divide(4, 4));
        System.out.println(((YourService) ms).divide(4, 4));
    }

    /**
     * 时间处理器
     */
    static class TimeHandler implements InvocationHandler {
        //目标对象
        private Object target;

        public TimeHandler(Object target) {
            this.target = target;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            long start = System.nanoTime();
            //调用目标对象的方法
            Object retVal = method.invoke(target, args);
            System.out.println(method.getName() + " : " + (System.nanoTime() - start));
            //切记:返回目标对象方法的返回值，否则篡改了业务逻辑。
            return retVal;
        }
    }
}
