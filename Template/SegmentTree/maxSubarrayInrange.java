
import java.io.*;
import java.util.*;

// codeforcess problem link: https://codeforces.com/contest/1692/problem/H
// here we are finding the max subArray sum in the range l --> r.

// Node: this prooblem is only for the reference.
public class maxSubarrayInrange {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FASTIO in = new FASTIO();
  static int inf = Integer.MAX_VALUE / 2;

  public static void main(String[] args) {
    int t = in.nextInt();
    while (t-- > 0) {
      solve();
    }
    out.close();
  }

  static void solve() {
    int n = in.nextInt();
    long[] x = new long[n];
    Map<Long, List<Integer>> map = new HashMap<>(); // value , occurence list.
    for (int i = 0; i < n; i++) {
      x[i] = in.nextLong();
      map.computeIfAbsent(x[i], k -> new ArrayList<>()).add(i);
    }

    SegTree sgt = new SegTree(n);

    // now we are trying to fix the value of a (as we need to find the a, l, r, so
    // we win on a).
    long num = -1, maxSum = 0;
    for (var entry : map.entrySet()) {
      long val = entry.getKey();
      // now we are trying for the val .
      List<Integer> occ = entry.getValue();
      for (int i : occ) {
        sgt.update(0, 0, n - 1, i, 1);
      }
      Node res = sgt.query(0, 0, n - 1, 0, n - 1);
      if (res.maxSubSum > maxSum) {
        maxSum = res.maxSubSum;
        num = val;
      }
      for (int i : occ) {
        sgt.update(0, 0, n - 1, i, -1);
      }
    }

    // now we had find the value of now , its time to find the range l -- > r
    int[] arr = new int[n];
    Arrays.fill(arr, -1);
    for (int index : map.get(num)) {
      arr[index] = 1;
    }

    maxSum = -inf;
    int currSum = -inf;
    int l = -1, ansL = -1, ansR = -1;

    for (int i = 0; i < n; i++) {
      if (arr[i] > arr[i] + currSum) {
        currSum = arr[i];
        l = i;
      } else {
        currSum += arr[i];
      }

      if (currSum > maxSum) {
        maxSum = currSum;
        ansL = l;
        ansR = i;
      }
    }

    out.println(num + " " + ++ansL + " " + ++ansR);
  }

  static class SegTree {
    int n;
    Node[] tree;

    public SegTree(int n) {
      this.n = n;
      tree = new Node[4 * n];
      for (int i = 0; i < 4 * n; i++) {
        tree[i] = new Node();
      }

      build(0, n - 1, 0);
    }

    public void build(int start, int end, int node) {
      if (start == end) {
        tree[node] = new Node(-1, -1, -1, -1);
        return;
      }

      int mid = (start + end) / 2;
      build(start, mid, 2 * node + 1);
      build(mid + 1, end, 2 * node + 2);
      tree[node].merge(tree[2 * node + 1], tree[2 * node + 2]);
    }

    public void update(int node, int start, int end, int idx, int val) {
      if (start == end) {
        tree[node] = new Node(val, val, val, val); // if the subArray for size one has this conf.
        return;
      }
      int mid = (start + end) >> 1;
      if (idx <= mid) {
        update(2 * node + 1, start, mid, idx, val);
      } else {
        update(2 * node + 2, mid + 1, end, idx, val);
      }
      tree[node].merge(tree[2 * node + 1], tree[2 * node + 2]);
    }

    Node query(int node, int start, int end, int l, int r) {
      if (start > r || l > end) {
        return new Node(0, 0, 0, 0); // default value that could not contribute in out of bound range.
      }
      if (start >= l && end <= r) {
        return tree[node];
      }
      int mid = (start + end) >> 1;
      Node left = query(2 * node + 1, start, mid, l, r);
      Node right = query(2 * node + 2, mid + 1, end, l, r);
      Node res = new Node();
      res.merge(left, right);
      return res;
    }
  }

  static class Node {
    int sum, leftSum, rightSum, maxSubSum;

    Node() {
      sum = 0;
      leftSum = -inf;
      rightSum = -inf;
      maxSubSum = -inf;
    }

    Node(int sum, int leftSum, int rightSum, int maxSubSum) {
      this.sum = sum;
      this.leftSum = leftSum;
      this.rightSum = rightSum;
      this.maxSubSum = maxSubSum;
    }

    void merge(Node a, Node b) {
      sum = a.sum + b.sum;
      leftSum = Math.max(a.leftSum, a.sum + b.leftSum);
      rightSum = Math.max(b.rightSum, b.sum + a.rightSum);
      maxSubSum = Math.max(a.maxSubSum, Math.max(b.maxSubSum, a.rightSum + b.leftSum));
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