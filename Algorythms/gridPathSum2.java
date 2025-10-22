
import java.io.*;
import java.util.*;

// cf link : https://codeforces.com/contest/1695/problem/C

public class gridPathSum2 {
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
    int n = in.nextInt(), m = in.nextInt();
    int[][] arr = new int[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        arr[i][j] = in.nextInt();
      }
    }
    if ((n + m) % 2 == 0) {
      out.println("NO");
      return;
    }
    int[][] dpMin = new int[n + 1][m + 1];
    int[][] dpMax = new int[n + 1][m + 1];
    // we are going to find the min ans max sum to reach the destination
    dpMin[n - 1][m - 1] = dpMax[n - 1][m - 1] = arr[n - 1][m - 1];
    for (int i = n - 2; i >= 0; i--) {
      dpMin[i][m - 1] = dpMin[i + 1][m - 1] + arr[i][m - 1];
      dpMax[i][m - 1] = dpMax[i + 1][m - 1] + arr[i][m - 1];
    }
    for (int j = m - 2; j >= 0; j--) {
      dpMin[n - 1][j] = dpMin[n - 1][j + 1] + arr[n - 1][j];
      dpMax[n - 1][j] = dpMax[n - 1][j + 1] + arr[n - 1][j];
    }

    for (int i = n - 2; i >= 0; i--) {
      for (int j = m - 2; j >= 0; j--) {
        dpMin[i][j] = arr[i][j] + Math.min(dpMin[i + 1][j], dpMin[i][j + 1]);
        dpMax[i][j] = arr[i][j] + Math.max(dpMax[i + 1][j], dpMax[i][j + 1]);
      }
    }

    // it means that we can achieve from the minSum to maxSum , as each change of
    // value differ the sum by 2 so sum zero is possible if min <= 0 and max >= 0.
    if (dpMin[0][0] <= 0 && dpMax[0][0] >= 0)
      out.println("YES");
    else
      out.println("NO");
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