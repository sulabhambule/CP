
import java.io.*;
import java.util.*;

public class euler_tour {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FASTIO in = new FASTIO();
  static int MOD = (int) 1e9 + 7;
  static List<Integer>[] adj;
  static int time = 0;

  public static void main(String[] args) throws IOException {
    int t = 1;
    while (t-- > 0) {
      solve();
    }
    out.close();
  }

  static void solve() {
    int n = in.nextInt();
    int q = in.nextInt();
    long[] v = new long[n];
    for (int i = 0; i < n; i++) {
      v[i] = in.nextLong();
    }

    adj = new ArrayList[n + 1];
    for (int i = 0; i <= n; i++) {
      adj[i] = new ArrayList<>();
    }

    for (int i = 0; i < n - 1; i++) {
      int a = in.nextInt();
      int b = in.nextInt();
      adj[a].add(b);
      adj[b].add(a);
    }

    long[] euler = new long[2 * n];
    int[] inTime = new int[n + 1];
    int[] outTime = new int[n + 1];

    dfs(1, -1, inTime, outTime);

    for (int i = 1; i <= n; i++) {
      euler[inTime[i]] = v[i - 1];
      euler[outTime[i]] = -v[i - 1];
    }

    SegTree seg = new SegTree();
    seg.init(2 * n); // Euler array size
    seg.build(0, 2 * n - 1, 0, euler);

    while (q-- > 0) {
      int type = in.nextInt();
      if (type == 1) {
        int s = in.nextInt();
        long x = in.nextLong();
        seg.update(0, 2 * n - 1, inTime[s], 0, x);
        seg.update(0, 2 * n - 1, outTime[s], 0, -x);
      } else {
        int s = in.nextInt();
        out.println(seg.query(0, 2 * n - 1, 0, inTime[s], 0));
      }
    }
  }

  private static void dfs(int node, int parent, int[] inTime, int[] outTime) {
    inTime[node] = time++;
    for (int adjNode : adj[node]) {
      if (adjNode != parent) {
        dfs(adjNode, node, inTime, outTime);
      }
    }
    outTime[node] = time++;
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
  }
}

class SegTree {
  private int n;
  public long[] st;

  public void init(int n) {
    this.n = n;
    st = new long[4 * n];
  }

  public long combine(long a, long b) {
    return a + b;
  }

  public void build(int start, int end, int node, long[] v) {
    if (start == end) {
      st[node] = v[start];
      return;
    }
    int mid = (start + end) / 2;
    build(start, mid, 2 * node + 1, v);
    build(mid + 1, end, 2 * node + 2, v);
    st[node] = combine(st[2 * node + 1], st[2 * node + 2]);
  }

  public void update(int start, int end, int idx, int node, long value) {
    if (start == end) {
      st[node] = value;
      return;
    }
    int mid = (start + end) / 2;
    if (idx <= mid) {
      update(start, mid, idx, 2 * node + 1, value);
    } else {
      update(mid + 1, end, idx, 2 * node + 2, value);
    }
    st[node] = combine(st[2 * node + 1], st[2 * node + 2]);
  }

  public long query(int start, int end, int l, int r, int node) {
    if (start > r || end < l)
      return 0;
    if (start >= l && end <= r)
      return st[node];

    int mid = (start + end) / 2;
    long q1 = query(start, mid, l, r, 2 * node + 1);
    long q2 = query(mid + 1, end, l, r, 2 * node + 2);
    return combine(q1, q2);
  }
}
