import java.io.*;
import java.util.*;

public class TLE {
    public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    static FastReader in = new FastReader();
    private static final int MOD = (int) 1e9 + 7;
    // private static final int MOD = 998244353;

    public static void main(String[] args) {
        int T = in.nextInt();
        while (T-- > 0) {
            solve();
        }
        out.close();
    }

    private static void solve() {
        int n = in.nextInt();
        int k = in.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextLong();
        }

        List<Long> b = new ArrayList<>();
        for (int i = k; i < n; i++) {
            b.add(a[i]);
        }

        List<Long> c = new ArrayList<>();
        for (int i = k - 1; i > 0; i--) {
            c.add(-a[i]);
        }
        long sum = 0;
        int operations = 0;
        PriorityQueue<Long> pq = new PriorityQueue<>();

        for (long x : b) {
            sum += x;
            pq.add(x);
            while (sum < 0) {
                long smallest = pq.poll();
                sum -= 2L * smallest;
                operations++;
            }
        }

        sum = 0;
        PriorityQueue<Long> pq2 = new PriorityQueue<>();
        for (long x : c) {
            sum += x;
            pq2.add(x);
            while (sum < 0) {
                long smallest = pq2.poll();
                sum -= 2L * smallest;
                operations++;
            }
        }

        System.out.println(operations);
    }

    static class FastReader {

        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
