package com.multi_thread.singleton.singleton_3_dcl;


public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(MyObject.getInstance().hashCode());
    }
}
