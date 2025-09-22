package Tree;

import java.io.*;
import java.util.*;

// cf link : https://codeforces.com/contest/2050/problem/Gs

public class TreeDestruction {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FASTIO in = new FASTIO();
  static long INF = Long.MAX_VALUE / 2;
  static int inf = Integer.MAX_VALUE / 2;
  static int mod = (int) 1e9 + 7;

  public static void main(String[] args) {
    int t = in.nextInt();
    while (t-- > 0) {
      solve();
    }
    out.flush();
    out.close();
  }

  static List<List<Integer>> adj;
  static int ans;

  static void solve() {
    int n = in.nextInt();
    ans = -inf;
    adj = new ArrayList<>();
    for (int i = 0; i <= n + 5; i++) {
      adj.add(new ArrayList<>());
    }
    int[] deg = new int[n + 5];
    for (int i = 1; i < n; i++) {
      int u = in.nextInt(), v = in.nextInt();
      adj.get(u).add(v);
      adj.get(v).add(u);
      deg[u]++;
      deg[v]++;
    }
    dfs(1, -1, deg);
    out.println(ans + 2); // this minus 2 is because at leaf there is not child node
  }

  public static int dfs(int node, int par, int[] deg) {
    int max1 = 0, max2 = 0;
    for (int adjNode : adj.get(node)) {
      if (adjNode == par)
        continue;
      int temp = dfs(adjNode, node, deg);
      if (temp > max1) {
        max2 = max1;
        max1 = temp;
      } else if (temp > max2) {
        max2 = temp;
      }
    }

    ans = Math.max(ans, max1 + max2 + deg[node] - 2);
    return Math.max(max1, max2) + deg[node] - 2;
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
