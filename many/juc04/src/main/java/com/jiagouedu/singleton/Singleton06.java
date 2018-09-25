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

import com.jiagouedu.annotation.ThreadSafe;
import com.jiagouedu.util.TlUtil;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ThreadSafe
public class Singleton06 {

  private static volatile  Singleton06 singleton01=null;

  private Singleton06() {
  }

  /***
   *
   * @return
   */
  public static Singleton06 getSingleton01(){
    if (null==singleton01){
        synchronized (Singleton06.class) {
          if (null==singleton01){
            singleton01 = new Singleton06();
          }
        }
    }
    return singleton01;

  }

  public static void main(String[] args) {
     final Set set=new CopyOnWriteArraySet();
    TlUtil.timeTasks(100, 1, new Runnable() {
      @Override
      public void run() {

        set.add(Singleton06.getSingleton01().hashCode());
      }
    });
    System.out.println(set.size());
  }
}
