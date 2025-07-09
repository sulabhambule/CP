import java.io.*;
import java.util.*;

public class MaxEdgeCostOnPath {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FASTIO in = new FASTIO();
  static List<Edge>[] adj;
  static int[] depth, heavy, size, head, linear_tree, segTree, parent, edgeToNode, value, pos;
  static int index;

  public static void main(String[] hi) {
    int t = in.nextInt();
    int p = 1;
    while (t-- > 0) {
      index = 0;
      int n = in.nextInt();
      depth = new int[n + 1];
      heavy = new int[n + 1];
      Arrays.fill(heavy, -1); // Initialize heavy to -1
      parent = new int[n + 1];
      size = new int[n + 1];
      value = new int[n + 1];
      linear_tree = new int[n + 1];
      head = new int[n + 1];
      edgeToNode = new int[n];
      pos = new int[n + 1];
      adj = new ArrayList[n + 1];
      for (int i = 0; i <= n; i++) {
        adj[i] = new ArrayList<>();
      }
      segTree = new int[4 * n];

      for (int i = 1; i < n; i++) {
        int v = in.nextInt(), u = in.nextInt(), c = in.nextInt();
        adj[v].add(new Edge(v, u, c, i));
        adj[u].add(new Edge(u, v, c, i));
      }

      dfs(1, 0);
      hld(1, 1);

      build(0, 0, index - 1);

      while (true) {
        String cmd = in.next();
        if (cmd.equals("DONE"))
          break;

        if (cmd.equals("CHANGE")) {
          int iEdge = in.nextInt();
          int ti = in.nextInt();
          int node = edgeToNode[iEdge];
          update(0, 0, index - 1, pos[node], ti);
        } else if (cmd.equals("QUERY")) {
          int a = in.nextInt(), b = in.nextInt();
          out.println(pathQuery(a, b));
        }
      }

      // If input has blank lines between test cases, consume here:
      if (p > 1 && t != 1) {
        String blankLine = in.nextLine(); // if needed
      }
    }
    out.flush();
    out.close();
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
        st = null; // clear the tokenizer so next() doesn't use old tokens
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

  static void build(int node, int start, int end) {
    if (start == end) {
      segTree[node] = linear_tree[start];
      return;
    }

    int mid = (start + end) / 2;
    build(2 * node + 1, start, mid);
    build(2 * node + 2, mid + 1, end);
    segTree[node] = Math.max(segTree[2 * node + 1], segTree[2 * node + 2]);
  }

  static void update(int node, int start, int end, int idx, int val) {
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

  static int query(int node, int start, int end, int l, int r) {
    if (l > end || r < start)
      return Integer.MIN_VALUE;
    if (l <= start && end <= r)
      return segTree[node];

    int mid = (start + end) / 2;
    return Math.max(query(2 * node + 1, start, mid, l, r), query(2 * node + 2, mid + 1, end, l, r));
  }

  static int pathQuery(int a, int b) {
    int res = Integer.MIN_VALUE;
    while (head[a] != head[b]) {
      if (depth[head[a]] > depth[head[b]]) {
        int temp = a;
        a = b;
        b = temp;
      }
      int start = pos[head[b]], end = pos[b];
      res = Math.max(res, query(0, 0, index - 1, start, end));
      b = parent[head[b]];
    }
    if (depth[a] > depth[b]) {
      int temp = a;
      a = b;
      b = temp;
    }
    // NOTE: pos[a]+1 because edge values are stored at child nodes
    if (pos[a] + 1 <= pos[b])
      res = Math.max(res, query(0, 0, index - 1, pos[a] + 1, pos[b]));
    return res;
  }

  static void dfs(int node, int par) {
    size[node] = 1;
    parent[node] = par;
    depth[node] = depth[par] + 1;
    int maxSubtreeSize = 0;
    for (Edge e : adj[node]) {
      if (e.neighbor != par) {
        edgeToNode[e.i] = e.neighbor;
        value[e.neighbor] = e.c;
        dfs(e.neighbor, node);
        size[node] += size[e.neighbor];
        if (size[e.neighbor] > maxSubtreeSize) {
          maxSubtreeSize = size[e.neighbor];
          heavy[node] = e.neighbor;
        }
      }
    }
  }

  static void hld(int node, int chain) {
    head[node] = chain;
    linear_tree[index] = value[node];
    pos[node] = index++;
    if (heavy[node] != -1) {
      hld(heavy[node], chain);
    }
    for (Edge e : adj[node]) {
      if (e.neighbor != parent[node] && e.neighbor != heavy[node]) {
        hld(e.neighbor, e.neighbor);
      }
    }
  }

  static class Edge {
    int source, neighbor, i, c;

    public Edge(int vv, int uu, int cc, int ii) {
      source = vv;
      neighbor = uu;
      c = cc;
      i = ii;
    }
  }
}
