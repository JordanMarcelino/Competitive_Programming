package org.competitive.programming.code_league.juli_2022;

// Start of user code (user defined imports)

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class CewekCowok {

    private final FastReader in = new FastReader();
    private final PrintWriter out = new PrintWriter(System.out);

    public CewekCowok() {
    }

    void solve() throws IOException {
        final String girl = "CHAT WITH HER!";
        final String boy = "IGNORE HIM!";

        String name = s();
        Map<Character, Integer> hash = new HashMap<>();

        for (char c : name.toCharArray()) {
            if (!hash.containsKey(c)){
                hash.put(c, 1);
            }else{
                hash.put(c, hash.get(c) + 1);
            }
        }

        int temp = 0;
        for (int i = 0; i < name.length() - 1; i++) {
            if (i < temp) continue;

            if (name.charAt(i) == name.charAt(i+1)){
                for (int j = i + 1; j < name.length() - 1; j++) {
                    if (name.charAt(j) != name.charAt(j+1)){
                        hash.put(name.charAt(j), hash.get(name.charAt(j)) - (j-i+1));
                        temp = j + 1;
                        break;
                    }
                }
            }
        }

        int res = 0;

        for (var set: hash.entrySet()) {
            if (set.getValue() != 0){
                res++;
            }
        }

        out.println(res % 2 == 0 ? girl : boy);
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
        CewekCowok driver = new CewekCowok();

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

        if (arr.length == 0){
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

    void push(TreeMap<Integer, Integer> map, int k, int v) {
        //map[k] += v;
        if (!map.containsKey(k))
            map.put(k, v);
        else
            map.put(k, map.get(k) + v);
    }

    void pull(TreeMap<Integer, Integer> map, int k, int v) {
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
