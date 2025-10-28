package Tree;

import java.io.*;
import java.util.*;

// codeforces link : https://codeforces.com/problemset/problem/1760/G

public class xorEqualZeroTree {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FASTIO in = new FASTIO();
  static long INF = Long.MAX_VALUE / 2;
  static int inf = Integer.MAX_VALUE / 2;
  static int mod = (int) 1e9 + 7;
  // static int mod = 998244353;

  public static void main(String[] args) {
    int t = in.nextInt();
    while (t-- > 0) {
      solve();
    }
    out.flush();
    out.close();
  }

  static List<List<int[]>> adj;
  static HashSet<Integer> set;

  static void solve() {
    set = new HashSet<>();
    int n = in.nextInt();
    int a = in.nextInt(), b = in.nextInt();
    adj = new ArrayList<>();
    for (int i = 0; i <= n; i++)
      adj.add(new ArrayList<>());
    for (int i = 0; i < n - 1; i++) {
      int u = in.nextInt(), v = in.nextInt(), w = in.nextInt();
      adj.get(u).add(new int[] { v, w });
      adj.get(v).add(new int[] { u, w });
    }
    dfs1(a, -1, 0, b);
    if (dfs2(b, -1, 0, b))
      out.println("YES");
    else
      out.println("NO");
  }

  static void dfs1(int node, int par, int xor, int b) {
    if (node == b)
      return;
    set.add(xor);
    for (int[] e : adj.get(node)) {
      int adjNode = e[0], cost = e[1];
      if (adjNode == par)
        continue;
      dfs1(adjNode, node, xor ^ cost, b);
    }
  }

  static boolean dfs2(int node, int par, int xor, int b) {
    if (node != b && set.contains(xor))
      return true;
    for (int[] e : adj.get(node)) {
      int adjNode = e[0], cost = e[1];
      if (adjNode == par)
        continue;
      if (dfs2(adjNode, node, xor ^ cost, b))
        return true;
    }
    return false;
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
