import java.io.*;
import java.util.*;

public class Main {
    static FastIO in = new FastIO();
    static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {
        solve();
        out.close();
    }

    static void solve() {
        int n = in.nextInt();
        String s = in.next();
        int zero = 0, one = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') {
                zero++;
            } else {
                one++;
            }
        }
        if (one == 0 || zero == 0) {
            out.println(0);
            return;
        }
    }

    static class FastIO {
        BufferedReader br;
        StringTokenizer st;

        public FastIO() {
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
    }
}
