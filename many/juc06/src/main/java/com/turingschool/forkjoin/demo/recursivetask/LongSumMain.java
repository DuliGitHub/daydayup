package com.turingschool.forkjoin.demo.recursivetask;

import com.turingschool.forkjoin.demo.utils.Utils;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class LongSumMain {
	static final int NCPU = Runtime.getRuntime().availableProcessors();
	/** for time conversion */
	static final long NPS = (1000L * 1000 * 1000);

	static long calcSum;

	static final boolean reportSteals = true;

	public static void main(String[] args) throws Exception {
		int[] array = Utils.buildRandomIntArray(20000000);


 		calcSum = seqSum(array);
		System.out.println("seq sum=" + calcSum);

		LongSum ls = new LongSum(array, 0, array.length);
  		ForkJoinPool fjp  = new ForkJoinPool(4); // with number of threads to use

		ForkJoinTask<Long> result = fjp.submit(ls);
		System.out.println("forkjoin sum=" + result.get());

		fjp.shutdown();

	}


	static long seqSum(int[] array) {
		long sum = 0;
		for (int i = 0; i < array.length; ++i)
			sum += array[i];
		return sum;
	}
}