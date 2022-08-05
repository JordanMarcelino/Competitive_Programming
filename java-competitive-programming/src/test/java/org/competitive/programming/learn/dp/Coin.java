package org.competitive.programming.learn.dp;

import org.junit.Assert;
import org.junit.Test;

public class Coin {

    public int findSmallestCoin(int x, int[] arr) {
        if (x < 0) return 0;
        if (x == 0) return 0;
        int res = Integer.MAX_VALUE;

        for (int val : arr) {
            res = Math.min(res, findSmallestCoin(x - val, arr) + 1);
        }

        return res;
    }

    int[] memo = new int[(int) 10e5];

    public int findSmallestCoinWithMemo(int x, int[] arr) {
        if (x < 0) return 0;
        if (x == 0) return 0;
        if (memo[x] != 0) return memo[x];
        int res = Integer.MAX_VALUE;

        for (int val : arr) {
            res = Math.min(res, findSmallestCoinWithMemo(x - val, arr) + 1);
        }

        memo[x] = res;
        return res;
    }

    public int findSmallestCoinWithIterative(int x, int[] arr) {
        int[] value = new int[x + 1];
        int[] first = new int[x + 1];
        value[0] = 0;

        for (int i = 1; i <= x; i++) {
            value[i] = Integer.MAX_VALUE;
            for (int val : arr) {
                if (i - val >= 0 && ((value[i - val] + 1) < value[i])) {
                    value[i] = value[i - val] + 1;
                    first[i] = val;
                }
            }
        }

        while (x > 0) {
            System.out.print(first[x] + " ");
            x -= first[x];
        }

        System.out.println();
        return value[value.length - 2];
    }


    @Test
    public void testCoin() {
        int[] coins = {1, 3, 4};
        int[] input = {4, 6, 10};
        int[] expected = {1, 2, 3};

        System.out.println("Without memo");
        for (int i = 0; i < input.length; i++) {
            long start = System.nanoTime();
            int smallestCoin = findSmallestCoin(input[i], coins);
            Assert.assertEquals(expected[i], smallestCoin);
            System.out.println("Result " + i + " : " + smallestCoin);
            System.out.println("Result " + i + " duration : " + (System.nanoTime() - start));
        }

        System.out.println("With memo");
        for (int i = 0; i < input.length; i++) {
            long start = System.nanoTime();
            int smallestCoin = findSmallestCoinWithMemo(input[i], coins);
            Assert.assertEquals(expected[i], smallestCoin);
            System.out.println("Result " + i + " : " + smallestCoin);
            System.out.println("Result " + i + " duration : " + (System.nanoTime() - start));
        }


        System.out.println("With iterative");
        for (int i = 0; i < input.length; i++) {
            long start = System.nanoTime();
            int smallestCoin = findSmallestCoinWithIterative(input[i], coins);
            Assert.assertEquals(expected[i], smallestCoin);
            System.out.println("Result " + i + " : " + smallestCoin);
            System.out.println("Result " + i + " duration : " + (System.nanoTime() - start));
        }

    }
}
