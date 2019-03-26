package com.jiagouedu.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定时线程池        submit -- 有返回值   execute -- 无返回值
 */
public class SchedulePoolTest {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("悟空是只猴子");
            }
        });
        scheduledExecutorService.execute(()->{
            System.out.println("execute");
        });

        scheduledExecutorService.schedule(() -> {
            System.out.println("5");
        }, 5, TimeUnit.SECONDS);

        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {

                    System.out.println("悟空是只猴子---5");
              //  throw new RuntimeException(); //有异常了就不会在执行了

            }
        },3,5, TimeUnit.SECONDS);


        scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {

                System.out.println("悟空是只猴子---8");
                //  throw new RuntimeException(); //有异常了就不会在执行了

            }
        },0,8, TimeUnit.SECONDS);

    }
}
