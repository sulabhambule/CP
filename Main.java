import java.io.*;
import java.util.*;

public class Main {
    public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    static FastReader in = new FastReader();
    // private static final int MOD = (int) 1e9 + 7;
    private static final int MOD = 998244353;

    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-- > 0) {
            function();
        }
        out.close();
    }

    /*
     * @Sulabh Ambule
     * Lets hope for Best !
     */

    private static void function() {
        int n = in.nextInt();
        int[][] adj = new int[n][n];

        for (int i = 0; i < n; i++) {
            String s = in.next();
            for (int j = 0; j < s.length(); j++) {
                adj[i][j] = s.charAt(j) - '0';
            }
        }
        Integer[] p = new Integer[n];
        for (int i = 0; i < n; i++) {
            p[i] = i;
        }
        Arrays.sort(p, (x, y) -> {
            if (adj[x][y] == 1) {
                return Integer.compare(x, y);
            } else {
                return Integer.compare(y, x);
            }
        });
        for (int i : p) {
            System.out.print((i + 1) + " ");
        }
        System.out.println();
    }

    /*------------------------------------------------------------------------------------------------------ */
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