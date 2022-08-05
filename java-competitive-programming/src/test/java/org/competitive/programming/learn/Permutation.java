package org.competitive.programming.learn;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Permutation {

    LinkedList<Integer> tracker = new LinkedList<>();
    boolean[] choosen = new boolean[(int) 10e5];

    public List<List<Integer>> search(int[] arr) {
        List<List<Integer>> res = new ArrayList<>();
        if (tracker.size() == arr.length) {
            res.add(tracker.stream().toList());
            return res;
        } else {
            for (int i = 0; i < arr.length; i++) {
                if (choosen[i]) continue;
                choosen[i] = true;
                tracker.addLast(arr[i]);
                res.addAll(search(arr));
                choosen[i] = false;
                tracker.removeLast();
            }
        }

        return res;
    }

    @Test
    public void testPermutation() {
        int[] arr = {0,1,2};
        List<List<Integer>> list = search(arr);
        System.out.println(list);
    }
}
