package com.jiagouedu.sync;

public class Synchronized01 {
    public static void main(String[] args) {
// 对Synchronized Class对象进行加锁
        synchronized (Synchronized01.class) {
        }
// 静态同步方法，对Synchronized Class对象进行加锁
        m();
    }

    public static synchronized void m() {
    }
}
