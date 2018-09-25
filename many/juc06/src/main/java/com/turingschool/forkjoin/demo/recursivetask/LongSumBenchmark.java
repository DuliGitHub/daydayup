package com.turingschool.forkjoin.demo.recursivetask;


import com.turingschool.forkjoin.demo.arraysum.SumMultiThreads;
import com.turingschool.forkjoin.demo.arraysum.SumSequential;
import com.turingschool.forkjoin.demo.utils.Utils;
import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Benchmark)
public class LongSumBenchmark {

    @Param({"200000000"})
    public int N;

    @Param({"4", "10", "100"})
    public int M;

    @Param({"1000"})
    public int NUM;

    public int[] arrayToSum;
    public int[] arrayToSumSingleThread;
    public ForkJoinPool pool;

    @Setup(Level.Iteration)
    public void init() {
        arrayToSum = Utils.buildRandomIntArray(N);
        arrayToSumSingleThread = Arrays.copyOf(arrayToSum, arrayToSum.length);

        pool = new ForkJoinPool(M);
    }

    @TearDown(Level.Iteration)
    public void destroy() {
        pool.shutdown();
    }

    @Benchmark
    public long multiThreadTask() throws Exception {
        LongSum ls = new LongSum(arrayToSum, 0, arrayToSum.length);
        long result = pool.submit(ls).get();
        return result;
    }

    @Benchmark
    public long singleThreadTask() {
        return LongSumMain.seqSum(arrayToSumSingleThread);
    }
}