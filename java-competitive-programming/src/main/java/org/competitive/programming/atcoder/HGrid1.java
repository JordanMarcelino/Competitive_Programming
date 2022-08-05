package org.competitive.programming.atcoder;

// Start of user code (user defined imports)

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public class HGrid1 {

    private final FastReader in = new FastReader();
    private final PrintWriter out = new PrintWriter(System.out);
    final long MOD = (long) (1e9 + 7);
    long[][] dp;

    public HGrid1() {
    }

    void solve() throws IOException {
        int h = i(), w = i();
        char[][] path = new char[h+1][w+1];
        dp = new long[h+1][w+1];

        for (int i = 0; i < h; i++) {
            String s = s();
            for (int j = 0; j < w; j++) {
                path[i][j] = s.charAt(j);
            }
        }

        out.println(countPath(path, h-1, w-1));
    }

    long countPath(char[][] path, int row, int col){
        if (row == 0 && col == 0) return 1;
        if (dp[row][col] != 0) return dp[row][col];

        long res = 0;
        if (row - 1 >= 0 && path[row-1][col] != '#') res = ((res % MOD) + (countPath(path, row-1, col) % MOD)) % MOD;
        if (col - 1 >= 0 && path[row][col-1] != '#') res = ((res % MOD) + (countPath(path, row, col-1) % MOD)) % MOD;

        dp[row][col] = res;
        return res;
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
        HGrid1 driver = new HGrid1();

        driver.run();
        driver.closeResources();
    }

    int i() throws IOException {
        return in.nextInt();
    }

    long l() throws IOException {
        return in.nextLong();
    }

    double d() throws IOException {
        return in.nextDouble();
    }

    String s() throws IOException {
        return in.nextLine();
    }

    void closeResources() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    static class FastReader {
        final private int BUFFER_SIZE = 1 << 16;
        private final DataInputStream din;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public FastReader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public FastReader(String file_name) throws IOException {
            din = new DataInputStream(
                    new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String nextLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n') {
                    if (cnt != 0) {
                        break;
                    } else {
                        continue;
                    }
                }
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0,
                    BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            din.close();
        }
    }
}

// End of Code
