package com.jiagouedu.concurrent2;/* ━━━━━━如来保佑━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　┻　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━永无BUG━━━━━━
 * 图灵学院-悟空老师
 * 以往视频加小乔老师QQ：895900009
 * 悟空老师QQ：245553999
 */

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/***
 * 只是为了大家更好的了解线程池的原理，此代码还有很多不足。
 */
public class ThreadPoolExecutor  extends  AbstarctExecutorService {
   private volatile  int corePoolSize;
   private volatile  int maximumPoolSize;
   private volatile long keepAliveTime;
   private volatile boolean allowCoreThreadTimeOut;//描述是否需要超时
   private final AtomicInteger ctl=new AtomicInteger(0);
   private BlockingQueue<Runnable> workQueue;


   public ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, BlockingQueue<Runnable> workQueue) {
      this.corePoolSize = corePoolSize;
      this.maximumPoolSize = maximumPoolSize;
      this.workQueue = workQueue;
   }

   public ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, boolean allowCoreThreadTimeOut, BlockingQueue<Runnable> workQueue) {
      this.corePoolSize = corePoolSize;
      this.maximumPoolSize = maximumPoolSize;
      this.keepAliveTime = keepAliveTime;
      if(keepAliveTime>0){
         allowCoreThreadTimeOut=true;
      }
      this.allowCoreThreadTimeOut = allowCoreThreadTimeOut;
      this.workQueue = workQueue;
   }
   public int getCorePoolSize(){
      return  ctl.get();
   }

   /***
    * 暴露的接口  然后接受task任务的
    * @param command
    * TODO 怎么用我们的核心数量这个事情？
    */
   @Override
   public void execute(Runnable command) {
      if (command==null){
         throw new NullPointerException();
      }
      int c=ctl.get();
      if(c< corePoolSize){
         addWorker(command,true);
      }else if (c<maximumPoolSize){
         addWorker(null,false);
      }
      else if (workQueue.offer(command)){
         addWorker(null,false);
      }else
      {
         reject(command);
      }


   }

   /***
    * ZUOYE
    */
   @Override
   public void shutDown() {

   }

   class RejectedExecutionHandler{

      public void rejectdExection(Runnable command){
         throw new RejectedExecutionException("这个task"+command+"我处理不过来了");
      }

   }

   private void reject(Runnable command) {
      RejectedExecutionHandler rejectedExecutionHandler=new RejectedExecutionHandler();
      rejectedExecutionHandler.rejectdExection(command);

   }

   private void addWorker(Runnable task,Boolean core) {
      if (core){
         ctl.incrementAndGet();
      }
    Worker worker= new Worker(task);
    worker.thread.start();

   }

   /***
    * 干活的人
    */
   class Worker extends ReentrantLock implements  Runnable{
      private Runnable firstTask;
      private Thread thread;

      public Worker(Runnable firstTask) {
         this.firstTask = firstTask;
         thread=new Thread(this);
      }

      @Override
      public void run() {
         runWorker(this);

      }


      private void runWorker(Worker w) {
         try {
            w.lock();
            Runnable task = w.firstTask;
            if(task!=null||(task=getTask())!=null){
               task.run();
            }

         } finally {
            processWorkerExit(w);
            w.unlock();
         }


      }

      /***
       * todo
       * @param w
       */
      private void processWorkerExit(Worker w) {
         addWorker(null,false);
      }

      public Runnable getTask() {
         try {
            if (workQueue.isEmpty()){
               return  null;
            }
            Runnable r=allowCoreThreadTimeOut? workQueue.poll(keepAliveTime, TimeUnit.NANOSECONDS):workQueue.take();
            if (r!=null){
               return  r;
            }
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
         return  null;

      }
   }
}
