package com.jiagouedu.threadpool;
/**
 * 线程测试
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ThreadPkTest {
    public static void main(String[] args) throws InterruptedException {

        int COUNT_BITS = Integer.SIZE - 3;
        int RUNNING    = -1 << COUNT_BITS;
        int SHUTDOWN   =  0 << COUNT_BITS;
       int  STOP       =  1 << COUNT_BITS;
        int TIDYING    =  2 << COUNT_BITS;
        int TERMINATED =  3 << COUNT_BITS;
        System.out.println(COUNT_BITS);
        System.out.println(Integer.toBinaryString(((1 << COUNT_BITS) - 1)&RUNNING));
        System.out.println(Integer.toBinaryString((1 << COUNT_BITS) - 1));


        System.out.println("RUNNING "+Integer.toBinaryString(RUNNING));
        System.out.println("-1: " + Integer.toBinaryString(-1));
        System.out.println("SHUTDOWN "+Integer.toBinaryString(SHUTDOWN));
        System.out.println("STOP "+Integer.toBinaryString(STOP));
        System.out.println("TIDYING "+Integer.toBinaryString(TIDYING));
        System.out.println("| "+Integer.toBinaryString(TIDYING|0));
        System.out.println("TERMINATED "+Integer.toBinaryString(TERMINATED));
        System.out.println("TERMINATED | 0 "+Integer.toBinaryString(TERMINATED | 0));



        Long start = System.currentTimeMillis();
        final List<Integer> l = new ArrayList<Integer>();
        final Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            Thread thread = new Thread() {
                public void run() {
                    l.add(random.nextInt());
                }
            };
            thread.start();
            thread.join();
        }
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(l.size());

    }
}
