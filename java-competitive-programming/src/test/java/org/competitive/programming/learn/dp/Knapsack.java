package org.competitive.programming.learn.dp;

import org.junit.Assert;
import org.junit.Test;

public class Knapsack {

    int[][] dp = new int[1000][1000];

    public int knapsack(int[] weight, int[]values, int idx, int storage){
        if (idx == 0) return 0;
        if(dp[idx][storage] != 0) return dp[idx][storage];

        int result = knapsack(weight, values, idx - 1, storage);
        if(storage >= weight[idx]){
            result = Math.max(result, knapsack(weight, values, idx - 1, storage - weight[idx]) + values[idx]);
        }

        dp[idx][storage] = result;
        return result;
    }

    @Test
    public void testKnapsack() {
        int[] values = {6,5,4,6,4};
        int[] weight = {5,4,3,7,4};
        int storage = 14;

        int result = knapsack(weight, values, values.length - 1, storage);
        System.out.println(result);
        Assert.assertEquals(result, 15);
    }
}
