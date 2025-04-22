import java.util.*;

public class Simple {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int m = in.nextInt();
    long[] a = new long[n];
    LazySGTG tree = new LazySGTG(n, a);

    for (int i = 0; i < m; i++) {
      int l = in.nextInt();
      int r = in.nextInt();
      long val = in.nextLong();
      tree.makeUpdate(l, r - 1, val);
      System.out.println(tree.makeQuery(0, n - 1).maxSum);
    }
  }
}

class LazySGTG {
  static class Node {
    long segSum;
    long prefSum;
    long suffSum;
    long maxSum;

    Node() { // Identity element
      segSum = 0;
      prefSum = 0;
      suffSum = 0;
      maxSum = 0;
    }

    Node(long val) {
      segSum = val;
      prefSum = Math.max(0, val);
      suffSum = Math.max(0, val);
      maxSum = Math.max(0, val);
    }

    void merge(Node l, Node r) { // Merge two child nodes
      segSum = l.segSum + r.segSum;
      prefSum = Math.max(l.prefSum, r.prefSum + l.segSum);
      suffSum = Math.max(r.suffSum, r.segSum + l.suffSum);
      maxSum = Math.max(l.maxSum, Math.max(r.maxSum, l.suffSum + r.prefSum));
    }
  }

  static class Update {
    long val; // may change

    Update() { // Identity update
      val = 0;
    }

    Update(long val1) { // Actual Update
      val = val1;
    }

    void apply(Node a, int start, int end) { // apply update to given node
      a.segSum = (end - start + 1) * val;
      if (val > 0) {
        a.prefSum = a.segSum;
        a.suffSum = a.segSum;
        a.maxSum = a.segSum;
      } else {
        a.prefSum = 0;
        a.suffSum = 0;
        a.maxSum = 0;
      }
    }

    void combine(Update newUpdate, int start, int end) {
      val = newUpdate.val;
    }
  }

  private Node[] tree;
  private boolean[] lazy;
  private Update[] updates;
  private long[] arr; // type may change
  private int n;
  private int s;

  LazySGTG(int a_len, long[] a) { // change if type updated
    arr = Arrays.copyOf(a, a_len);
    n = a_len;
    s = 1;
    while (s < 2 * n) {
      s <<= 1;
    }
    tree = new Node[s];
    lazy = new boolean[s];
    updates = new Update[s];
    for (int i = 0; i < s; i++) {
      tree[i] = new Node();
      updates[i] = new Update();
    }
    build(0, n - 1, 1);
  }

  private void build(int start, int end, int index) { // Never change this
    if (start == end) {
      tree[index] = new Node(arr[start]);
      return;
    }
    int mid = (start + end) / 2;
    build(start, mid, 2 * index);
    build(mid + 1, end, 2 * index + 1);
    tree[index] = new Node();
    tree[index].merge(tree[2 * index], tree[2 * index + 1]);
  }

  private void pushdown(int index, int start, int end) {
    if (lazy[index]) {
      int mid = (start + end) / 2;
      apply(2 * index, start, mid, updates[index]);
      apply(2 * index + 1, mid + 1, end, updates[index]);
      updates[index] = new Update();
      lazy[index] = false;
    }
  }

  private void apply(int index, int start, int end, Update u) {
    if (start != end) {
      lazy[index] = true;
      updates[index].combine(u, start, end);
    }
    u.apply(tree[index], start, end);
  }

  private void update(int start, int end, int index, int left, int right, Update u) {
    if (start > right || end < left)
      return;
    if (start >= left && end <= right) {
      apply(index, start, end, u);
      return;
    }
    pushdown(index, start, end);
    int mid = (start + end) / 2;
    update(start, mid, 2 * index, left, right, u);
    update(mid + 1, end, 2 * index + 1, left, right, u);
    tree[index].merge(tree[2 * index], tree[2 * index + 1]);
  }

  private Node query(int start, int end, int index, int left, int right) {
    if (start > right || end < left)
      return new Node();
    if (start >= left && end <= right) {
      pushdown(index, start, end);
      return tree[index];
    }
    pushdown(index, start, end);
    int mid = (start + end) / 2;
    Node l = query(start, mid, 2 * index, left, right);
    Node r = query(mid + 1, end, 2 * index + 1, left, right);
    Node ans = new Node();
    ans.merge(l, r);
    return ans;
  }

  public void makeUpdate(int left, int right, long val) { // pass in as many parameters as required
    Update newUpdate = new Update(val); // may change
    update(0, n - 1, 1, left, right, newUpdate);
  }

  public Node makeQuery(int left, int right) {
    return query(0, n - 1, 1, left, right);
  }
}
