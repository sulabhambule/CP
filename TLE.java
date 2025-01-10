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
        String s = in.next();
        int n = s.length();
        long[] dp = new long[n]; // dp[i] = total substrings divisible by 4 ending at index i

        // if the current number is divisible by 4 then add 1 to the aswer, if the
        // number last 2 is divisible by 4 then all number are divisible by 4 means add
        // i to the answer. and also so dp[i - 1]

        for (int i = 0; i < n; i++) {
            if ((s.charAt(i) - '0') % 4 == 0) {
                dp[i]++;
            }
            if (i > 0) {
                int num = (s.charAt(i - 1) - '0') * 10 + (s.charAt(i) - '0');
                if (num % 4 == 0) {
                    dp[i] += (i + dp[i - 1]);
                } else {
                    dp[i] += dp[i - 1];
                }
            }
        }
        System.out.println(dp[n - 1]);
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