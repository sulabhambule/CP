import java.util.*;

public class LCA1Based {
  static List<int[]>[] adj;
  static int[][] up;
  static int[] depth;
  static int LOG;

  public static void main(String[] args) {
    int n = 10000;
    depth = new int[n];

    dfs(0, -1);
    for (int j = 1; j < LOG; j++) {
      for (int i = 0; i < n; i++) {
        if (up[i][j - 1] != -1)
          up[i][j] = up[up[i][j - 1]][j - 1];
      }
    }
  }

  static void dfs(int node, int parent) {
    up[node][0] = parent == -1 ? node : parent;
    for (int[] nei : adj[node]) {
      int next = nei[0], w = nei[1];
      if (next == parent)
        continue;
      depth[next] = depth[node] + 1;
      dfs(next, node);
    }
  }

  static int LCA(int u, int v) {
    if (depth[u] < depth[v]) {
      int t = u;
      u = v;
      v = t;
    }

    int diff = depth[u] - depth[v];
    for (int i = LOG - 1; i >= 0; i--) {
      if (((1 << i) & diff) != 0)
        u = up[u][i];
    }

    if (u == v)
      return u;

    for (int i = LOG - 1; i >= 0; i--) {
      if (up[u][i] != -1 && up[u][i] != up[v][i]) {
        u = up[u][i];
        v = up[v][i];
      }
    }

    return up[u][0];
  }
}