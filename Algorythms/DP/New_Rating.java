import java.io.*;
import java.util.*;

// Cf link : https://codeforces.com/contest/2029/problem/C

public class New_Rating {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FASTIO in = new FASTIO();
  static final int MOD = 998244353;
  static final int mod = (int) 1e9 + 7;
  static final int inf = (int) 1e9;
  static final long INF = (long) 1e18;

  public static void main(String[] args) {
    int t = in.nextInt();
    while (t-- > 0) {
      solve();
    }
    out.close();
  }

  static void solve() {
    int n = in.nextInt();
    int[] a = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      a[i] = in.nextInt();
    }
    // dp[i]: what will be ans if we consider a[0....i], & contest is skkipped
    // there are two transition if we cosider the ithindex for the r and check for
    // left l for the prefix max or we put the ith index for normal continuos range.

    int[] dp = new int[n + 1];
    int[] prefix = new int[n + 1];

    int x = 0;
    for (int i = 1; i <= n; i++) {
      if (a[i] > x) {
        x++;
      } else if (a[i] < x) {
        x--;
      }
      prefix[i] = Math.max(x, prefix[i - 1]);
    }
    // base case if there is one element that should be removed dp[0] = 0.

    for (int i = 2; i <= n; i++) {
      int y = dp[i - 1];
      if (a[i] > y) {
        y++;
      } else if (a[i] < y) {
        y--;
      }
      // y if we stopped the skipped contest in i - 1 th index.
      // prefix[i - 1] if the at ith index the skipped subarray ends.
      dp[i] = Math.max(y, prefix[i - 1]);
    }
    out.println(dp[n]);
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

    String nextLine() {
      String str = "";
      try {
        st = null;
        str = br.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return str;
    }
  }
}