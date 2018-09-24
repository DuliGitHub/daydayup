package com.multi_thread.communication.multi_pc;

//消费者
public class C {
    private String lock;
    public C(String lock){
        this.lock = lock;
    }
    public void getValue(){
        try{
            synchronized (lock){
                while (ValueObject.value.equals("")){
                    System.out.println("消费者 " + Thread.currentThread().getName() + " WAITING 了 ^^");
                    lock.wait();
                }
                System.out.println(" 消费者 " + Thread.currentThread().getName() + " RUNNABLE 了");
                ValueObject.value = "";
//                lock.notify();
                lock.notifyAll();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
