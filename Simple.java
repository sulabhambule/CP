import java.io.*;
import java.util.*;

public class Simple {
    static final int INF = (int) 1e9;
    static int[] fTree;

    public static void main(String[] args) throws IOException {
        FastReader in = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        int q = in.nextInt();
        long[] x = new long[n + 1];
        for (int i = 1; i <= n; i++)
            x[i] = in.nextLong();
        List<List<int[]>> queries = new ArrayList<>();
        for (int i = 0; i <= n; i++)
            queries.add(new ArrayList<>());

        for (int i = 0; i < q; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            queries.get(a).add(new int[] { b, i });
        }

        int[] ans = new int[q];

        fTree = new int[n + 1];

        Map<Long, Integer> map = new HashMap<>();

        for (int i = n; i > 0; i--) {
            long val = x[i];
            if (map.containsKey(val)) {
                update(map.get(val), -1);
            }
            map.put(val, i);
            update(i, 1);

            for (int[] qu : queries.get(i)) {
                ans[qu[1]] = query(i, qu[0]);
            }
        }

        for (int i : ans) {
            out.print(i + " ");
        }

        out.println();
        out.flush();
    }

    public static void update(int idx, int val) {
        int n = fTree.length;
        while (idx < n) {
            fTree[idx] += val;
            idx += (idx & -idx);
        }
    }

    public static int prefix(int idx) {
        int sum = 0;
        while (idx > 0) {
            sum += fTree[idx];
            idx -= (idx & -idx);
        }
        return sum;
    }

    public static int query(int l, int r) {
        return prefix(r) - prefix(l - 1);
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
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
