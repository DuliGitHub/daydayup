package com.jiagouedu.thread;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Callable01  implements Callable<String> {

  @Override
  public String call() throws Exception {
      boolean a = true;
//      while (a){
//          System.out.println("第一个call");
//          Thread.sleep(1000);
//
//      }
      System.out.println("第一个call");
    return "第一个call";
  }

  public static void main(String[] args) throws ExecutionException, InterruptedException {
   FutureTask task= new FutureTask(new Callable01());
      FutureTask task2= new FutureTask(new Callable02());

   new Thread(task).start();
      new Thread(task2).start();
      Thread.sleep(1000);
      System.out.println(task2.get());
      System.out.println(task.get());


  }



}
