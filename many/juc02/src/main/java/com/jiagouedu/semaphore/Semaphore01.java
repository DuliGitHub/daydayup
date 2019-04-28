package com.jiagouedu.semaphore;

import java.util.concurrent.Semaphore;

public class Semaphore01 {
    private static Semaphore semaphore = new Semaphore(5);// 5 代表只能支持5个人

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {//20个人
            final int j = i;
            new Thread(() -> {
                try {
                    action(j);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

        }
    }


    public static void action(int i) throws InterruptedException {
        semaphore.acquire();
        System.out.println(i + "在京东秒杀iphonex");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(i + "秒杀成功");
        semaphore.release();


    }
}
