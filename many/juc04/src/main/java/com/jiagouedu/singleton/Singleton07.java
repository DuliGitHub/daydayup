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
 * 图灵学院-悟空老师
 * www.jiagouedu.com
 * 悟空老师QQ：245553999
 */



import com.jiagouedu.util.TlUtil;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/***
 * 静态内部类
 * 是否线程安全 1 不是0
 * threadsafe
 */
public class Singleton07 {


  private Singleton07(){
  }
  private static class  SingletonInner{
     private static  final Singleton07 singleton02=new Singleton07();
  }


  public static   Singleton07 getSingleton02(){

    return  SingletonInner.singleton02;
  }

  public static void main(String[] args) {
      final Set set=new CopyOnWriteArraySet();
        TlUtil.timeTasks(1000, 100, new Runnable() {
          @Override
          public void run() {
            set.add(Singleton07.getSingleton02().hashCode());
          }
        });
    System.out.println(set.size());

  }


}
