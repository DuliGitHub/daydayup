package com.multi_thread.communication.read_write_lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读读共享、写写互斥、读写互斥
 */
public class Service {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public void read(){
        try {
            try {
                lock.readLock().lock();
                System.out.println("获得读锁 " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
                Thread.sleep(1000);
            }finally {
                lock.readLock().unlock();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void write(){
        try {
            try {
                lock.writeLock().lock();
                System.out.println("获得写锁 " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
                Thread.sleep(1000);
            }finally {
                lock.writeLock().unlock();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
