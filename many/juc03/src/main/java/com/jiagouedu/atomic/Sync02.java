package com.jiagouedu.atomic;/*
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　┻　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　永无BUG 　┣┓
 * 　　　　┃　　如来保佑　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┗┻┛　┗┻┛
 * 图灵学院-悟空老师
 * www.jiagouedu.com
 * 悟空老师QQ：245553999
 */


import java.util.concurrent.atomic.AtomicInteger;

public class Sync02 implements Runnable {
  static AtomicInteger i = new AtomicInteger();

  @Override
  public   void run() {
    add();
  }

  /***
   * 方法级别的
   */
  private   void add() {
    for (int j = 0; j < 10000; j++) {
        i.incrementAndGet();
    }
  }

  public static void main(String[] args)throws  Exception {
    Sync02 sync01 = new Sync02();
    Thread thread = new Thread(sync01);
    Thread thread2 = new Thread(sync01);
    thread.start();
    thread2.start();
    thread.join();thread2.join();
    System.out.println(i.get());


  }


}
