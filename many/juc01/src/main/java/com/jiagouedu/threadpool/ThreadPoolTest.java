package com.jiagouedu.threadpool;/*
 * ━━━━━━如来保佑━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　┻　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━永无BUG━━━━━━
 * 图灵学院-悟空老师
 * 以往视频加小乔老师QQ：895900009
 * 悟空老师QQ：245553999
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolTest implements  Runnable {

    public static void main(String[] args) {
       ExecutorService executorService= Executors.newCachedThreadPool();
       // new Thread(new MonitorThreadPoolUtil((ThreadPoolExecutor) executorService,1)).start();
      /* executorService.submit(new Runnable() {
           @Override
           public void run() {
           System.out.println(Thread.currentThread().getName());
           System.out.println("悟空是只猴子");
         }
       });*/
      new Thread(new MonitorThreadPoolUtil((ThreadPoolExecutor) executorService,1)).start();
      executorService.execute(new Runnable() {
        @Override
        public void run() {
          System.out.println(Thread.currentThread().getName());
          System.out.println("悟空是只猴子");
          try {
            Thread.sleep(10000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      });
        executorService.shutdown();

    }

  @Override
  public void run() {

  }
}
