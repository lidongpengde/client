package com.boot.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by lidongpeng on 2018/2/9.
 */
public class RPCProxyClient implements InvocationHandler{
    private Object obj;

    public RPCProxyClient(Object obj){
        this.obj=obj;
    }

    /**
     * 得到被代理对象;
     */
    public static Object getProxy(Object obj){
        return java.lang.reflect.Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(), new RPCProxyClient(obj));
    }

    /**
     * 调用此方法执行
     */
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        //结果参数;
        Object result = new Object();
        // ...执行通信相关逻辑
        System.out.println("执行了invoke");
        return result;
    }
}
