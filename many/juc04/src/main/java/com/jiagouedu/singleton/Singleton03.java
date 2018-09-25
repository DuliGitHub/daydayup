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
 * 懒汉模式
 * 影响程序启动的时间
 * threadsafe
 */

import com.jiagouedu.annotation.ThreadNoSafe;
import com.jiagouedu.annotation.ThreadSafe;
import com.jiagouedu.util.TlUtil;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ThreadSafe
public class Singleton03 {

  private static Singleton03 singleton01=null;

  private Singleton03() {
  }
  static {
    singleton01=new Singleton03();
  }

  public static Singleton03 getSingleton01(){

    return singleton01;

  }


  public static void main(String[] args) {
     Set set=new CopyOnWriteArraySet();
    System.out.println(Singleton03.getSingleton01());;
/*    TlUtil.timeTasks(100, 1, new Runnable() {
      @Override
      public void run() {

        set.add(Singleton03.getSingleton01().hashCode());
      }
    });*/
    System.out.println(set.size());
  }
}
