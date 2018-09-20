package com.jiagouedu.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/***
 * 设置时间
 */
public class ReentrantLock04 implements Runnable {
	public static ReentrantLock reentrantLock = new ReentrantLock();

	@Override
	public void run() {
		try {
			if (reentrantLock.tryLock(5, TimeUnit.SECONDS)) {
				Thread.sleep(6000); //调这个时间===========
				System.out.println("获取");
			} else {
				System.out.println("获取失败");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if(reentrantLock.isHeldByCurrentThread()) {// 不加这个条件会报错 getHoldCount()方法来检查当前线程是否拥有该锁
				reentrantLock.unlock();
			}
		}
	}

	public static void main(String[] args) {
		ReentrantLock04 reentrantLock03 = new ReentrantLock04();
		IntStream.range(0,2).forEach(i->new Thread(reentrantLock03){
		}.start());

	}
}
