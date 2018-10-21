package com.kawasaki.jvm;

public class GCTest {
    public static void main(String[] args) throws InterruptedException {
        byte[] allocation1, allocation2, allocation3, allocation4, allocation5, allocation6;
        allocation1 = new byte[30231 * 1024];
        allocation2 = new byte[900*1024];
        allocation3 = new byte[1000*1024];
        allocation4 = new byte[1000*1024];
        allocation5 = new byte[1000*1024];
        allocation6 = new byte[1000*1024];
        Thread.sleep(Integer.MAX_VALUE);
    }
}
