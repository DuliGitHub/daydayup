package com.jiagouedu.sync;

import com.jiagouedu.annotation.ThreadSafe;

@ThreadSafe
public class Sync05 implements Runnable {
  static int i = 0;

  @Override
  public   void run() {
    add();
  }

  /***
   * 关键字
   */
  private   void add() {
    //要改成Sync05.class
    synchronized (this){
    for (int j = 0; j < 10000; j++) {
      i++;
    }
  }
  }

  public static void main(String[] args)throws  Exception {
    Sync05 sync01 = new Sync05();
    Sync05 sync02 = new Sync05();
    Thread thread = new Thread(sync01);
    Thread thread2 = new Thread(sync02);
    thread.start();
    thread2.start();
    thread.join();thread2.join();
    System.out.println(i);


  }


}
