package org.competitive.programming.learn;

import org.junit.Assert;
import org.junit.Test;

public class Greedy {

    public int binarySearchClosest(int[] arr, int target) {
        int idx = -1;
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) return mid;

            if (target > arr[mid]) {
                left = mid + 1;
                idx = mid;
            } else if (target < arr[mid]){
                right = mid - 1;
                idx = mid - 1;
            }
        }

        return idx;
    }

    public int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left != right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) return mid;

            if (arr[mid] >= target) right = mid;
            else left = mid + 1;
        }

        return right;
    }


    public int coins(int target) {
        int[] coinAvailable = {1, 2, 5, 10, 20, 50, 100, 200};
        int result = 0;

        while (target > 0) {
            int min = binarySearchClosest(coinAvailable, target);
            target -= coinAvailable[min];
            System.out.println(target);
            result++;
        }

        return result;
    }


    @Test
    public void testCoinsSearch() {
        int target = 520;

        Assert.assertEquals(coins(target), 4);
    }

    @Test
    public void testBinarySearch() {
        int[] arr = {0,4,12};
        Assert.assertEquals(binarySearch(arr, 2), 1);
    }
}
