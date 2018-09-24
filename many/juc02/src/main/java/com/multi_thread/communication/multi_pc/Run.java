package com.multi_thread.communication.multi_pc;

import java.sql.SQLOutput;

/**
 * notify() 换成notifyAll()  解决假死，不光通知同类线程，也包括异类
 */
public class Run {
    public static void main(String[] args) throws InterruptedException{
        String lock = new String("");
        P p = new P(lock);
        C c = new C(lock);
        ThreadP[] pThread = new ThreadP[2];
        ThreadC[] cThread = new ThreadC[2];
        for(int i = 0;i < 2; i++){
            pThread[i] = new ThreadP(p);
            pThread[i].setName("生产者 " + (i + 1));
            cThread[i] = new ThreadC(c);
            cThread[i].setName("消费者 " + (i + 1));
            pThread[i].start();
            cThread[i].start();
        }

        Thread.sleep(2000);
        Thread[] threadArray = new Thread[Thread.currentThread().getThreadGroup().activeCount()];
        Thread.currentThread().getThreadGroup().enumerate(threadArray);
        for(int i = 0 ;i < threadArray.length;i++){
            System.out.println(threadArray[i].getName() + " " + threadArray[i].getState());
        }
    }
}
