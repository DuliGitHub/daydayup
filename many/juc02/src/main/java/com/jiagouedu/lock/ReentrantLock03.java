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

public class ReentrantLock03 {
  private static final ReentrantLock reentrantLock=new ReentrantLock();
   static int i=0;

  public static void main(String[] args) throws InterruptedException {
    ReentrantLock03 reentrantLock01 = new ReentrantLock03();
    new Thread(()->{
      reentrantLock01.add();
    }).start();
    Thread.sleep(1000);//主要目的是让两个线程把事情干完
    Thread thread = new Thread(() -> {
      reentrantLock01.add();
    });
    thread.start();
    Thread.sleep(1000);//主要目的是让两个线程把事情干完
    thread.interrupt();//增加这段代码================
    System.out.println(i);



  }

  /***
   * 强调try fianlly规范
   */
  public  void add(){
        if( reentrantLock.tryLock()){
          try {
            for (;;) {
              i++;
            }
          } finally {
            reentrantLock.unlock();
          }
          }
          else{
          System.out.println("没有获取到锁"+Thread.currentThread().getName());
        }
        }






}
