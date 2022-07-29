package org.competitive.programming.toki_cp;

// Start of user code (user defined imports)

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class ModusTerbesar {

    private final Scanner in = new Scanner(System.in);

    public ModusTerbesar() {
    }

    void solve() throws IOException {
        int n = in.nextInt();
        int[] arr = new int[1001];
        for (int i = 0; i < n; i++) {
            arr[in.nextInt()]++;
        }
        int max = 0;
        int res = 0;

        for (int i = 1; i < 1001; i++) {
            if (max <= arr[i]){
                max = arr[i];
                if (res < i) res = i;
            }
        }

        System.out.println(res);
    }

    void run() throws IOException {
        int tc = 1;
//        If want to run multiple test cases, use below code
//        tc = i();

        for (int i = 1; i <= tc; i++) {
//            out.println("Case #" + i + ": ");
            solve();
        }
    }

    public static void main(String[] args) throws IOException {
        ModusTerbesar driver = new ModusTerbesar();

        driver.run();
    }

}

// End of Code
