
import java.util.*;

public class LAZY {
  public static void main(String[] args) {
    long[] arr = { 1, 2, 3, 4, 5 };
    LazySGT segTree = new LazySGT(arr.length, arr);

    System.out.println(segTree.makeQuery(0, 4));

    segTree.makeUpdate(1, 3, 10);

    System.out.println(segTree.makeQuery(0, 4));
  }
}

class LazySGT {

  class Node {
    long val;

    Node() {
      val = 0;
    }

    Node(long p1) {
      val = p1;
    }

    void merge(Node l, Node r) {
      val = l.val + r.val;
    }
  }

  class Update {
    long val;

    Update() {
      val = 0;
    }

    Update(long val1) {
      val = val1;
    }

    void apply(Node node, int start, int end) {
      node.val = val * (end - start + 1);
    }

    void combine(Update newUpdate, int start, int end) {
      val = newUpdate.val;
    }
  }

  private Node[] tree;
  private boolean[] lazy;
  private Update[] updates;
  private long[] arr;
  private int n;
  private int s;

  LazySGT(int a_len, long[] a) {
    this.arr = a;
    this.n = a_len;
    this.s = 1;

    while (s < 2 * n) {
      s <<= 1;
    }

    tree = new Node[s];
    lazy = new boolean[s];
    updates = new Update[s];

    Arrays.fill(tree, new Node());
    Arrays.fill(lazy, false);
    Arrays.fill(updates, new Update());

    build(0, n - 1, 1);
  }

  private void build(int start, int end, int index) {
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
    if (start > right || end < left) {
      return;
    }

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
    if (start > right || end < left) {
      return new Node();
    }

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

  public void makeUpdate(int left, int right, long val) {
    Update newUpdate = new Update(val);
    update(0, n - 1, 1, left, right, newUpdate);
  }

  public long makeQuery(int left, int right) {
    return query(0, n - 1, 1, left, right).val;
  }
}
