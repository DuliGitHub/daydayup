package com.jiagouedu.countdownlatch;/*
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
 */

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/***
 * 等待异步处理结果之后才进行我的操作
 */
public class CountDownLatch02 {
  public static void main(String[] args) throws ExecutionException, InterruptedException {
    CountDownLatch countDownLatch=new CountDownLatch(1);
    CallableImple callableImple=new CallableImple(countDownLatch);
    FutureTask<String> futureTask = new FutureTask<String>(callableImple);
    new Thread(futureTask).start();
    if (!futureTask.isDone()){
      try {
        System.out.println("你知道我一直在等你嘛>>>>>>>>");
        countDownLatch.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println(futureTask.get());




  }
  
}
