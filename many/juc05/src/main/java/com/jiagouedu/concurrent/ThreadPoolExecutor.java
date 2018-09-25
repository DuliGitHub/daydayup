package com.jiagouedu.concurrent;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPoolExecutor  implements  Executor{
   private volatile  int corePoolSize;
   private volatile  int maximumPoolSize;
   private BlockingQueue<Runnable> workQueue;


   public ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, BlockingQueue<Runnable> workQueue) {
      this.corePoolSize = corePoolSize;
      this.maximumPoolSize = maximumPoolSize;
      this.workQueue = workQueue;
   }

   /***
    * 暴露的接口  然后接受task任务的
    * @param task
    * TODO 怎么用我们的核心数量这个事情？
    */
   @Override
   public void execute(Runnable task) {
      addWorker(task);
      workQueue.offer(task);
   }

   private void addWorker(Runnable task) {
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
            w.unlock();
         }


      }

      public Runnable getTask() {
         try {
            if (workQueue.isEmpty()){
               return  null;
            }
            System.out.println();
            return  workQueue.take();
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
         return  null;

      }
   }
}
