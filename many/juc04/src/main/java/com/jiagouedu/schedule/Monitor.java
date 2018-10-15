package com.jiagouedu.schedule;

import java.util.Date;

public class Monitor implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Start. Time = " + new Date());
        System.out.println("java.version: " + System.getProperty("java.version"));
        System.out.println("java.class.path : " + System.getProperty("java.class.path"));
        System.out.println("user.dir : " + System.getProperty("user.dir"));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " End. Time = " + new Date());
        System.out.println("===================================");
        throw new RuntimeException();


    }
}
