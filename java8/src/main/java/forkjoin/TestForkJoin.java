package forkjoin;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @Author: Damon
 * @Date: 2020/5/24 17:27
 */
public class TestForkJoin {

    /**
     * Fork 框架
     */
    @Test
    public void test1(){
        Instant start = Instant.now();

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCalc(0,10000000000L);
        Long sum = pool.invoke(task);
        System.out.println(sum);

        Instant end = Instant.now();

        System.out.println("耗费时间为:" + Duration.between(start,end).toMillis());
    }

    /**
     * 普通for
     */
    @Test
    public void test2(){
        Instant start = Instant.now();
        long sum = 0L;
        for(long i = 0;i < 10000000000L;i++){
            sum += i;
        }
        Instant end = Instant.now();
        System.out.println(sum);
        System.out.println("耗费时间为:" + Duration.between(start,end).toMillis());
    }

    /**
     * java8 并发流
     */
    @Test
    public void test3(){
//        long reduce = LongStream.range(0, 10000000000L)
//                .reduce(0, Long::sum);
        Instant start = Instant.now();
        long sum = LongStream.range(0, 100000000000L)
                .parallel()//并行流，底层forkJoin
                .reduce(0, Long::sum);
        Instant end = Instant.now();
        System.out.println(sum);
        System.out.println("耗费时间为:" + Duration.between(start,end).toMillis());
    }
}
