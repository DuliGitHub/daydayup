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