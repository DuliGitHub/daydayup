package com.jiagouedu.countdownlatch;/*
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

import java.util.concurrent.CountDownLatch;

public class CountDownLatchJoin {
  static CountDownLatch c = new CountDownLatch(1);
  public static void main(String[] args) throws InterruptedException {
    new Thread(()->{

        try {
          System.out.println("我在干活");
          Thread.sleep(2000);
          c.countDown();
        } catch (InterruptedException e) {
          e.printStackTrace();
      }

    }).start();
    c.await();
    System.out.println("我等你干完");
  }
}