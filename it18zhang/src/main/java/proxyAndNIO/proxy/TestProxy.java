package proxyAndNIO.proxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 不改变源代码，还为类增加新功能。
 * jdk的动态代理。
 */
public class TestProxy {
    @Test
    public void test1() {

        //目标对象
        HelloWorldServiceImpl s = new HelloWorldServiceImpl();

        //调用处理器
        MyInvocationHandler h = new MyInvocationHandler(s);

        //接口列表
        Class[] clazzes = {HelloWorldService.class};

        //创建代理对象
        /**
         * loader - the class loader to define the proxy class
         * interfaces - the list of interfaces for the proxy class to implement
         * h - the invocation handler to dispatch method invocations to
         */
        HelloWorldService hws = (HelloWorldService) Proxy
                .newProxyInstance(HelloWorldService.class.getClassLoader(), clazzes, h);
        //访问代理的方法.
        hws.sayHello("tom");

        //该方法用于获取指定代理对象所关联的调用处理器
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(hws);
        System.out.println(invocationHandler);

    }

    @Test
    public void test2() {


    }

    //代理处理器
    class MyInvocationHandler implements InvocationHandler {
        //目标对象
        private Object target;

        public MyInvocationHandler(Object target) {
            this.target = target;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("hello---world");
            //调用目标对象的方法
            return method.invoke(target, args);
        }
    }
}
