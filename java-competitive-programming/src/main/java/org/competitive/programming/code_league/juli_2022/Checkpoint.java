package org.competitive.programming.code_league.juli_2022;

// Start of user code (user defined imports)

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Checkpoint {

    private final FastReader in = new FastReader();
    private final PrintWriter out = new PrintWriter(System.out);

    public Checkpoint() {
    }

    void solve() throws IOException {
        Map<Integer, Integer> scoreBoard = new HashMap<>();
        List<Integer> impos = new ArrayList<>(List.of(7, 10, 11));
        List<Integer> score = new ArrayList<>();
        boolean pos = false;

        for (int i = 0; i < 4; i++) {
            int temp = i();
            if (impos.contains(temp)) {
                out.println("IMPOSSIBLE");
                pos = true;
            }
            score.add(temp);
        }

        if (pos) return;

        if (score.contains(12)){
            int twelveCount = (int) score.stream().filter(integer -> integer == 12).count();
            if (twelveCount > 1){
                out.println("IMPOSSIBLE"); return;
            }
        }

        for (int sc : score) {
            if (sc % 4 == 0) {
                if (sc == 12) {
                    push(scoreBoard, 1, 3);
                } else if (sc > 0) {
                    push(scoreBoard, 1, sc / 4);
                    push(scoreBoard, -1, sc / 4 == 1 ? 2 : 1);
                } else {
                    push(scoreBoard, -1, 3);
                }
            } else if (sc % 4 == 1) {
                if (sc > 4) {
                    if (sc == 5) {
                        push(scoreBoard, 1, 1);
                        push(scoreBoard, 0, 1);
                        push(scoreBoard, -1, 1);
                    } else {
                        push(scoreBoard, 1, 2);
                        push(scoreBoard, 0, 1);
                    }
                } else {
                    push(scoreBoard, 0, 1);
                    push(scoreBoard, -1, 2);
                }
            } else if (sc % 4 == 2) {
                if (sc > 4) {
                    push(scoreBoard, 1, 1);
                    push(scoreBoard, 0, 2);
                } else {
                    push(scoreBoard, 0, 2);
                    push(scoreBoard, -1, 1);
                }
            }
        }

        int tmp = 0;

        out.println(scoreBoard);
        if (!Objects.equals(scoreBoard.get(-1), scoreBoard.get(1))){
            out.println("IMPOSSIBLE"); return;
        }

        for (var sc : scoreBoard.entrySet()) {
            if (sc.getValue() > 6) {
                out.println("IMPOSSIBLE");
                return;
            }
            tmp += sc.getValue();
        }

        out.println(tmp > 12 ? "IMPOSSIBLE" : "POSSIBLE");
    }

    void run() throws IOException {
        int tc = 1;
//        If want to run multiple test cases, use below code
        tc = i();

        for (int i = 1; i <= tc; i++) {
//            out.println("Case #" + i + ": ");
            solve();
        }
    }

    public static void main(String[] args) throws IOException {
        Checkpoint driver = new Checkpoint();

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

    int[] readArr(int N) throws IOException {
        int[] arr = new int[N];

        for (int i = 0; i < N; i++)
            arr[i] = i();

        return arr;
    }

    long[] readArrLong(int N) throws IOException {
        long[] arr = new long[N];

        for (int i = 0; i < N; i++)
            arr[i] = l();

        return arr;
    }

    void print(int[] arr) {
        //for debugging only
        for (int x : arr)
            out.print(x + " ");
        out.println();
    }

    boolean isPrime(long n) {
        if (n < 2) return false;
        if (n == 2 || n == 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        long sqrtN = (long) Math.sqrt(n) + 1;
        for (long i = 6L; i <= sqrtN; i += 6) {
            if (n % (i - 1) == 0 || n % (i + 1) == 0) return false;
        }
        return true;
    }

    long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    long lcm(long a, long b) {
        if (a == 0 || b == 0) return 0;
        return (a * b) / gcd(a, b);
    }

    <T> List<List<T>> permutations(T[] arr) {
        List<List<T>> result = new ArrayList<>();

        if (arr.length == 0) {
            result.add(new ArrayList<T>());
            return result;
        }

        T firstEl = arr[0];
        List<List<T>> permsWithoutFirst = permutations(Arrays.copyOfRange(arr, 1, arr.length));

        for (var perm : permsWithoutFirst) {
            for (int i = 0; i <= perm.size(); i++) {
                var permsWithFirst = new ArrayList<>(perm.subList(0, i));
                permsWithFirst.add(firstEl);
                permsWithFirst.addAll(perm.subList(i, perm.size()));

                result.add(permsWithFirst);
            }
        }


        return result;
    }

    ArrayList<Integer> findDiv(int N) {
        //gens all divisors of N
        ArrayList<Integer> ls1 = new ArrayList<Integer>();
        ArrayList<Integer> ls2 = new ArrayList<Integer>();
        for (int i = 1; i <= (int) (Math.sqrt(N) + 0.00000001); i++)
            if (N % i == 0) {
                ls1.add(i);
                ls2.add(N / i);
            }
        Collections.reverse(ls2);
        for (int b : ls2)
            if (b != ls1.get(ls1.size() - 1))
                ls1.add(b);
        return ls1;
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

    void sort(long[] arr) {
        //because Arrays.sort() uses quicksort which is dumb
        //Collections.sort() uses merge sort
        ArrayList<Long> ls = new ArrayList<>();
        for (long x : arr)
            ls.add(x);
        Collections.sort(ls);
        for (int i = 0; i < arr.length; i++)
            arr[i] = ls.get(i);
    }

    void push(Map<Integer, Integer> map, int k, int v) {
        //map[k] += v;
        if (!map.containsKey(k))
            map.put(k, v);
        else
            map.put(k, map.get(k) + v);
    }

    void pull(Map<Integer, Integer> map, int k, int v) {
        //assumes map[k] >= v
        //map[k] -= v
        int lol = map.get(k);
        if (lol == v)
            map.remove(k);
        else
            map.put(k, lol - v);
    }

    static class Pair<K, V> {
        K key;
        V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
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
