package com.multi_thread.singleton.singleton_1;

/**
 * 延迟加载/懒汉模式
 * 延迟加载就是在调用get() 方法时实例才被创建，常见的实现方法就是get()方法中进行new实例化
 * <p>
 * ** 多线程下，代码错误，不能实现保持单例的状态
 */
public class MyObject {
    private static MyObject myObject;

    public MyObject() {

    }

    public static MyObject getInstance() {
        //延迟加载
        if (myObject != null) {

        } else {
            try {
                Thread.sleep(3000);//模拟在创建对象之前做一些准备性的工作
                myObject = new MyObject();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        return myObject;
    }
}
