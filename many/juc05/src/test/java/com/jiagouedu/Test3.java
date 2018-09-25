package com.jiagouedu;/* ━━━━━━如来保佑━━━━━━
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

import java.util.concurrent.*;

public class Test3 {

   public static void main(String[] args) {
     // ExecutorService executorService = Executors.newCachedThreadPool();//todo 阿里不推荐

    // ExecutorService executorService = Executors.newFixedThreadPool(1);
      /***
       * 设置有问题的,maximumPoolSize设置不大，导致创建不了线程来做事情
       */
 /*     ExecutorService executor = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
      for (int i = 0; i < 10; i++) {
         int j = i;
         executor.execute(new Runnable() {
            @Override
            public void run() {
               System.out.println("悟空活好人帅");
            }
         });
      }*/

      /***
       * LinkedBlockingQueue  这个队列没有最大值限制maximumPoolSize作废\
       * 执行的个数减去核心数量就是队列的数量

       ThreadPoolExecutor executor = new ThreadPoolExecutor(0, 100, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
       for (int i = 0; i < 10; i++) {
       int j = i;
       executor.execute(new Runnable() {
      @Override
      public void run() {
      System.out.println("悟空活好人帅");
      }
      });
       }
       System.out.println(executor.getQueue().size());
       */


      /****
       * ArrayBlockingQueue跟maximumPoolSize有关，大于这个数之和就会放入队列中，如果队列满了就会异常
       */
      ThreadPoolExecutor executor = new ThreadPoolExecutor(0, 7, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1));
      for (int i = 0; i < 10; i++) {
         int j = i;
         executor.execute(new Runnable() {
            @Override
            public void run() {
               System.out.println("悟空活好人帅");
            }
         });
      }
      System.out.println(executor.getQueue().size());

   }

}


