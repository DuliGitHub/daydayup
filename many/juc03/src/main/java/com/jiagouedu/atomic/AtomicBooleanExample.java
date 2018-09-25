package com.jiagouedu.atomic;/*
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

import java.util.concurrent.atomic.AtomicBoolean;

/***
 * 程序启动
 */
public class AtomicBooleanExample {

  //volatile boolean stop =false;
   AtomicBoolean atomicBoolean=new AtomicBoolean(false);

  public void shutDown() {
    //stop = true;
    atomicBoolean.compareAndSet(false,true);
  }

  public void doWork() {
    while (!atomicBoolean.get()) {
    }
    System.out.println("你能读到我吗...");
  }


  public static void main(String[] args) throws InterruptedException {
    AtomicBooleanExample volatile01=new AtomicBooleanExample();
    new Thread(()->{
      volatile01.doWork();//先就开始工作，stop为false
    }).start();
    Thread.sleep(1000);
    new Thread(()->{
      volatile01.shutDown(); //调用shtdown方法stop为true 但是线程工作副本的问题
    }).start();

  }
}
