package com.jiagouedu.thread;

/**
 * 线程执行顺序
 * join ： 线程执行顺序
 */
public class ThreadSort {

  public static void main(String[] args) throws InterruptedException {
    Thread thread1 = new Thread(()->{
      System.out.println("thread1");

    });
    Thread thread2 = new Thread(()->{
      System.out.println("thread2");

    });
    Thread thread3 = new Thread(()->{
      System.out.println("thread3");

    });
    thread1.start();
    Thread.sleep(1000);
//    thread1.join();//阻塞主线程
    thread2.start();
    Thread.sleep(1000);
//    thread2.join();
    thread3.start();
//    thread3.join();


  }

}
