package com.jiagouedu.concurrent2;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public interface ExecutorServer  extends  Executor{


    void shutDown();
    <T>Future<T> submit(Runnable runnable);
    <T>Future<T> submit(Callable callable);
}
