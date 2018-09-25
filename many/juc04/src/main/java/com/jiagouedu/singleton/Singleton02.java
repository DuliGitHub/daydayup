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

@ThreadNoSafe
public class Singleton02 {

  private static Singleton02 singleton01=null;

  private Singleton02() {
  }

  public static Singleton02 getSingleton01(){
    if (null==singleton01){
      singleton01=new Singleton02();
    }
    return singleton01;

  }

  public static void main(String[] args) {
    final Set set;
    set = new CopyOnWriteArraySet();
    TlUtil.timeTasks(100, 1, new Runnable() {
      @Override
      public void run() {

        set.add(Singleton02.getSingleton01().hashCode());
      }
    });
    System.out.println(set.size());
  }
}
