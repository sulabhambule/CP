
import java.io.*;
import java.util.*;

// Cf link : https://codeforces.com/contest/1691/problem/D
// in this problem we are using the segment tree to find max > min prefix in the range L <---> R 

public class MaxPrefixSeg {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FASTIO in = new FASTIO();
  static final int inf = (int) 1e9;
  static final long INF = (long) 1e18;

  public static void main(String[] args) {
    int t = in.nextInt();
    while (t-- > 0)
      solve();
    out.close();
  }

  static void solve() {
    int n = in.nextInt();
    long[] a = new long[n];
    for (int i = 0; i < n; i++)
      a[i] = in.nextLong();

    // prev[i] = previous greater element index
    // next[i] = next greater element index
    int[] prev = new int[n];
    int[] next = new int[n];
    Arrays.fill(prev, -1);
    Arrays.fill(next, n);

    Stack<Integer> st = new Stack<>();

    for (int i = 0; i < n; i++) {
      while (!st.isEmpty() && a[st.peek()] <= a[i])
        st.pop();
      prev[i] = st.isEmpty() ? -1 : st.peek();
      st.push(i);
    }

    st.clear();

    for (int i = n - 1; i >= 0; i--) {
      while (!st.isEmpty() && a[st.peek()] <= a[i])
        st.pop();
      next[i] = st.isEmpty() ? n : st.peek();
      st.push(i);
    }

    long[] prefix = new long[n + 1];
    for (int i = 0; i < n; i++)
      prefix[i + 1] = prefix[i] + a[i];

    SegTree sgt = new SegTree(prefix);
    // we are buildig the sement tree on the prefix and the query is gonna be give
    // us min/max prefix in given range.

    boolean ok = true;
    for (int i = 0; i < n; i++) {
      int l = prev[i] + 1;
      int r = next[i];

      // max sum ending at i
      long maxLeft = prefix[i + 1] - sgt.query(l, i).minVal;

      // max sum starting at i
      long maxRight = sgt.query(i + 1, r).maxVal - prefix[i];

      long maxSum = Math.max(maxLeft, maxRight);

      if (maxSum > a[i]) {
        ok = false;
        break;
      }
    }

    out.println(ok ? "YES" : "NO");
  }

  static class SegTree {
    int n;
    Node[] tree;

    public SegTree(long[] a) {
      this.n = a.length;
      tree = new Node[4 * n];
      build(a, 0, n - 1, 0);
    }

    public void build(long[] a, int s, int e, int node) {
      if (s == e) {
        // reached to the leaf node
        tree[node] = new Node(a[s], a[s]);
        return;
      }

      int m = (s + e) >> 1;
      build(a, s, m, 2 * node + 1);
      build(a, m + 1, e, 2 * node + 2);
      tree[node] = new Node(tree[2 * node + 1], tree[2 * node + 2]);
    }

    public Node query(int l, int r) {
      return query(0, n - 1, l, r, 0);
    }

    public Node query(int s, int e, int l, int r, int node) {
      if (l > e || r < s) {
        // out of the range
        return new Node(-INF, INF);
      }

      if (s >= l && e <= r) {
        // totaly inside the range so return the node value
        return tree[node];
      }

      int m = (s + e) >> 1;
      Node left = query(s, m, l, r, 2 * node + 1);
      Node right = query(m + 1, e, l, r, 2 * node + 2);
      return new Node(left, right);
    }
  }

  static class Node {
    long maxVal, minVal;

    Node(long maxVal, long minVal) {
      this.maxVal = maxVal;
      this.minVal = minVal;
    }

    Node(Node a, Node b) {
      this.maxVal = Math.max(a.maxVal, b.maxVal);
      this.minVal = Math.min(a.minVal, b.minVal);
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
