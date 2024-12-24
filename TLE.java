import java.io.*;
import java.util.*;

public class TLE {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FastReader in = new FastReader();
  private static final int MOD = (int) 1e9 + 7;

  public static void main(String[] args) throws Exception {
    int t = 1;
    while (t-- > 0) {
      TLAM();
    }
    out.close();
  }

  private static void TLAM() {
    int n = in.nextInt();
    int x = in.nextInt();
    int[] h = new int[n];
    for (int i = 0; i < n; i++) {
      h[i] = in.nextInt();
    }
    int[] s = new int[n];
    for (int i = 0; i < n; i++) {
      s[i] = in.nextInt();
    }
    int[] dp = new int[x + 1];
    // dp[i][j] = max number of pages in books from index i to n with left page j
    // dp[i][j] = dp[i + 1][j - a[i]] Or dp[i + 1][j];
    // ans = dp[0][x];

    for (int i = n - 1; i >= 0; i--) {
      int[] curr = new int[x + 1];
      for (int j = 0; j <= x; j++) {
        if (h[i] <= j) {
          curr[j] = Math.max(dp[j], s[i] + dp[j - h[i]]);
        } else {
          curr[j] = dp[j];
        }
      }
      dp = curr;  
    }

    out.println(dp[x]);

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

    String nextLine() {
      String str = "";
      try {
        str = br.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return str;
    }
  }
}
