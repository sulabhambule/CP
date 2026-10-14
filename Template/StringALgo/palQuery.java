import java.io.*;
import java.util.*;

// problem link : https://cses.fi/problemset/task/2420

public class palQuery {
  static FastReader in = new FastReader();
  static PrintWriter out = new PrintWriter(System.out);

  public static void main(String[] args) throws IOException {
    solve();
    out.close();
  }

  static final long mod1 = 1_000_000_007;
  static final long mod2 = 1_000_000_009;
  static final long base = 91138233;

  static class Node {
    long h1, h2, rh1, rh2;
    int len;

    Node(long h1, long h2, long rh1, long rh2, int len) {

      this.h1 = h1;
      this.h2 = h2;
      this.rh1 = rh1;
      this.rh2 = rh2;
      this.len = len;
    }

    Node() {
      this(0, 0, 0, 0, 0);
    }
  }

  static Node[] seg;
  static long[] pow1, pow2;
  static String s;

  static void solve() {
    int n = in.nextInt(), m = in.nextInt();
    s = in.next();
    seg = new Node[4 * n];
    for (int i = 0; i < seg.length; i++) {
      seg[i] = new Node();
    }
    pow1 = new long[n + 1];
    pow2 = new long[n + 1];
    pow1[0] = pow2[0] = 1;
    for (int i = 0; i < n; i++) {
      pow1[i + 1] = (pow1[i] * base) % mod1;
      pow2[i + 1] = (pow2[i] * base) % mod2;
    }
    build(0, n - 1, 0);
    for (int i = 0; i < m; i++) {
      int type = in.nextInt();
      if (type == 1) {
        int k = in.nextInt() - 1;
        char x = in.next().charAt(0);
        update(0, 0, n - 1, k, x);
      } else {
        int a = in.nextInt() - 1, b = in.nextInt() - 1;
        Node res = query(0, n - 1, 0, a, b);
        if (res.h1 == res.rh1 && res.h2 == res.rh2) {
          out.println("YES");
        } else {
          out.println("NO");
        }
      }
    }
  }

  static void build(int start, int end, int node) {
    if (start == end) {
      int val = s.charAt(start) - 'a' + 1;
      seg[node] = new Node(val, val, val, val, 1);
      return;
    }

    int mid = (start + end) / 2;
    build(start, mid, 2 * node + 1);
    build(mid + 1, end, 2 * node + 2);
    seg[node] = merge(seg[2 * node + 1], seg[2 * node + 2]);
  }

  static Node merge(Node left, Node right) {
    Node res = new Node();
    res.len = left.len + right.len;
    res.h1 = (left.h1 * pow1[right.len] + right.h1) % mod1;
    res.h2 = (left.h2 * pow2[right.len] + right.h2) % mod2;

    res.rh1 = (right.rh1 * pow1[left.len] + left.rh1) % mod1;
    res.rh2 = (right.rh2 * pow2[left.len] + left.rh2) % mod2;

    return res;
  }

  static void update(int node, int start, int end, int pos, char ch) {
    if (start == end) {
      int val = ch - 'a' + 1;
      seg[node] = new Node(val, val, val, val, 1);
      return;
    }

    int mid = (start + end) / 2;
    if (pos <= mid) {
      update(2 * node + 1, start, mid, pos, ch);
    } else {
      update(2 * node + 2, mid + 1, end, pos, ch);
    }
    seg[node] = merge(seg[2 * node + 1], seg[2 * node + 2]);
  }

  static Node query(int start, int end, int node, int l, int r) {
    if (r < start || l > end) {
      return new Node();
    }
    if (l <= start && r >= end)
      return seg[node];
    int mid = (start + end) / 2;
    Node left = query(start, mid, 2 * node + 1, l, r);
    Node right = query(mid + 1, end, 2 * node + 2, l, r);
    return merge(left, right);
  }

  static class FastReader {
    BufferedReader br;
    StringTokenizer st;

    public FastReader() {
      br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
      while (st == null || !st.hasMoreTokens()) {
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException e) {
          throw new RuntimeException(e);
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
