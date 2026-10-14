
// problem link : https://codeforces.com/gym/302977/problem/C
// contest link : https://codeforces.com/gym/302977

import java.io.*;
import java.util.*;

public class bitmask_take_skip {
  static final long INF = Long.MAX_VALUE / 2;
  static FastReader in = new FastReader();
  static PrintWriter out = new PrintWriter(System.out);

  public static void main(String[] args) throws IOException {
    int t = 1;
    while (t-- > 0) {
      solve();
    }
    out.close();
  }

  static void solve() {
    int n = in.nextInt();
    long[][] dp = new long[n + 1][8];
    long inf = (int) 1e17;
    for (int i = 0; i <= n; i++) {
      for (int j = 0; j < 8; j++) {
        dp[i][j] = inf;
      }
    }
    dp[0][0] = 0;

    for (int i = 0; i < n; i++) {
      long cost = in.nextLong();
      String s = in.next();
      int string_mask = 0;
      for (char ch : s.toCharArray()) {
        if (ch == 'A')
          string_mask |= 1;
        if (ch == 'B')
          string_mask |= 2;
        if (ch == 'C')
          string_mask |= 4;
      }

      for (int mask = 0; mask < 8; mask++) {
        dp[i + 1][mask] = Math.min(dp[i + 1][mask], dp[i][mask]);

        dp[i + 1][mask | string_mask] = Math.min(dp[i + 1][mask | string_mask], cost + dp[i][mask]);
      }
    }
    long ans = dp[n][7];

    if (ans == inf)
      ans = -1;
    out.println(ans);
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
