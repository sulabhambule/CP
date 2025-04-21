import java.util.*;

public class Simple {
  static final int BITS = 21;

  static class Node {
    long val;

    Node() {
      this.val = 0;
    }

    Node(long v) {
      this.val = v;
    }

    public void merge(Node left, Node right) {
      this.val = left.val + right.val;
    }

    public void setVal(long v) {
      this.val = v;
    }
  }

  static class Update {
    long val;

    Update() {
      this.val = 0;
    }

    Update(long v) {
      this.val = v;
    }

    void apply(Node node, int start, int end) {
      if (val == 0)
        return;
      node.val += (end - start + 1) * val;
    }

    void combine(Update other, int start, int end) {
      val += other.val;
    }

    boolean hasUpdate() {
      return val != 0;
    }

    void reset() {
      val = 0;
    }
  }

  static class LazySegmentTree {
    int n;
    Node[] tree;
    Update[] lazy;
    boolean[] pendingUpdate;

    LazySegmentTree(long[] arr) {
      n = arr.length;
      tree = new Node[4 * n];
      lazy = new Update[4 * n];
      pendingUpdate = new boolean[4 * n];

      for (int i = 0; i < 4 * n; i++) {
        tree[i] = new Node();
        lazy[i] = new Update();
      }

      build(1, 0, n - 1, arr);
    }

    void build(int index, int l, int r, long[] arr) {
      if (l == r) {
        tree[index] = new Node(arr[l]);
        return;
      }
      int mid = (l + r) / 2;
      build(2 * index, l, mid, arr);
      build(2 * index + 1, mid + 1, r, arr);
      tree[index] = new Node();
      tree[index].merge(tree[2 * index], tree[2 * index + 1]);
    }

    void pushDown(int index, int l, int r) {
      if (!pendingUpdate[index])
        return;

      int mid = (l + r) / 2;

      apply(2 * index, l, mid, lazy[index]);
      apply(2 * index + 1, mid + 1, r, lazy[index]);

      lazy[index].reset();
      pendingUpdate[index] = false;
    }

    void apply(int index, int l, int r, Update upd) {
      upd.apply(tree[index], l, r);
      if (l != r) {
        lazy[index].combine(upd, l, r);
        pendingUpdate[index] = true;
      }
    }

    void update(int index, int l, int r, int i, int j, Update upd) {
      if (r < i || l > j)
        return;

      if (l >= i && r <= j) {
        apply(index, l, r, upd);
        return;
      }

      pushDown(index, l, r);
      int mid = (l + r) / 2;
      update(2 * index, l, mid, i, j, upd);
      update(2 * index + 1, mid + 1, r, i, j, upd);
      tree[index].merge(tree[2 * index], tree[2 * index + 1]);
    }

    Node query(int index, int l, int r, int i, int j) {
      if (r < i || l > j)
        return new Node(0);

      if (l >= i && r <= j)
        return tree[index];

      pushDown(index, l, r);
      int mid = (l + r) / 2;
      Node left = query(2 * index, l, mid, i, j);
      Node right = query(2 * index + 1, mid + 1, r, i, j);
      Node result = new Node();
      result.merge(left, right);
      return result;
    }

    public void updateRange(int l, int r, long val) {
      update(1, 0, n - 1, l, r, new Update(val));
    }

    public long queryRange(int l, int r) {
      return query(1, 0, n - 1, l, r).val;
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();

    int[] arr = new int[n];
    long[][] bitwiseArr = new long[BITS][n];

    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextInt();
      for (int j = 0; j < BITS; j++) {
        if ((arr[i] & (1 << j)) != 0) {
          bitwiseArr[j][i] = 1;
        }
      }
    }

    LazySegmentTree[] segTrees = new LazySegmentTree[BITS];
    for (int i = 0; i < BITS; i++) {
      segTrees[i] = new LazySegmentTree(bitwiseArr[i]);
    }

    int q = sc.nextInt();
    while (q-- > 0) {
      int type = sc.nextInt();
      if (type == 2) {
        int l = sc.nextInt() - 1;
        int r = sc.nextInt() - 1;
        int x = sc.nextInt();
        for (int i = 0; i < BITS; i++) {
          if ((x & (1 << i)) != 0) {
            segTrees[i].updateRange(l, r, 1);
          }
        }
      } else {
        int l = sc.nextInt() - 1;
        int r = sc.nextInt() - 1;
        long ans = 0;
        for (int i = 0; i < BITS; i++) {
          long count = segTrees[i].queryRange(l, r);
          ans += count * (1L << i);
        }
        System.out.println(ans);
      }
    }
  }
}
