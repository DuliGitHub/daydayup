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



import com.jiagouedu.annotation.ThreadSafe;
import com.jiagouedu.util.TlUtil;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/***
 * 枚举
 * 是否线程安全 1 不是0
 * threadsafe
 */
@ThreadSafe
public class Singleton08 {


  private Singleton08(){
  }
  private enum SingletonEnum{
     SINGLETON_ENUM;
     private Singleton08 singleton08;
     //jvm保证这个方法绝对的调用一次
    SingletonEnum(){
      singleton08=new Singleton08();
    }
    public Singleton08 getSingleton08(){
      return  singleton08;
    }
  }


  public static Singleton08 getSingleton02(){

    return  SingletonEnum.SINGLETON_ENUM.getSingleton08();
  }

  public static void main(String[] args) {
      final Set set=new HashSet();
        TlUtil.timeTasks(1000, 100, new Runnable() {
          @Override
          public void run() {
            set.add(Singleton08.getSingleton02().hashCode());
          }
        });
    System.out.println(set.size());
    Iterator iterator = set.iterator();
    while(iterator.hasNext()){
      System.out.println(iterator.next());
    }
  }


}
