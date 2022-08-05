package org.competitive.programming.learn;

import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class ComparisonToSorting {

    public List<Integer> findCommonNumber1(int[] a, int[] b) {
        Set<Integer> set = new HashSet<>();
        List<Integer> res = new ArrayList<>();

        for (int i : a) {
            set.add(i);
        }

        for (int i : b) {
            if (set.contains(i)){
                res.add(i);
            }

        }

        return res;
    }

    public List<Integer> findCommonNumber2(int[] a, int[] b) {
        List<Integer> res = new ArrayList<>();
        Arrays.sort(a);
        Arrays.sort(b);
//        sort(a);
//        sort(b);

        int i = 0, j = 0;
        while (i < a.length && j < b.length) {
            if (a[i] == b[j]) {
                res.add(a[i]);
                i++;
                j++;
            } else if (a[i] < b[j]) {
                i++;
            }else{
                j++;
            }
        }

        return res;
    }

    void sort(int[] arr) {
        //because Arrays.sort() uses quicksort which is dumb
        //Collections.sort() uses merge sort
        ArrayList<Integer> ls = new ArrayList<Integer>();
        for (int x : arr)
            ls.add(x);
        Collections.sort(ls);
        for (int i = 0; i < arr.length; i++)
            arr[i] = ls.get(i);
    }

    @Test
    public void testFindCommon1() {
        var start = LocalDateTime.now();
        Random rand = new Random();
        rand.setSeed(LocalDateTime.now().getNano());
        final int N = 1000;
        int[] a = new int[N];
        int[] b = new int[N];

        for (int i = 0; i < N; i++) {
            a[i] = rand.nextInt(N) + 1;
            b[i] = rand.nextInt(N) + 1;
        }

        List<Integer> list = findCommonNumber1(a, b);
        System.out.println("a : " + Arrays.toString(a));
        System.out.println("b : " + Arrays.toString(b));
        System.out.println("Result : " + list);

        var duration = Duration.between(start, LocalDateTime.now());
        System.out.println("Duration : " + duration.getNano() + " nanoseconds");
    }

    @Test
    public void testFindCommon2() {
        var start = LocalDateTime.now();
        Random rand = new Random();
        rand.setSeed(LocalDateTime.now().getNano());
        final int N = 1000;
        int[] a = new int[N];
        int[] b = new int[N];

        for (int i = 0; i < N; i++) {
            a[i] = rand.nextInt(N) + 1;
            b[i] = rand.nextInt(N) + 1;
        }

        List<Integer> list = findCommonNumber2(a, b);
        System.out.println("a : " + Arrays.toString(a));
        System.out.println("b : " + Arrays.toString(b));
        System.out.println("Result : " + list);

        var duration = Duration.between(start, LocalDateTime.now());
        System.out.println("Duration : " + duration.getNano() + " nanoseconds");
    }

    @Test
    public void compareFindCommon1AndFindCommon2_inputOneThousand() {
        Random rand = new Random();
        rand.setSeed(LocalDateTime.now().getNano());
        final int N = (int) 1e3;
        int[] counter = new int[N+1];
        Arrays.fill(counter, 0);
        int[] a = new int[N];
        int[] b = new int[N];

        for (int i = 0; i < N; i++) {
            int x = rand.nextInt(N) + 1;

            while (counter[x] == 1) {
                x = rand.nextInt(N) + 1;
            }

            counter[x] = 1;
            a[i] = x;
        }

        Arrays.fill(counter, 0);
        for (int i = 0; i < N; i++) {
            int x = rand.nextInt(N) + 1;

            while (counter[x] == 1) {
                x = rand.nextInt(N) + 1;
            }

            counter[x] = 1;
            b[i] = x;
        }

        var start1 = System.nanoTime();
        List<Integer> list1 = findCommonNumber1(a, b);
        var duration1 = System.nanoTime() - start1;


        var start2 = System.nanoTime();
        List<Integer> list2 = findCommonNumber2(a, b);
        var duration2 = System.nanoTime() - start2;

        System.out.println("Duration1 : " + duration1 + " milliseconds");
        System.out.println("Duration2 : " + duration2 + " milliseconds");

        Collections.sort(list1);
        Assert.assertEquals(list1, list2);
        Assert.assertTrue(duration2 < duration1);
    }
}
