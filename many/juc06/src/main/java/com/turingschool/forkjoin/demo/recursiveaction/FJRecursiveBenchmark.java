package com.turingschool.forkjoin.demo.recursiveaction;


import com.turingschool.forkjoin.demo.utils.Utils;
import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 10, time = 3, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 3, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Benchmark)
public class FJRecursiveBenchmark {

    @Param({"20000000"})
    public int N;

    @Param({"4", "10", "100"})
    public int M;

    @Param({"100"})
    public int T;

    public int[] arrayToSort;
    public int[] arrayToSortSingleThread;
    public ForkJoinPool pool;

    @Setup(Level.Iteration)
    public void init() {
        arrayToSort = Utils.buildRandomIntArray(N);
        arrayToSortSingleThread = Arrays.copyOf(arrayToSort, arrayToSort.length);

        pool = new ForkJoinPool(M);
    }

    @TearDown(Level.Iteration)
    public void destroy() {
        pool.shutdown();
    }

    @Benchmark
    public int[] forkJoinTasks() {
        MergeSortAction mergeSortAction = new MergeSortAction(arrayToSort, 100);
        pool.invoke(mergeSortAction);
        return mergeSortAction.getSortedArray();
    }

    @Benchmark
    public int[]  singleThreadTask() {
        return MergeSortMain.sequentialSort(arrayToSortSingleThread, 100);
     }


}