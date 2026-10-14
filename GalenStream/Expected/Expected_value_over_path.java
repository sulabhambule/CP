package GalenStream.Expected;

import java.io.*;
import java.util.*;

// quesition link : https://codeforces.com/gym/304970/problem/B

public class Expected_value_over_path {
  static final long INF = Long.MAX_VALUE / 2;
  static FastReader in = new FastReader();
  static PrintWriter out = new PrintWriter(System.out);
  static int mod = (int) 1e9 + 7;

  public static void main(String[] args) throws IOException {
    int t = 1;
    while (t-- > 0) {
      solve();
    }
    out.close();
  }

  static List<List<Integer>> adj;

  static void solve() {
    int n = in.nextInt();
    adj = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      adj.add(new ArrayList<>());
    }
    for (int i = 0; i < n - 1; i++) {
      int ui = in.nextInt(), vi = in.nextInt();
      adj.get(ui).add(vi);
      adj.get(vi).add(ui);
    }
    double ans = dfs(1, 0);
    out.println(ans);
  }

  // E(node) = 1 + (sum(E(children)) / noOfChild)

  static double dfs(int node, int parent) {
    int child = 0;
    double val = 0;

    for (int adjNode : adj.get(node)) {
      if (adjNode == parent)
        continue;
      child++;
      val += dfs(adjNode, node);
    }
    if (child == 0)
      return 0;
    return 1 + (val / child);

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
