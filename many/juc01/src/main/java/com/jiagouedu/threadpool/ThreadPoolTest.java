package com.jiagouedu.threadpool;

import com.jiagouedu.thread.Callable01;

import java.util.concurrent.*;

public class ThreadPoolTest {

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        // new Thread(new MonitorThreadPoolUtil((ThreadPoolExecutor) executorService,1)).start();
//        submit方法
//        System.out.println(
//       executorService.submit(new Runnable() {
//           @Override
//           public void run() {
//           System.out.println(Thread.currentThread().getName());
//           System.out.println("悟空是只猴子");
//         }
//       }).get()
//        );
        //---------------------submit Callable---------------
//        Future task =executorService.submit(new Callable<String>(){
//            @Override
//            public String call() throws Exception {
//                int count = 0;
//                for(int i =0 ;i< 100 ;i++){
//                   count += i;
//                }
//                System.out.println(count);
//                return "ok";
//            }
//        });
//        System.out.println(task.get());
        //-----------------------------------
//        executorService.shutdown();

//        new Thread(new MonitorThreadPoolUtil((ThreadPoolExecutor) executorService, 1)).start();


        executorService.execute(
                () ->
                {
                    System.out.println(Thread.currentThread().getName());
                    System.out.println("第一个");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
        Thread.sleep(5000);
        executorService.execute(
                () ->
                {
                    System.out.println(Thread.currentThread().getName());
                    System.out.println("第二个");
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
        executorService.shutdown();

    }

}
