import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Simple {
     public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
     static FASTIO in = new FASTIO();
     static int MOD = (int) 1e9 + 7;

     public static void main(String[] args) throws IOException {
          int t = 1;
          while (t-- > 0) {
               solve();
          }
          out.close();
     }

     static void solve() {
          int n = in.nextInt();
          int m = in.nextInt();

          LazySeg tree = new LazySeg();
          tree.init(n);
          long[] a = new long[n];
          tree.build(0, n - 1, 0, a);

          for (int i = 0; i < m; i++) {
               int type = in.nextInt();
               if (type == 1) {
                    int l = in.nextInt();
                    int r = in.nextInt();
                    long val = in.nextLong();
                    tree.update(0, n - 1, l, r - 1, 0, val);
               } else {
                    int l = in.nextInt();
                    int r = in.nextInt();
                    out.println(tree.query(0, n - 1, l, r - 1, 0));
               }
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
     }
}

class LazySeg {
     private int n;
     public long[] lazy;
     public long[] st;

     public void init(int n) {
          this.n = n;
          lazy = new long[4 * n];
          Arrays.fill(lazy, -1);
          st = new long[4 * n];
     }

     public long combine(long a, long b) {
          return (a + b);
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

     public void push(int start, int end, int node) {
          if (lazy[node] != -1) {
               st[node] = lazy[node] * (end - start + 1);
               if (start != end) {
                    lazy[2 * node + 1] = lazy[node];
                    lazy[2 * node + 2] = lazy[node];
               }
               lazy[node] = -1;
          }
     }

     public void update(int start, int end, int l, int r, int node, long value) {
          push(start, end, node);
          if (start > r || end < l) {
               return;
          }
          if (start >= l && end <= r) {
               lazy[node] = value;
               push(start, end, node);
               return;
          }
          int mid = (start + end) / 2;
          update(start, mid, l, r, 2 * node + 1, value);
          update(mid + 1, end, l, r, 2 * node + 2, value);
          st[node] = combine(st[2 * node + 1], st[2 * node + 2]);
     }

     public long query(int start, int end, int l, int r, int node) {
          push(start, end, node);
          if (start > r || end < l) {
               return 0;
          }
          if (start >= l && end <= r) {
               return st[node];
          }
          int mid = (start + end) / 2;
          long q1 = query(start, mid, l, r, 2 * node + 1);
          long q2 = query(mid + 1, end, l, r, 2 * node + 2);
          return combine(q1, q2);
     }
}