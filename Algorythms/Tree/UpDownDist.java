
import java.io.*;
import java.util.*;

// cf link : https://codeforces.com/contest/1822/problem/F

// here we are finding the max dist in subTree in down1[node] and second max leaf dist in down2[node], also the up[node] = max dist not in the subTree. and the heavy[node] give in subTree frim which node the max distance means down1[node] is comming . 

public class UpDownDist {
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

  static List<List<Integer>> adj;
  static int[] depth, parent, down1, down2, heavy, up;

  static void solve() {
    int n = in.nextInt();
    long k = in.nextLong(), c = in.nextLong();

    depth = new int[n + 1];
    adj = new ArrayList<>();
    for (int i = 0; i <= n; i++)
      adj.add(new ArrayList<>());
    for (int i = 1; i < n; i++) {
      int u = in.nextInt(), v = in.nextInt();
      adj.get(u).add(v);
      adj.get(v).add(u);
    }
    depth = new int[n + 1];
    down1 = new int[n + 1];
    down2 = new int[n + 1];
    heavy = new int[n + 1];
    up = new int[n + 1];
    parent = new int[n + 1];
    dfsDepth(1, -1);
    dfsDown(1, -1);
    up[1] = 0;
    dfsUp(1, -1);
    long ans = -INF;
    for (int node = 1; node <= n; node++) {
      long curr = k * (long) Math.max(down1[node], up[node]) - c * (long) depth[node];
      ans = Math.max(ans, curr);
    }
    out.println(ans);
  }

  static void dfsUp(int node, int par) {
    for (int adjNode : adj.get(node)) {
      if (adjNode == par)
        continue;
      int curr = (heavy[node] == adjNode ? down2[node] : down1[node]);
      up[adjNode] = 1 + Math.max(up[node], curr);
      dfsUp(adjNode, node);
    }
  }

  static void dfsDown(int node, int p) {
    down1[node] = down2[node] = 0;
    heavy[node] = -1;
    for (int adjNode : adj.get(node)) {
      if (adjNode == p)
        continue;
      dfsDown(adjNode, node);
      int curr = 1 + down1[adjNode];
      if (curr > down1[node]) {
        down2[node] = down1[node];
        down1[node] = curr;
        heavy[node] = adjNode;
      } else if (curr > down2[node]) {
        down2[node] = curr;
      }
    }
  }

  static void dfsDepth(int node, int p) {
    parent[node] = p;
    for (int adjNode : adj.get(node)) {
      if (adjNode == p)
        continue;
      depth[adjNode] = 1 + depth[node];
      dfsDepth(adjNode, node);
    }
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
