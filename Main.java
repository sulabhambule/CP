import java.io.*;
import java.util.*;

public class Main {
  static FastReader in = new FastReader();
  static PrintWriter out = new PrintWriter(System.out);
  static String L, R;
  static int n;
  static Integer[][][] dp;

  public static void main(String[] args) {
    int t = in.nextInt();
    while (t-- > 0) {
      L = in.next();
      R = in.next();
      n = L.length();
      dp = new Integer[n + 1][2][2];
      int ans = solve(0, 1, 1);
      out.println(ans);
      out.flush();
    }
  }

  static int solve(int pos, int tightL, int tightH) {
    if (pos == n)
      return 0;
    if (dp[pos][tightL][tightH] != null)
      return dp[pos][tightL][tightH];
    int best = Integer.MAX_VALUE;
    int low = tightL == 1 ? L.charAt(pos) - '0' : 0;
    int high = tightH == 1 ? R.charAt(pos) - '0' : 9;
    for (int d = low; d <= high; d++) {
      int cost = 0;
      if (d == R.charAt(pos) - '0')
        cost++;
      if (d == L.charAt(pos) - '0')
        cost++;
      int nHigh = tightH == 1 && d == high ? 1 : 0;
      int nLow = tightL == 1 && d == low ? 1 : 0;
      best = Math.min(best, cost + solve(pos + 1, nLow, nHigh));
    }
    return dp[pos][tightL][tightH] = best;
  }

  static class FastReader {
    BufferedReader br;
    StringTokenizer st;

    FastReader() {
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
  }
}
