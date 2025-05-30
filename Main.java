import java.io.*;
import java.util.*;

public class Main {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FASTIO in = new FASTIO();
  static List<Integer>[] adj;
  static int[] color;
  static int[] inTime, outTime;
  static int timer = 0;
  static int n;

  public static void main(String[] args) throws IOException {
    n = in.nextInt();
    color = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      color[i] = in.nextInt();
    }
    adj = new ArrayList[n + 1];
    for (int i = 0; i <= n; i++)
      adj[i] = new ArrayList<>();
    for (int i = 0; i < n - 1; i++) {
      int u = in.nextInt(), v = in.nextInt();
      adj[u].add(v);
      adj[v].add(u);
    }
    inTime = new int[n + 1];
    outTime = new int[n + 1];
    dfs(1, 0);

    // Prepare queries: for each node, we want distinct count in [inTime[node],
    // outTime[node]]
    // We'll process times 0 to 2*n-1, update last occurrence of color
    int size = 2 * n;
    Query[] queries = new Query[n];
    for (int i = 1; i <= n; i++) {
      queries[i - 1] = new Query(inTime[i], outTime[i], i);
    }
    Arrays.sort(queries, Comparator.comparingInt(qr -> qr.r));

    SegTree seg = new SegTree();
    seg.init(size);
    // last occurrence map
    Map<Integer, Integer> last = new HashMap<>();
    int idx = 0;
    int[] answer = new int[n + 1];
    for (int t = 0; t < size; t++) {
      // at time t, find which node appears entering or exiting
      int nodeAtTime = nodeAt[t];
      if (nodeAtTime != 0 && (inTime[nodeAtTime] == t)) {
        int c = color[nodeAtTime];
        // remove old
        if (last.containsKey(c)) {
          seg.update(0, size - 1, last.get(c), 0, 0);
        }
        // add new
        seg.update(0, size - 1, t, 0, 1);
        last.put(c, t);
      }
      // answer queries with r == t
      while (idx < n && queries[idx].r == t) {
        answer[queries[idx].id] = seg.query(0, size - 1, queries[idx].l, queries[idx].r, 0);
        idx++;
      }
    }

    for (int i = 1; i <= n; i++) {
      out.print(answer[i]);
      out.print(i < n ? ' ' : '\n');
    }
    out.close();
  }

  // Euler tour to assign in and out times and record node at each time
  static int[] nodeAt;

  private static void dfs(int u, int p) {
    if (nodeAt == null)
      nodeAt = new int[2 * n];
    inTime[u] = timer;
    nodeAt[timer++] = u;
    for (int v : adj[u]) {
      if (v == p)
        continue;
      dfs(v, u);
    }
    outTime[u] = timer;
    nodeAt[timer++] = 0; // no entry on exit
  }

  static class Query {
    int l, r, id;

    Query(int l, int r, int id) {
      this.l = l;
      this.r = r;
      this.id = id;
    }
  }

  static class SegTree {
    private int n;
    public int[] st;

    public void init(int n) {
      this.n = n;
      st = new int[4 * n];
    }

    public void update(int start, int end, int idx, int node, int value) {
      if (start == end) {
        st[node] = value;
        return;
      }
      int mid = (start + end) >> 1;
      if (idx <= mid)
        update(start, mid, idx, 2 * node + 1, value);
      else
        update(mid + 1, end, idx, 2 * node + 2, value);
      st[node] = st[2 * node + 1] + st[2 * node + 2];
    }

    public int query(int start, int end, int l, int r, int node) {
      if (start > r || end < l)
        return 0;
      if (start >= l && end <= r)
        return st[node];
      int mid = (start + end) >> 1;
      return query(start, mid, l, r, 2 * node + 1)
          + query(mid + 1, end, l, r, 2 * node + 2);
    }
  }

  static class FASTIO {
    BufferedReader br;
    StringTokenizer st;

    FASTIO() {
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
  }
}