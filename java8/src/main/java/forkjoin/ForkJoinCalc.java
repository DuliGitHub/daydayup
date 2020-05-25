package forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @Author: Damon
 * @Date: 2020/5/24 17:17
 */
public class ForkJoinCalc extends RecursiveTask<Long> {

    private long start;

    private long end;

    private static final long THRESHOLD = 1000000;

    public ForkJoinCalc(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;

        if(length <= THRESHOLD){
            long sum = 0;

            for(long i = start;i <= end;i++){
                sum += i;
            }
            return sum;
        }else {
//            long middle = (start + end) / 2;
//            long middle = (end - start)/2+start;
            long middle = ((end - start)>>1)+start;
            ForkJoinCalc left = new ForkJoinCalc(start,middle);
            left.fork();//拆分子任务，同时压入线程队列
            ForkJoinCalc right = new ForkJoinCalc(middle + 1,end);
            right.fork();
            return left.join() + right.join();
        }
    }
}
