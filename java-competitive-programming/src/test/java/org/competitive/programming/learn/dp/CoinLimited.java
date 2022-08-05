package org.competitive.programming.learn.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class CoinLimited {

    int[][] dp = new int[1000][1000];


    public int coinLimited(int[] coins, int idx, int target){
        if (target == 0) return 0;
        if (idx == 0) return Integer.MAX_VALUE - 100;
        if (dp[idx][target] != 0) return dp[idx][target];

        int best = coinLimited(coins,idx - 1, target);

        if(target >= coins[idx]){
            best = Math.min(best, coinLimited(coins,idx - 1, target - coins[idx]) + 1);
        }

        dp[idx][target] = best;
        return best;
    }

    @Test
    public void testCoinLimited() {
        int[] coins = {1,1,1,5,5,5,7};
        int target1 = 15;
        int target2 = 20;

        boolean[] m = new boolean[2];
        int res1 = coinLimited(coins,coins.length - 1, target1);
        int res2 = coinLimited(coins,coins.length - 1, target2);
        System.out.println(res1);
        System.out.println(res2);

        Assert.assertEquals(res1, 3);
        Assert.assertEquals(res2, 6);
    }
}
