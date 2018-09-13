package com.jiagouedu.thread;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Callable01  implements Callable<String> {

  @Override
  public String call() throws Exception {
    return "活好人帅";
  }

  public static void main(String[] args) throws ExecutionException, InterruptedException {
   FutureTask task= new FutureTask(new Callable01());
   new Thread(task).start();
    System.out.println(task.get());


  }



}
