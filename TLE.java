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
        long n = in.nextInt();
        int m = in.nextInt();
        long d = in.nextLong();
        List<Long> list = new ArrayList<>();
        list.add(1 - d);
        for (int i = 0; i < m; i++) {
            long num = in.nextLong();
            list.add(num);
        }
        list.add(n + 1);
        long cookies = 0;
        for (int i = 1; i < list.size(); i++) {
            cookies += ((list.get(i) - list.get(i - 1) - 1) / d);
        }
        cookies += (list.size() - 2);
        long count = 0;
        long ans = (long) 2e9;
        for (int i = 1; i <= m; i++) {
            long a = list.get(i) - list.get(i - 1) - 1;
            long b = list.get(i + 1) - list.get(i) - 1;
            long c = list.get(i + 1) - list.get(i - 1) - 1;
            long D = (c / d - a / d - b / d);
            if (D < ans) {
                ans = D;
                count = 0;
            }
            if (D == ans) {
                count++;
            }
        }

        System.out.println(ans + cookies - 1 + " " + count);
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
