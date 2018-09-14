package com.jiagouedu.thread;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Callable02 implements Callable<String> {

  @Override
  public String call() throws Exception {
      System.out.println("第二个call");
    return "第二个call";
  }




}
