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

/***
 * 非线程安全的
 */
@ThreadNoSafe
public class Sync01 implements Runnable {
  static int i = 0;

  @Override
  public  synchronized void run() {
    //重构
    for (int j = 0; j < 10000; j++) {
      i++;
    }
  }

  public static void main(String[] args)throws  Exception {
    Sync01 sync01 = new Sync01();
  /*  IntStream.range(0,2).forEach(i->new Thread(sync01){
    }.start());*/


    Thread thread = new Thread(sync01);
    Thread thread2 = new Thread(sync01);
    thread.start();
    thread2.start();
    thread.join();thread2.join();
    System.out.println(i);


  }


}
