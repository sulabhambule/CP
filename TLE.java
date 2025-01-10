import java.io.*;
import java.util.*;

public class TLE {
    public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    static FastReader in = new FastReader();
    private static final int MOD = (int) 1e9 + 7;
    // private static final int MOD = 998244353;

    public static void main(String[] args) {
        int T = 1;
        while (T-- > 0) {
            solve();
        }
        out.close();
    }

    private static void solve() {
        int n = in.nextInt();
        List<Integer> result = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        int[] parent = new int[n + 1];
        int root = 0;
        int[] c = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int pi = in.nextInt();
            int ci = in.nextInt();
            if (pi == -1) {
                root = i;
            } else {
                parent[i] = pi;
            }
            c[i] = ci;
        }
        for (int i = 1; i <= n; i++) {
            if (i == root) {
                continue;
            }
            if (c[i] == 0) {
                set.add(i);
                set.add(parent[i]);
            }
        }
        for (int i = 1; i <= n; i++) {
            if (i == root)
                continue;
            if (!set.contains(i)) {
                result.add(i);
            }
        }
        if (result.isEmpty()) {
            System.out.println(-1);
            return;
        }

        Collections.sort(result);
        for (int i : result) {
            System.out.print(i + " ");
        }
        System.out.println();
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