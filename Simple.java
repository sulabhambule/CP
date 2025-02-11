import java.io.*;
import java.util.*;

public class Simple {
    public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    static FASTIO in = new FASTIO();

    public static void main(String[] Hi) throws IOException {
        int cp = in.nextInt();
        while (cp-- > 0) {
            solve();
        }
        out.flush();
        out.close();
    }

    static void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        if (m == 1) {
            out.println(0);
        } else if (n > m - 1) {
            out.println(m);
        } else {
            out.println(n + 1);
        }

        for (int i = 0; i < Math.min(m - 1, n); i++) {
            for (int j = 0; j < m; j++) {
                out.print((j + i) % m + " ");
            }
            out.println();
        }

        if (n > m - 1) {
            for (int i = m - 1; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    out.print(j + " ");
                }
                out.println();
            }
        }
    }

    static class FASTIO {
        BufferedReader br;
        StringTokenizer st;

        public FASTIO() {
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
