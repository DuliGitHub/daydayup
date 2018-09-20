package com.jiagouedu.volatiles;/*
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

public class Volatile02 implements Runnable{

    static volatile int	i	= 1;
    @Override
    public void run()
    {
      /***
       * i++; 操作并非为原子性操作。
       什么是原子性操作？简单来说就是一个操作不能再分解。i++ 操作实际上分为 3 步：
       读取 i 变量的值。
       增加 i 变量的值。
       把新的值写到内存中。
       */
      System.out.println(Thread.currentThread().getName() + ": " + i + ", "
             + (++i));
    }
    public static void main(String[] args)
    {
      Thread t1 = new Thread(new Volatile02(), "A");
      Thread t2 = new Thread(new Volatile02(), "B");
      Thread t3 = new Thread(new Volatile02(), "C");
      Thread t4 = new Thread(new Volatile02(), "D");
      t1.start();
      t2.start();
      t3.start();
      t4.start();
    }
  }

