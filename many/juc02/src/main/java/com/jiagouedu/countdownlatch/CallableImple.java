package com.jiagouedu.countdownlatch;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class CallableImple implements Callable {
    private CountDownLatch latch;

    public CallableImple(CountDownLatch latch) {
        this.latch = latch;
    }

    public String doSomeThing() {
        latch.countDown();
        return "一分钟就干完了";
    }


    @Override
    public Object call() throws Exception {
        Thread.sleep(1000);
        return doSomeThing();
    }
}
