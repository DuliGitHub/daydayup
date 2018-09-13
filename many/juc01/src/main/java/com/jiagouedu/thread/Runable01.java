package com.jiagouedu.thread;/*
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

public class Runable01 implements   Runnable{

  public static void main(String[] args) {
    Runable01 thread01 = new Runable01();
   new Thread(new Runable01()).start();
  }


  @Override
  public void run() {
    System.out.println("活好人帅");
  }
}
