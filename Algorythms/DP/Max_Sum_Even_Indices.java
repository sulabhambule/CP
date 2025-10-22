import java.io.*;
import java.util.*;

// CF link : https://codeforces.com/contest/1373/problem/D

public class Max_Sum_Even_Indices {
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
    long[] a = new long[n];
    for (int i = 0; i < n; i++) {
      a[i] = in.nextLong();
    }
    long[][] dp = new long[n + 1][3];
    // dp[i][0] --> max possible sum if we not reverse any subarray yet.
    // dp[i][1] --> max possible sum if we are in reverse subarray running.
    // dp[i][2] --> max possible sum if reverse subarray, already taken.

    // here the dp[i + 1] --> store the ans till a[0...i].

    for (int i = 0; i < n; i++) {
      // if we not start yet reversing part.
      dp[i + 1][0] = Math.max(dp[i + 1][0], dp[i][0] + ((i & 1) == 1 ? 0 : a[i]));

      // no we are taking the current ith index in the reversing part so if the index
      // is odd then include a[i] else exclude it. So in this case the ith index and i
      // + 1 th index has considered in the dp as if we consider ith index in
      // reversing part then if i is odd then it include else i + 1 inclide in the sum

      if (i + 2 <= n)
        dp[i + 2][1] = Math.max(dp[i + 2][1], Math.max(dp[i][0], dp[i][1]) + ((i & 1) == 1 ? a[i] : a[i + 1]));

      // now we are taking 2 i,e the reversing part is already taken

      dp[i + 1][2] = Math.max(dp[i + 1][2],
          Math.max(dp[i][0], Math.max(dp[i][1], dp[i][2])) + ((i & 1) == 1 ? 0 : a[i]));
    }

    long ans = Math.max(dp[n][0], Math.max(dp[n][1], dp[n][2])); // given we may not reverse any subarray or reverse.
    out.println(ans);
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