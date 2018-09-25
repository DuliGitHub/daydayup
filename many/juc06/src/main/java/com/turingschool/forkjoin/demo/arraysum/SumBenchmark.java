package com.turingschool.forkjoin.demo.arraysum;


import com.turingschool.forkjoin.demo.utils.Utils;
import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Benchmark)
public class SumBenchmark {

    @Param({"20000000"})
    public int N;

    @Param({"4", "10", "100"})
    public int M;

    @Param({"1000"})
    public int NUM;

    public int[] arrayToSum;
    public int[] arrayToSumSingleThread;
    public ExecutorService executor;

    @Setup(Level.Iteration)
    public void init() {
        arrayToSum = Utils.buildRandomIntArray(N);
        arrayToSumSingleThread = Arrays.copyOf(arrayToSum, arrayToSum.length);
        int numThreads = arrayToSum.length / NUM > 0 ? arrayToSum.length / NUM : 1;

        executor = Executors.newFixedThreadPool(numThreads);
    }

    @TearDown(Level.Iteration)
    public void destroy() {
        executor.shutdown();
    }

    @Benchmark
    public long multiThreadTask() throws Exception {
        long result = SumMultiThreads.sum(arrayToSum, executor);
        return result;
    }

    @Benchmark
    public long singleThreadTask() {
        return SumSequential.sum(arrayToSumSingleThread);
    }
}