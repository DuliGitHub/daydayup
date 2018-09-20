package com.jiagouedu.sync;/*
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

import com.jiagouedu.annotation.ThreadSafe;

@ThreadSafe
public class Sync03 implements Runnable {
  static int i = 0;

  @Override
  public   void run() {
    add();
  }

  /***
   * 关键字
   */
  private   void add() {
    synchronized (this){
    for (int j = 0; j < 10000; j++) {
      i++;
    }
  }
  }

  public static void main(String[] args)throws  Exception {
    Sync03 sync01 = new Sync03();
    Thread thread = new Thread(sync01);
    Thread thread2 = new Thread(sync01);
    thread.start();
    thread2.start();
    thread.join();thread2.join();
    System.out.println(i);


  }


}
