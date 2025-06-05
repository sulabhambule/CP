import java.io.*;
import java.util.*;

public class Simple {
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static List<Edge>[] adj;
  static int[] depth, heavy, size, head, linear_tree, segTree, parent, edgeToNode, value, pos;
  static int index;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(System.out);

    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      index = 0;
      int n = Integer.parseInt(br.readLine());

      depth = new int[n + 1];
      heavy = new int[n + 1];
      parent = new int[n + 1];
      size = new int[n + 1];
      value = new int[n + 1];
      linear_tree = new int[n + 1];
      head = new int[n + 1];
      edgeToNode = new int[n];
      pos = new int[n + 1];
      adj = new ArrayList[n + 1];
      for (int i = 0; i <= n; i++)
        adj[i] = new ArrayList<>();
      segTree = new int[4 * n];

      for (int i = 1; i < n; i++) {
        st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int u = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        adj[v].add(new Edge(v, u, c, i));
        adj[u].add(new Edge(u, v, c, i));
      }

      dfs(1, 0);
      hld(1, 1);
      build(0, 0, index - 1);

      while (true) {
        String line = br.readLine();
        if (line.equals("DONE"))
          break;

        st = new StringTokenizer(line);
        String cmd = st.nextToken();
        if (cmd.equals("CHANGE")) {
          int iEdge = Integer.parseInt(st.nextToken());
          int ti = Integer.parseInt(st.nextToken());
          int node = edgeToNode[iEdge];
          update(0, 0, index - 1, pos[node], ti);
        } else { // QUERY
          int a = Integer.parseInt(st.nextToken());
          int b = Integer.parseInt(st.nextToken());
          out.println(pathQuery(a, b));
        }
      }
      out.println();
    }
    out.flush();
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
    res = Math.max(res, query(0, 0, index - 1, pos[a], pos[b]));
    return res;
  }

  static void dfs(int node, int par) {
    size[node] = 1;
    parent[node] = par;
    depth[node] = depth[par] + 1;
    for (Edge e : adj[node]) {
      edgeToNode[e.i] = e.neighbor;
      if (e.neighbor != par) {
        value[e.neighbor] = e.c;
        dfs(e.neighbor, node);
        size[node] += size[e.neighbor];
        if (size[e.neighbor] > size[heavy[node]]) {
          heavy[node] = e.neighbor;
        }
      }
    }
  }

  static void hld(int node, int chain) {
    head[node] = chain;
    linear_tree[index] = value[node];
    pos[node] = index++;
    if (heavy[node] != 0) {
      hld(heavy[node], chain);
    }
    for (Edge e : adj[node]) {
      if (e.neighbor != heavy[node] && e.neighbor != parent[node]) {
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