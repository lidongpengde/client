package com.boot;

import com.boot.test.IhelloWorldService;

/**
 * Created by lidongpeng on 2018/2/9.
 */
public class HelloWorldService implements IhelloWorldService{
    public void sayHello(String str){
        System.out.println(str);
    }
}
