package com.jiagouedu.lock;/*
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
 */

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock01 {
  private static final ReentrantLock reentrantLock=new ReentrantLock();
   static int i=0;

  public static void main(String[] args) throws InterruptedException {
    ReentrantLock01 reentrantLock01 = new ReentrantLock01();
    new Thread(()->{
      reentrantLock01.add();
    }).start();
    Thread.sleep(1000);//主要目的是让两个线程把事情干完
    new Thread(()->{
      reentrantLock01.add();
    }).start();
    Thread.sleep(1000);//主要目的是让两个线程把事情干完
    System.out.println(i);

  }

  /***
   * 强调try fianlly规范
   */
  public  void add(){
    try {
      reentrantLock.lock();
      for (int j = 0; j < 10000; j++) {
        i++;
      }
    } finally {
      reentrantLock.unlock();
    }
  }





}
