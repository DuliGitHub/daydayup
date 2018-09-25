package com.turingschool.forkjoin.demo.utils;

import java.util.Random;

/**
 * User: William Cheng
 * Create Time: 2018/9/24 15:48
 * Description:
 */
public class Utils {

    public static int[] buildRandomIntArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = new Random().nextInt(100);
        }
        return array;
    }

    public static int[] buildRandomIntArray() {
        int size = new Random().nextInt(100);
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = new Random().nextInt(100);
        }
        return array;
    }

    public static void main(String[] args) {
        int[] ints = Utils.buildRandomIntArray(20000);

        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }
}