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

- 实现 Runnable接口相比继承Thread类有如下优势 类有如下优势 类有如下优势
   - 1）可以避免由于 Java的单继承特性而带来局限 的单继承特性而带来局限 
   - 2）增强程序的 健壮性，代码能够被多个线共享与数据是独立3）线程池只能放入实现 Runable或 Callable类线程，
   不能直接放入继承 类线程，不能直接放入继承 Thread的类
- 实现 Runnable接口和实现Callable接口的区别 接口的区别 接口的区别
   - 1）Runnable是自从 java1.1就有了，而 就有了，而 Callable是 1.5之后才加上去的 
   - 2）实现 Callable接口的任务线程能返回执行结果，而实现 Runnable接口的任务线程不能返回结果 接口的任务线程不能返回结果 
   - 3）Callable接口的 **call()方法允许抛出异,而 Runnable接口的 run()方法的异常只能在内部消化**，
   -  4）**加入线程池运行， Runnable使用 ExecutorService的 execute方法**，
   Callable使用 submit方法 注： Callable接口支持返回执行结果，此时需要调用 接口支持返回执行结果,此时需要调用 FutureTask.get()方法实现，
   此会阻塞主线程直到获 方法实现，此会阻塞主线程直到获取返回结果，当不调用此方法时，主线程不会阻塞
- java 中的线程池
   - 普通线程池 ThreadPoolExecutor
   - 定时线程池ScheduledThreadPoolExecutor
   

-  maximumPoolSize(最大线程数) = corePoolSize(核心线程数) + noCorePoolSize(非核心线程数)；
 
  - （1）当currentSize<corePoolSize时，没什么好说的，直接启动一个核心线程并执行任务。
 
  - （2）当currentSize>=corePoolSize、并且workQueue未满时，添加进来的任务会被安排到workQueue中等待执行。
 
  - （3）当workQueue已满，但是currentSize<maximumPoolSize时，会立即开启一个非核心线程来执行任务。
 
  - （4）当currentSize>=corePoolSize、workQueue已满、并且currentSize>maximumPoolSize时，调用handler默认抛出RejectExecutionExpection异常。
- 几种线程池
  - （1）FixedThreadPool：Fixed中文解释为固定。结合在一起解释固定的线程池，说的更全面点就是，有固定数量线程的线程池。其corePoolSize=maximumPoolSize，且keepAliveTime为0，适合线程稳定的场所。
 
  - （2）SingleThreadPool： Single中文解释为单一。结合在一起解释单一的线程池，说的更全面点就是，有固定数量线程的线程池，且数量为一，从数学的角度来看SingleThreadPool应该属于FixedThreadPool的子集。其corePoolSize=maximumPoolSize=1,且keepAliveTime为0，适合线程同步操作的场所。
 
  - （3）CachedThreadPool： Cached中文解释为储存。结合在一起解释储存的线程池，说的更通俗易懂，既然要储存，其容量肯定是很大，所以他的corePoolSize=0，maximumPoolSize=Integer.MAX_VALUE(2^32-1一个很大的数字)
 
  - （4）ScheduledThreadPool： Scheduled中文解释为计划。结合在一起解释计划的线程池，顾名思义既然涉及到计划，必然会涉及到时间。所以ScheduledThreadPool是一个具有定时定期执行任务功能的线程池。
   
- Java并发编程三个概念
   - 原子性：不说了
   - 可见性：多个线程访问一个变量时，一个线程修改了这个变量的值，其他线程能够立即看到修改的值
   - 有序性：程序执行的顺序按照代码的先后顺序执行，程序顺序和我们的编译运行的执行一定是一样  
      -编译优化、
      -指令重排 ：Happens-before原则，传递原则：A>B>C  -->  A>C

