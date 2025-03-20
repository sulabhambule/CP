import java.io.*;
import java.util.*;

public class Simple {
     public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
     static FASTIO in = new FASTIO();
     static final int MOD = 998244353;

     public static void main(String[] Hi) throws IOException {
          int cp = 1;
          while (cp-- > 0) {
               solve();
          }
          out.close();
     }

     static void solve() {

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
     }
}

class LazySimple {
     private int n;
     private int[] st;
     private int[] lazy;

     public void init(int _n) {
          this.n = _n;
          st = new int[4 * n];
          lazy = new int[4 * n];
     }

     private int combine(int a, int b) {
          return a ^ b;
     }

     private void push(int start, int end, int node) {
          if (lazy[node] != 0) {
               int len = end - start + 1;
               if (len % 2 == 0) {
                    st[node] = 0;
               } else {
                    st[node] = lazy[node];
               }
               if (start != end) {
                    lazy[2 * node + 1] = lazy[node];
                    lazy[2 * node + 2] = lazy[node];
               }
               lazy[node] = 0;
          }
     }

     private void build(int start, int end, int node, int[] v) {
          if (start == end) {
               st[node] = v[start];
               return;
          }
          int mid = (start + end) / 2;
          build(start, mid, 2 * node + 1, v);
          build(mid + 1, end, 2 * node + 2, v);
          st[node] = combine(st[2 * node + 1], st[2 * node + 2]);
     }

     private int query(int start, int end, int l, int r, int node) {
          push(start, end, node);
          if (start > r || end < l)
               return 0;
          if (start >= l && end <= r)
               return st[node];
          int mid = (start + end) / 2;
          int q1 = query(start, mid, l, r, 2 * node + 1);
          int q2 = query(mid + 1, end, l, r, 2 * node + 2);
          return combine(q1, q2);
     }

     private void update(int start, int end, int node, int l, int r, int value) {
          push(start, end, node);
          if (start > r || end < l)
               return;
          if (start >= l && end <= r) {
               lazy[node] = value;
               push(start, end, node);
               return;
          }
          int mid = (start + end) / 2;
          update(start, mid, 2 * node + 1, l, r, value);
          update(mid + 1, end, 2 * node + 2, l, r, value);
          st[node] = combine(st[2 * node + 1], st[2 * node + 2]);
     }

     public void build(int[] v) {
          build(0, n - 1, 0, v);
     }

     public int query(int l, int r) {
          return query(0, n - 1, l, r, 0);
     }

     public void update(int l, int r, int x) {
          update(0, n - 1, 0, l, r, x);
     }
}
