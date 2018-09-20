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

public class Synchronized01 {
  public static void main(String[] args) {
// 对Synchronized Class对象进行加锁
    synchronized (Synchronized01.class) {
    }
// 静态同步方法，对Synchronized Class对象进行加锁
    m();
  }
  public static synchronized void m() {
  }
}
