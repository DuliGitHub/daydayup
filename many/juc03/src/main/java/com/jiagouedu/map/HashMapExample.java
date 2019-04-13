package com.jiagouedu.map;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapExample {

    public static final HashMap<String, String> map = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        //线程一
        new Thread() {
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    map.put(String.valueOf(i), String.valueOf(i));
                }
            }
        }.start();
        //线程二
//        new Thread() {
//            public void run() {
//                for (int j = 1000; j < 2000; j++) {
//                    map.put(String.valueOf(j), String.valueOf(j));
//                }
//            }
//        }.start();

        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //输出
        for (int i = 0; i < 2000; i++) {
            System.out.println("第：" + i + "元素，值：" + map.get(String.valueOf(i)));

        }
        System.out.println(1^0);
        System.out.println(1^1);
        System.out.println(0^1);
    }


}
