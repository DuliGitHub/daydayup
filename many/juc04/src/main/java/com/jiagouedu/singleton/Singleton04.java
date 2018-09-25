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
public class Singleton04 {

  private static Singleton04 singleton01=null;

  private Singleton04() {
  }

  /***
   * 同步锁、效率低 方法级别的
   * @return
   */
  public static synchronized  Singleton04 getSingleton01(){
    if (null==singleton01){
      singleton01=new Singleton04();
    }
    return singleton01;

  }
  final static Set set=new CopyOnWriteArraySet();
  public static void main(String[] args) {

    TlUtil.timeTasks(100, 1, new Runnable() {
      @Override
      public void run() {

        set.add(Singleton04.getSingleton01().hashCode());
      }
    });
    System.out.println(set.size());
  }
}
