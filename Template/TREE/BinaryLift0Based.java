
import java.io.*;
import java.util.*;

/*
 * parent[node][i] = parent[parent[node][i - 1]][i - 1];
 * This means that the 2^i-th parent of a node is
 * the 2^(i-1)-th parent of its 2^(i-1)-th parent
 */

public class BinaryLift0Based {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FastReader in = new FastReader();
  private static final int MOD = (int) 1e9 + 7;
  private static final int MAX_LOG = 20;

  private static int[] depth;

  public static void main(String[] args) {
    int T = 1;
    while (T-- > 0) {
      solve();
    }
    out.close();
  }

  private static void solve() {
    int n = in.nextInt();
    int q = in.nextInt();

    List<List<Integer>> adj = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      adj.add(new ArrayList<>());
    }

    for (int i = 1; i < n; i++) {
      int parent = in.nextInt();
      adj.get(parent).add(i);
      adj.get(i).add(parent);
    }

    int[][] par = new int[n][MAX_LOG];
    depth = new int[n];
    Arrays.fill(depth, -1);

    dfs(0, -1, adj, par); // root = 0, parent = -1

    for (int i = 0; i < q; i++) {
      int type = in.nextInt();
      if (type == 1) {
        // kth parent query
        int x = in.nextInt();
        int k = in.nextInt();
        int parent = KthParent(x, k, par);
        out.println(parent == -1 ? -1 : parent);
      } else if (type == 2) {
        // lca query
        int u = in.nextInt();
        int v = in.nextInt();
        out.println(lca(u, v, par));
      }
    }
  }

  private static void dfs(int node, int parent, List<List<Integer>> adj, int[][] par) {
    par[node][0] = parent;
    for (int j = 1; j < MAX_LOG; j++) {
      if (par[node][j - 1] != -1) {
        par[node][j] = par[par[node][j - 1]][j - 1];
      } else {
        par[node][j] = -1;
      }
    }
    if (parent == -1)
      depth[node] = 0;
    else
      depth[node] = depth[parent] + 1;

    for (int adjNode : adj.get(node)) {
      if (adjNode != parent) {
        dfs(adjNode, node, adj, par);
      }
    }
  }

  static int KthParent(int node, int k, int[][] par) {
    for (int i = MAX_LOG - 1; i >= 0; i--) {
      if (((1 << i) & k) != 0) {
        node = par[node][i];
        if (node == -1)
          return -1;
      }
    }
    return node;
  }

  static int lca(int u, int v, int[][] par) {
    if (depth[u] < depth[v]) {
      int tmp = u;
      u = v;
      v = tmp;
    }

    u = KthParent(u, depth[u] - depth[v], par);

    if (u == v)
      return u;

    for (int i = MAX_LOG - 1; i >= 0; i--) {
      if (par[u][i] != -1 && par[u][i] != par[v][i]) {
        u = par[u][i];
        v = par[v][i];
      }
    }

    return par[u][0];
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
  }
}
