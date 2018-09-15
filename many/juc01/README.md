##### 多线程
- 排查死锁
   - cmd->jps(查看当前java进程的服务)->jstack+id(查看相关线程运行状态-排查死锁)
   - idea照相机
   
   ```
   //   s = awaitDone(false, 0L)
    public V get() throws InterruptedException, ExecutionException
          {
        int s = state;
        if (s <= COMPLETING)
            s = awaitDone(false, 0L); //Callable的get方法，会阻塞
        return report(s);
    }

     private int awaitDone(boolean timed, long nanos)
            throws InterruptedException {
            final long deadline = timed ? System.nanoTime() + nanos : 0L;
            WaitNode q = null;
            boolean queued = false;
            for (;;) {
                if (Thread.interrupted()) {
                    removeWaiter(q);
                    throw new InterruptedException();
                }
    
                int s = state;
                if (s > COMPLETING) {
                    if (q != null)
                        q.thread = null;
                    return s;
                }
                else if (s == COMPLETING) // cannot time out yet
                    Thread.yield();
                else if (q == null)
                    q = new WaitNode();
                else if (!queued)
                    queued = UNSAFE.compareAndSwapObject(this, waitersOffset,
                                                         q.next = waiters, q);
                else if (timed) {
                    nanos = deadline - System.nanoTime();
                    if (nanos <= 0L) {
                        removeWaiter(q);
                        return state;
                    }
                    LockSupport.parkNanos(this, nanos);  //----->阻塞
                }
                else
                    LockSupport.park(this);
            }
        }
```

---

实现 Runnable接口相比继承 接口相比继承 接口相比继承 Thread类有如下优势 类有如下优势 类有如下优势
1）可以避免由于 Java的单继承特性而带来局限 的单继承特性而带来局限 2）增强程序的 健壮性，代码能够被多个线共享与数据是独立3）
线程池只能放入实现 Runable或 Callable类线程，不能直接放入继承 类线程，不能直接放入继承 Thread的类
实现 Runnable接口和实现 接口和实现 接口和实现 Callable接口的区别 接口的区别 接口的区别
1）Runnable是自从 java1.1就有了，而 就有了，而 Callable是 1.5之后才加上去的 2）实现 Callable接口的任务线程能返回执行结果，
而实现 接口的任务线程能返回执行结果，而实现 Runnable接口的任务线程不能返回结果 接口的任务线程不能返回结果 3）
Callable接口的 call()方法允许抛出异 方法允许抛出异 常，而 Runnable接口的 接口的 run()方法的异常只能在内部消化，
不继 方法的异常只能在内部消化，不继 续上抛 4）加入线程池运行， Runnable使用 ExecutorService的 execute方法，
 Callable使用 submit方法 注： Callable接口支持返回执行结果，此时需要调用 接口支持返回执行结果，
 此时需要调用 接口支持返回执行结果，此时需要调用 FutureTask.get()方法实现，此会阻塞主线程直到获 方法实现，此会阻塞主线程直到获 取
 
 //
