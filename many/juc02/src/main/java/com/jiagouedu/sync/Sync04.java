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

import com.jiagouedu.annotation.ThreadNoSafe;

@ThreadNoSafe
public class Sync04 implements Runnable {
  static int i = 0;

  @Override
  public   void run() {
    add();
  }

  /***
   * 非静态方法级别的 要改成static
   */
  private synchronized  void add() {
    for (int j = 0; j < 10000; j++) {
      i++;
    }
  }

  public static void main(String[] args)throws  Exception {
    Sync04 sync01 = new Sync04();
    Sync04 sync02 = new Sync04();
    Thread thread = new Thread(sync01);
    Thread thread2 = new Thread(sync02);
    thread.start();
    thread2.start();
    thread.join();thread2.join();
    System.out.println(i);


  }


}
