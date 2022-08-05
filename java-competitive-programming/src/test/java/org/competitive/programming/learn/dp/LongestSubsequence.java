package org.competitive.programming.learn.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class LongestSubsequence {

    public int binarySearch(int[] arr, int left, int right, int target){
        while (left != right){
            int mid = left + (right - left) / 2;

            if (arr[mid] >= target) right = mid;
            else left = mid + 1;
        }

        return right;
    }

    public int lis(int[] arr, int n){

        int[] tailTable = new int[n];
        int len = 1;

        tailTable[0] = arr[0];

        for (int i = 1; i < n; i++) {
            if (arr[i] < tailTable[0]) tailTable[0] = arr[i];
            else if (arr[i] > tailTable[len - 1]) tailTable[len++] = arr[i];
            else {
                int idx = binarySearch(tailTable, 0, len - 1, arr[i]);
                System.out.println("Before" + Arrays.toString(tailTable));
                System.out.println("Idx to replace " + idx);
                tailTable[idx] = arr[i];
                System.out.println("After" + Arrays.toString(tailTable));
            }
        }

        return len;
    }


    int[][] dp = new int[1000][1000];
    boolean[][] marked = new boolean[1000][1000];
    public int lcs(String a, String b, int ai, int bi){
        if (ai == 0 || bi == 0) return 0;
        if (marked[ai][bi]) return dp[ai][bi];

        int best;
        if(a.charAt(ai) == b.charAt(bi)){
            best = lcs(a, b, ai - 1, bi - 1) + 1;
        }else{
            best = Math.max(lcs(a, b, ai - 1, bi), lcs(a, b, ai, bi - 1));
        }

        marked[ai][bi] = true;
        dp[ai][bi] = best;
        return best;
    }


    @Test
    public void testLcs() {
        String a = "ajaib";
        String b  = "badai";
        int lcs = lcs(a, b, a.length() - 1, b.length() - 1);
        System.out.println(lcs);
    }

    @Test
    public void testLis() {
        int[] arr = { 2, 5, 3, 7, 11, 8, 10, 13, 6 };
        int n = arr.length;
        int lis = lis(arr, n);
        System.out.println(lis);
    }
}
