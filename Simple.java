import java.io.*;
import java.util.*;

public class Simple {
    static final int INF = Integer.MAX_VALUE / 2;
    static FastReader in = new FastReader();
    static PrintWriter out = new PrintWriter(System.out);
    static int[] fTree;

    public static void main(String[] args) throws IOException {
        int n = in.nextInt();
        int q = in.nextInt();

        List<Long> values = new ArrayList<>();
        int[] salary = new int[n + 1];
        for (int i = 0; i < n; i++) {
            salary[i + 1] = in.nextInt();
            values.add((long) salary[i + 1]);
        }
        String[] type = new String[q];
        int[] k = new int[q];
        long[] a = new long[q], b = new long[q], x = new long[q];

        for (int i = 0; i < q; i++) {
            String t = in.next();
            type[i] = t;
            if (t.equals("!")) {
                k[i] = in.nextInt();
                x[i] = in.nextLong();
                values.add(x[i]);
            } else {
                a[i] = in.nextLong();
                b[i] = in.nextLong();
                values.add(a[i]);
                values.add(b[i]);
            }
        }

        List<Long> sorted = new ArrayList<>(new HashSet<>(values));
        Collections.sort(sorted);
        Map<Long, Integer> comp = new HashMap<>();

        for (int i = 0; i < sorted.size(); i++)
            comp.put(sorted.get(i), i + 1);

        fTree = new int[sorted.size() + 1];
        for (int i = 1; i <= n; i++)
            update(comp.get((long) salary[i]), 1);

        // processing the query
        for (int i = 0; i < q; i++) {
            if (type[i].equals("!")) {
                int index = k[i];
                update(comp.get((long) salary[index]), -1);
                salary[index] = (int) x[i]; // new updated value
                update(comp.get(x[i]), +1);
            } else {
                // now count the no of salries between a to b so we want first index where val
                // >= a and last index val <= b
                int left = comp.get(a[i]);
                int right = comp.get(b[i]);
                out.println(query(left, right));
            }
        }
        out.flush();
    }

    public static void update(int idx, int delta) {
        while (idx < fTree.length) {
            fTree[idx] += delta;
            idx += (idx & -idx);
        }
    }

    public static int query(int idx) {
        int sum = 0;
        while (idx > 0) {
            sum += fTree[idx];
            idx -= (idx & -idx);
        }
        return sum;
    }

    public static int query(int l, int r) {
        if (l > r)
            return 0;
        return query(r) - query(l - 1);
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
