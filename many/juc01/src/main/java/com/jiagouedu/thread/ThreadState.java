package com.jiagouedu.thread;


import java.util.concurrent.TimeUnit;

/**
 * 图灵学院-致敬大师，致敬未来的你
 * 悟空QQ：245553999
 * 线程状态
 * 通过jps>jstack
 */
public class ThreadState {
  public static void main(String[] args) {
    new Thread(new TimeWaiting (), "TimeWaitingThread").start();
    new Thread(new Waiting(), "WaitingThread").start();
// 使用两个Blocked线程，一个获取锁成功，另一个被阻塞
    new Thread(new Blocked(), "BlockedThread-1").start();
    new Thread(new Blocked(), "BlockedThread-2").start();
  }
  // 该线程在Waiting.class实例上等待
  static class Waiting implements Runnable {
    @Override
    public void run() {
      while (true) {
        synchronized (Waiting.class) {
          try {
            Waiting.class.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }
    // 该线程不断地进行睡眠
  static class TimeWaiting implements Runnable {
    @Override
    public void run() {
      while (true) {
          second(100);
      }
    }

  }
  // 该线程在Blocked.class实例上加锁后，不会释放该锁
  static class Blocked implements Runnable {
    public void run() {
      synchronized (Blocked.class) {
        while (true) {
          second(100);
        }
      }
    }
  }


  public static final void second(long seconds) {
    try {
      TimeUnit.SECONDS.sleep(seconds);
    } catch (InterruptedException e) {
    }
  }
  }
