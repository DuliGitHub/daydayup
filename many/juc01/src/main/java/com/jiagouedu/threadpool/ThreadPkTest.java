package com.jiagouedu.threadpool;
/**
 *  线程测试
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ThreadPkTest {
    public static void main(String[] args) throws InterruptedException {
        Long start= System.currentTimeMillis();
        final List<Integer> l=new ArrayList<Integer>();
        final Random random=new Random();
        for(int i=0;i<10000;i++){
            Thread thread=new Thread(){
                public void run(){
                    l.add(random.nextInt());
                }
            };
            thread.start();
            thread.join();
        }
        System.out.println(System.currentTimeMillis()-start);
        System.out.println(l.size());

    }
}
