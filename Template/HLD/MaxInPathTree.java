import java.io.*;
import java.util.*;

public class MaxInPathTree {
  static Scanner in = new Scanner(System.in);
  static PrintWriter out = new PrintWriter(System.out);
  static int[] pos, head, heavy, depth, size, parent;
  static long[] value, ltree;
  static List<Integer>[] adj;
  static int index;
  static long[] segTree;

  static void build(int node, int start, int end) {
    if (start == end) {
      segTree[node] = ltree[start];
      return;
    }
    int mid = (start + end) / 2;
    build(2 * node + 1, start, mid);
    build(2 * node + 2, mid + 1, end);
    segTree[node] = Math.max(segTree[2 * node + 1], segTree[2 * node + 2]);
  }

  static void update(int node, int start, int end, int idx, long val) {
    if (start == end) {
      segTree[node] = val;
      return;
    }
    int mid = (start + end) / 2;
    if (idx <= mid) {
      update(2 * node + 1, start, mid, idx, val);
    } else {
      update(2 * node + 2, mid + 1, end, idx, val);
    }
    segTree[node] = Math.max(segTree[2 * node + 1], segTree[2 * node + 2]);
  }

  static long query(int node, int start, int end, int l, int r) {
    if (l > end || r < start) {
      return 0;
    }
    if (l <= start && r >= end) {
      return segTree[node];
    }
    int mid = (start + end) / 2;
    return Math.max(query(2 * node + 1, start, mid, l, r), query(2 * node + 2, mid + 1, end, l, r));
  }

  static long pathQuery(int u, int v) {
    long res = Long.MIN_VALUE;

    while (head[u] != head[v]) {
      if (depth[head[u]] < depth[head[v]]) {
        int temp = u;
        u = v;
        v = temp;
      }
      int start = pos[head[u]];
      int end = pos[u];
      res = Math.max(res, query(0, 0, index - 1, start, end));

      u = parent[head[u]];
    }

    if (depth[u] > depth[v]) {
      int temp = u;
      u = v;
      v = temp;
    }
    res = Math.max(res, query(0, 0, index - 1, pos[u], pos[v]));
    return res;
  }

  public static void main(String[] hi) {
    int n = in.nextInt(), q = in.nextInt();
    segTree = new long[4 * n];
    index = 0;
    heavy = new int[n + 1];
    depth = new int[n + 1];
    size = new int[n + 1];
    value = new long[n + 1];
    pos = new int[n + 1];
    head = new int[n + 1];
    parent = new int[n + 1];
    ltree = new long[n + 1];
    adj = new ArrayList[n + 1];
    for (int i = 0; i <= n; i++) {
      adj[i] = new ArrayList<>();
    }
    for (int i = 1; i <= n; i++) {
      value[i] = in.nextLong();
    }
    for (int i = 1; i < n; i++) {
      int a = in.nextInt(), b = in.nextInt();
      adj[a].add(b);
      adj[b].add(a);
    }

    dfs(1, 0); // caculate the size and the depth arr.
    hld(1, 1); // calculte the heavy, head, pos, ltree
    build(0, 0, index - 1);

    for (int i = 0; i < q; i++) {
      int type = in.nextInt();
      if (type == 1) {
        int s = in.nextInt();
        long x = in.nextLong();
        update(0, 0, index - 1, pos[s], x);
      } else {
        int a = in.nextInt(), b = in.nextInt();
        out.print(pathQuery(a, b) + " ");
      }
    }
    in.close();
    out.close();
  }

  static void hld(int node, int chain) {
    head[node] = chain;
    ltree[index] = value[node];
    pos[node] = index;
    index++;

    if (heavy[node] != 0) {
      hld(heavy[node], chain);
    }

    for (int adjNode : adj[node]) {
      if (adjNode != heavy[node] && adjNode != parent[node]) {
        hld(adjNode, adjNode);
      }
    }
  }

  static void dfs(int node, int par) {
    depth[node] = 1 + depth[par];
    size[node] = 1;
    parent[node] = par;
    for (int adjNode : adj[node]) {
      if (adjNode != par) {
        dfs(adjNode, node);
        size[node] += size[adjNode];
        if (size[adjNode] > size[heavy[node]]) {
          heavy[node] = adjNode;
        }
      }
    }
  }
}
