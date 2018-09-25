package com.jiagouedu.atomic.ABA;/*
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

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampesABAExample {
  private static AtomicStampedReference atomicStampedRef = new AtomicStampedReference(100,0);

  public static void main(String[] args) throws InterruptedException {
    Thread refT1 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }
        atomicStampedRef.compareAndSet(100, 101, atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
        atomicStampedRef.compareAndSet(101, 100, atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
      }
    });

    Thread refT2 = new Thread(new Runnable() {
      @Override
      public void run() {
        int stamp = atomicStampedRef.getStamp();
        try {
          TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
        }
        boolean c3 = atomicStampedRef.compareAndSet(100, 101, stamp, stamp + 1);
        System.out.println(c3); // false
      }
    });
    refT1.start();
    refT2.start();

  }
}
