import java.io.*;
import java.util.*;

public class HLDLCA {
  static final long INF = (long) 1e18;
  static final int MOD = (int) 1e9 + 7;
  static final int inf = (int) 1e9;
  static PrintWriter out = new PrintWriter(System.out);
  static Scanner in = new Scanner(System.in);
  static int[] parent, heavy, size, depth, head, lTree, pos, value;
  static int index;
  static List<Integer>[] adj; // 1 to n

  public static void main(String[] args) {
    int t = in.nextInt();
    while (t-- > 0) {
      solve();
    }
    in.close();
    out.close();
  }

  static void dfs(int node, int par) {
    depth[node] = depth[par] + 1;
    parent[node] = par;
    size[node] = 1;
    for (int adjNode : adj[node]) {
      if (adjNode == par)
        continue;
      dfs(adjNode, node);
      size[node] += size[adjNode];
      if (size[adjNode] > size[heavy[node]]) {
        heavy[node] = adjNode;
      }
    }
  }

  static void dfsHLD(int node, int chain) {
    head[node] = chain;
    // lTree[index] = value[node];
    pos[node] = index; // it is store the position about where our node lies in the ltree arr.
    index++;
    if (heavy[node] != 0) {
      dfsHLD(heavy[node], chain);
    }
    for (int adjNode : adj[node]) {
      if (adjNode != heavy[node]) {
        dfsHLD(adjNode, adjNode);
      }
    }
  }

  static int LCA(int u, int v) {
    while (head[u] != head[v]) {
      if (depth[head[u]] < depth[head[v]]) {
        int temp = u;
        u = v;
        v = temp;
      }
      // means depth of head[u] > depth of head[v]
      u = parent[head[u]];
    }
    if (depth[u] < depth[v]) {
      return u;
    }
    return v;
  }

  static void solve() {
    int n = in.nextInt();
    adj = new ArrayList[n + 1];
    for (int i = 0; i <= n; i++) {
      adj[i] = new ArrayList<>();
    }
    size = new int[n + 1];
    parent = new int[n + 1];
    depth = new int[n + 1];
    heavy = new int[n + 1];
    head = new int[n + 1];
    lTree = new int[n + 1];
    pos = new int[n + 1];
    value = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      int m = in.nextInt();
      for (int j = 0; j < m; j++) {
        int node = in.nextInt();
        adj[i].add(node);
      }
    }
    // for (int i = 0; i < n; i++) {
    // value[i] = in.nextInt();
    // }
    index = 0;
    dfs(1, 0); // 1 based indexing
    dfsHLD(1, 1);

    int q = in.nextInt();
    while (q-- > 0) {
      int a = in.nextInt(), b = in.nextInt();
      out.println(LCA(a, b));
    }
  }
}
