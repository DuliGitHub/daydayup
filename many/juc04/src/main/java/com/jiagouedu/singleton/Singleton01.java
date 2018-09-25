package com.jiagouedu.singleton;/*
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
 *
 * 饿汉模式
 * 影响程序启动的时间
 * threadsafe
 */

import com.jiagouedu.annotation.ThreadNoSafe;
import com.jiagouedu.annotation.ThreadSafe;
import com.jiagouedu.util.TlUtil;


@ThreadSafe
public class Singleton01 {

  private static  Singleton01 singleton01=new Singleton01();

  private Singleton01() {
  }

  public static  Singleton01 getSingleton01(){
    return singleton01;
  }

  public static void main(String[] args) {

    TlUtil.timeTasks(100, 1, new Runnable() {
      @Override
      public void run() {

        System.out.println(Singleton01.getSingleton01().hashCode());
      }
    });
  }
}
