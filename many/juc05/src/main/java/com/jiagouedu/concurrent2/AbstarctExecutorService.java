package com.jiagouedu.concurrent2;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public abstract class AbstarctExecutorService implements ExecutorServer {
    @Override
    public <T> Future<T> submit(Runnable runnable) {
        FutureTask futureTask = new FutureTask<T>(runnable, null);
        execute(futureTask);
        return futureTask;
    }

    @Override
    public <T> Future<T> submit(Callable callable) {
        FutureTask futureTask = new FutureTask<T>(callable);
        execute(futureTask);
        return futureTask;
    }
}
