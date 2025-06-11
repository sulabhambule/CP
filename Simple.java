import java.io.*;
import java.util.*;

public class Simple {
    public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    static FASTIO in = new FASTIO();
    static int MOD = (int) 1e9 + 7;

    public static void main(String[] args) {
        int t = 1;
        while (t-- > 0) {
            solve();
        }
        out.flush();
        out.close();
    }

    static void solve() {
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            long a = in.nextLong(), b = in.nextLong();
            out.println(modPow(a, b));
        }
    }

    static long modPow(long a, long b) {
        // a power b
        if (a == 0 && b == 0)
            return 1;
        long res = 1;
        a %= MOD;
        while (b > 0) {
            if (b % 2 == 1) {
                res = (res * a) % MOD;
            }
            b >>= 1;
            a = (a * a) % MOD;
        }
        return res;
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
    }
}
