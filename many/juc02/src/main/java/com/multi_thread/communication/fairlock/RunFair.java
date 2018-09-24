package com.multi_thread.communication.fairlock;

public class RunFair {
    public static void main(String[] args) {
        final Service service = new Service(true);
        //         new Thread(()-> System.out.println("  ★线程 " + Thread.currentThread().getName() + " 运行了")).start();

        Runnable runnable = () -> {
            System.out.println("  ★线程 " + Thread.currentThread().getName() + " 运行了");
            service.serviceMethod();
        };

        Thread[] threads = new Thread[10];
        for (int i = 0;i < 10;i++){
            threads[i] = new Thread(runnable);
        }
        for (int i = 0;i < 10;i++){
            threads[i].start();
        }


    }
}
