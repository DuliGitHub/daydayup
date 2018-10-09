package com.multi_thread.singleton.singleton_static;

public class MyObject {
    private static MyObject instance = null;

    public MyObject() {

    }

    static {
        instance = new MyObject();
    }

    public static MyObject getInstance() {
        return instance;
    }
}
