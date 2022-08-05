package org.competitive.programming.learn;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class GenerateSubset {

    LinkedList<Integer> tracker = new LinkedList<>();

    public List<List<Integer>> combinationSet(int[] arr, int k) {
        List<List<Integer>> res = new ArrayList<>();

        if (arr.length == k) {
            res.add(tracker.stream().toList());
            return res;
        } else {
            res.addAll(combinationSet(arr, k + 1));
            tracker.addLast(arr[k]);
            res.addAll(combinationSet(arr, k + 1));
            tracker.removeLast();
        }

        return res;
    }

    public List<String> combinationSet2(int num, int x, int y, String temp) {
        List<String> res = new ArrayList<>();

        if (num == 0) {
            res.add(temp);
            return res;
        } else if (num < Math.min(x, y)) {
            return new ArrayList<>();
        }

        String comb1 = temp + x;
        String comb2 = temp + y;

        res.addAll(combinationSet2(num - x, x, y, comb1));
        res.addAll(combinationSet2(num - y, x, y, comb2));

        return res;
    }

    @Test
    public void testCombinationSet() {
        int[] arr = {0, 1, 2};

        var integers = combinationSet(arr, 0);
        System.out.println(integers);
    }

    @Test
    public void testCombinationSet2() {
        var result = combinationSet2(12, 3, 2, "");
        System.out.println(result);
        Assert.assertEquals(result.size(), 12);
    }
}
