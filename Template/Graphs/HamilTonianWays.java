
import java.io.*;
import java.util.*;

public class HamilTonianWays {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FASTIO in = new FASTIO();
  static int MOD = (int) 1e9 + 7;

  public static void main(String[] args) throws IOException {
    int t = 1;
    while (t-- > 0) {
      solve();
    }
    out.flush();
    out.close();
  }

  // dp[mask][u] = number of ways to reach the node u such that the node in the
  // mask are already visited.

  static void solve() {
    int n = in.nextInt(), m = in.nextInt();
    List<Integer>[] graph = new ArrayList[n];
    for (int i = 0; i < n; i++)
      graph[i] = new ArrayList<>();

    for (int i = 0; i < m; i++) {
      int a = in.nextInt() - 1;
      int b = in.nextInt() - 1;
      graph[a].add(b);
    }

    long[][] dp = new long[1 << n][n];
    dp[1][0] = 1;
    for (int mask = 1; mask < (1 << n); mask++) {
      for (int u = 0; u < n; u++) {
        if ((mask & (1 << u)) == 0 || dp[mask][u] == 0)
          continue;

        for (int v : graph[u]) {
          if ((mask & (1 << v)) != 0)
            continue;
          dp[mask | (1 << v)][v] = (dp[mask | (1 << v)][v] + dp[mask][u]) % MOD;
        }
      }
    }
    out.println(dp[(1 << n) - 1][n - 1]);
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

    String nextLine() {
      try {
        st = null;
        return br.readLine();
      } catch (IOException e) {
        e.printStackTrace();
        return null;
      }
    }

    double nextDouble() {
      return Double.parseDouble(next());
    }
  }
}
