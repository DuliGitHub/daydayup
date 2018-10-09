package com.jiagouedu.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutorTest {


    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
/*    executorService.schedule(()->{
      System.out.println("5");
    },5, TimeUnit.SECONDS);*/
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("3");
            }
        }, 0, 3, TimeUnit.SECONDS);
        executorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("3");
            }
        }, 0, 3, TimeUnit.SECONDS);

    }
}
