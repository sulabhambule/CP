import static java.util.Collections.max;

import java.io.*;
import java.util.*;

public class Simple {
    static FastReader in = new FastReader();
    public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    static int mod = (int) 1e9 + 7;

    public static void main(String[] Hi) throws IOException {
        int cp = in.nextInt();
        while (cp-- > 0) {
            solve();
        }
        out.close();
    }

    static void solve() {

    }
    // static void solve() {
    // int n = in.nextInt();
    // int m = in.nextInt();
    // int max = 0;
    // int[][] a = inputIntArr(n, m);
    // Map<Integer, List<Integer>> row = new HashMap<>();
    // Set<Integer> col = new HashSet<>();
    // for (int[] aa : a) {
    // for (int ii : aa) {
    // max = max(max, ii);
    // }
    // }
    // boolean flag = false;
    // for (int i = 0; i < n; i++) {
    // for (int j = 0; j < m; j++) {
    // if (a[i][j] == max) {
    // List<Integer> r = row.computeIfAbsent(i, k -> new ArrayList<>());
    // r.add(j);
    // col.add(j);
    // }
    // }
    // }
    // if (row.size() == 1 || col.size() == 1) {
    // println(max - 1);
    // return;
    // }

    // for (int r : row.keySet()) {
    // Set<Integer> other = new HashSet<>();
    // for (Map.Entry<Integer, List<Integer>> e : row.entrySet()) {
    // int rr = e.getKey();
    // if (rr == r)
    // continue;
    // other.addAll(e.getValue());
    // if (other.size() > 1)
    // break;
    // }
    // if (other.size() == 1) {
    // println(max - 1);
    // return;
    // }
    // }
    // println(max);
    // }

    static class FastReader {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

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
