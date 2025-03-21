import java.io.*;
import java.util.*;

public class Simple {
     public static void main(String[] args) throws IOException {
          FastIO in = new FastIO();
          PrintWriter out = new PrintWriter(System.out);

          int n = in.nextInt();
          int[] arr = new int[n];

          for (int i = 0; i < n; i++) {
               arr[i] = in.nextInt();
          }

          int[] a = new int[n];
          Arrays.fill(a, 1);
          int[] ans = new int[n];

          SegTree tree = new SegTree(n, a);

          for (int i = n - 1; i >= 0; i--) {
               int count = arr[i] + 1;
               int index = tree.findKthOne(count);
               ans[index] = i + 1;
               tree.flip(index);
          }

          for (int i = 0; i < n; i++) {
               out.print(ans[i] + " ");
          }
          out.close();
     }
}

class SegTree {
     private int[] tree;
     private int[] arr;
     private int n;

     public SegTree(int size, int[] array) {
          this.n = size;
          this.arr = array;
          this.tree = new int[4 * n];
          build(0, 0, n - 1);
     }

     private void build(int node, int start, int end) {
          if (start == end) {
               tree[node] = arr[start];
               return;
          }
          int mid = (start + end) / 2;
          build(2 * node + 1, start, mid);
          build(2 * node + 2, mid + 1, end);
          tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
     }

     private void update(int node, int start, int end, int idx) {
          if (start == end) {
               arr[idx] = 0; // Mark the position as used (flip 1 → 0)
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

          int leftCount = tree[2 * node + 1];
          if (k > leftCount) {
               return findKthOne(2 * node + 2, (start + end) / 2 + 1, end, k - leftCount);
          } else {
               return findKthOne(2 * node + 1, start, (start + end) / 2, k);
          }
     }

     public int findKthOne(int k) {
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
