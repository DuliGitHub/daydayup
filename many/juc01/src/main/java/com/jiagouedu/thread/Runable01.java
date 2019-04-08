package com.jiagouedu.thread;

public class Runable01 implements Runnable {

    public static void main(String[] args) {
        new Thread(new Runable01()).start();
    }


    @Override
    public void run() {
        System.out.println("活好人帅");
    }
}
