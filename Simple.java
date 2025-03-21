import java.io.*;
import java.util.*;

public class Simple {
     public static void main(String[] args) throws IOException {
          FastIO in = new FastIO();
          PrintWriter out = new PrintWriter(System.out);

          int n = in.nextInt();
          int[] a = new int[n];

          for (int i = 0; i < n; i++) {
               a[i] = in.nextInt();
          }

          int[] ans = new int[n];
          SegTree tree = new SegTree(n);

          for (int i = n - 1; i >= 0; i--) {
               int index = tree.findKthOneFromRight(a[i] + 1); // Finding (a[i]+1)th one from the right
               ans[i] = index + 1; // Convert 0-based to 1-based
               tree.flip(index); // Mark as used
          }

          for (int i = 0; i < n; i++) {
               out.print(ans[i] + " ");
          }
          out.close();
     }
}

class SegTree {
     private int[] tree;
     private int n;

     public SegTree(int size) {
          this.n = size;
          this.tree = new int[4 * n];
          build(0, 0, n - 1);
     }

     private void build(int node, int start, int end) {
          if (start == end) {
               tree[node] = 1;
               return;
          }
          int mid = (start + end) / 2;
          build(2 * node + 1, start, mid);
          build(2 * node + 2, mid + 1, end);
          tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
     }

     private void update(int node, int start, int end, int idx) {
          if (start == end) {
               tree[node] = 0;
               return;
          }
          int mid = (start + end) / 2;
          if (idx <= mid) {
               update(2 * node + 1, start, mid, idx);
          } else {
               update(2 * node + 2, mid + 1, end, idx);
          }
          tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
     }

     public void flip(int index) {
          update(0, 0, n - 1, index);
     }

     private int findKthOne(int node, int start, int end, int k) {
          if (start == end)
               return start;

          int rightCount = tree[2 * node + 2];
          int mid = (start + end) / 2;

          if (k <= rightCount) {
               return findKthOne(2 * node + 2, mid + 1, end, k);
          } else {
               return findKthOne(2 * node + 1, start, mid, k - rightCount);
          }
     }

     public int findKthOneFromRight(int k) {
          return findKthOne(0, 0, n - 1, k);
     }
}

class FastIO {
     BufferedReader br;
     StringTokenizer st;

     public FastIO() {
          br = new BufferedReader(new InputStreamReader(System.in));
     }

     String next() throws IOException {
          while (st == null || !st.hasMoreElements()) {
               st = new StringTokenizer(br.readLine());
          }
          return st.nextToken();
     }

     int nextInt() throws IOException {
          return Integer.parseInt(next());
     }
}
