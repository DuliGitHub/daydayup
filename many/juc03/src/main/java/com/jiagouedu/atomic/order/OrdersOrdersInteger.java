package com.jiagouedu.atomic.order;/*
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/***
 * jvm 雪花算法
 */
public class OrdersOrdersInteger {
      static AtomicInteger count=new AtomicInteger(0);

  public String getOrdersNo() {
    SimpleDateFormat data = new SimpleDateFormat("YYYYMMDDHHMMSS");
    return data.format(new Date())+count.incrementAndGet();
  }

  public static void main(String[] args) {
    final CountDownLatch latch = new CountDownLatch(1);
    ExecutorService exeuctor = Executors.newFixedThreadPool(10);
    final OrdersOrdersInteger orderServer=new OrdersOrdersInteger();
    for (int i = 0; i < 10; i++) {
      exeuctor.submit(new Runnable() {
                          @Override
                          public void run() {
                            System.out.println(orderServer.getOrdersNo());;
                          }
      });

    }
    latch.countDown();
    exeuctor.shutdown();
  }

}
