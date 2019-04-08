package com.jiagouedu.countdownlatch;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class CountDownLatch01 {

    private final static int threadCount = 100;

    public static void main(String[] args) throws Exception {
        StringBuffer stringBuffer;
        ExecutorService exec = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < 99; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    test(threadNum);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
    countDownLatch.await();
//        countDownLatch.await(120, TimeUnit.MILLISECONDS);
        System.out.println("结束");
        exec.shutdown();
    }

    private static void test(int threadNum) throws Exception {
        Thread.sleep(50);
        System.out.println(threadNum);
        Thread.sleep(50);
    }
}
