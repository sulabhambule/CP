import java.io.*;
import java.util.*;

public class Simple {
     public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
     static FASTIO in = new FASTIO();

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
          tree.build(0, n - 1, 0, new long[n]);

          for (int i = 0; i < m; i++) {
               int type = in.nextInt();
               if (type == 1) {
                    int l = in.nextInt();
                    int r = in.nextInt();
                    long val = in.nextLong();
                    tree.update(0, n - 1, 0, l, r - 1, val);
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
     long[] st;
     long[] lazy;

     public void init(int n) {
          this.n = n;
          st = new long[4 * n];
          lazy = new long[4 * n];
     }

     private long combine(long a, long b) {
          return Math.min(a, b);
     }

     private void push(int start, int end, int node) {
          if (lazy[node] != 0) {
               // there is need of the update
               st[node] += lazy[node];
               if (start != end) {
                    // this is not the root node, so push the updates to the children
                    lazy[2 * node + 1] += lazy[node];
                    lazy[2 * node + 2] += lazy[node];
               }
               lazy[node] = 0;
          }
     }

     public void build(int start, int end, int node, long[] v) {
          if (start == end) {
               // this is the root node, so assign the values here
               st[node] = v[start];
               return;
          }
          int mid = (start + end) / 2;
          build(start, mid, 2 * node + 1, v);
          build(mid + 1, end, 2 * node + 2, v);
          st[node] = combine(st[2 * node + 1], st[2 * node + 2]);
     }

     public long query(int start, int end, int l, int r, int node) {
          push(start, end, node);
          if (l > end || r < start) {
               return Long.MAX_VALUE;
          }
          if (l <= start && end <= r) {
               return st[node];
          }
          int mid = (start + end) / 2;
          long q1 = query(start, mid, l, r, 2 * node + 1);
          long q2 = query(mid + 1, end, l, r, 2 * node + 2);
          return combine(q1, q2);
     }

     public void update(int start, int end, int node, int l, int r, long value) {
          push(start, end, node);
          if (l > end || r < start) {
               return;
          }
          if (l <= start && end <= r) {
               lazy[node] += value;
               push(start, end, node);
               return;
          }
          int mid = (start + end) / 2;
          update(start, mid, 2 * node + 1, l, r, value);
          update(mid + 1, end, 2 * node + 2, l, r, value);
          st[node] = combine(st[2 * node + 1], st[2 * node + 2]);
     }
}
