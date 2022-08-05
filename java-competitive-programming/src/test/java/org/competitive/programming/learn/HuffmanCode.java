package org.competitive.programming.learn;

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

public class HuffmanCode {

    void printCode(HuffmanNode root, String s){
        if (root.left == null && root.right == null && Character.isLetter(root.c)){

            System.out.println(root.c + "   |    " + s);

            return;
        }

        printCode(root.left, s + "0");
        printCode(root.right, s + "1");
    }

    char[] codes = {'A', 'B', 'C', 'D'};
    int[] items = {5,1,6,3};
    PriorityQueue<HuffmanNode> q = new PriorityQueue<>(codes.length, new HuffmanComparator());

    @Test
    public void testHuffmanCode() {

        for (int i = 0; i < codes.length; i++) {
            HuffmanNode hn = new HuffmanNode(items[i], codes[i]);
            q.add(hn);
        }

        HuffmanNode root = null;
        while (q.size() > 1){
            HuffmanNode left = q.poll();
            HuffmanNode right = q.poll();

            HuffmanNode hn = new HuffmanNode(left.item + right.item, '-');
            hn.left = left; hn.right = right;
            root = hn;

            q.add(hn);
        }


        printCode(root, "");
    }
}


class HuffmanNode{
    int item;
    char c;
    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode(int item, char c) {
        this.item = item;
        this.c = c;
    }
}

class HuffmanComparator implements Comparator<HuffmanNode>{

    @Override
    public int compare(HuffmanNode o1, HuffmanNode o2) {
        return o1.item - o2.item;
    }
}
