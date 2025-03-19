
public class SegTreeSimple {

}

class SegmentTree {
  private int[] tree;
  private int n;

  public SegmentTree(int[] arr) {
    this.n = arr.length;
    this.tree = new int[4 * n]; // Segment tree size (4*n is safe)
    build(arr, 0, 0, n - 1);
  }

  private void build(int[] arr, int node, int start, int end) {
    if (start == end) {
      tree[node] = arr[start]; // Leaf node stores the array value
      return;
    }
    int mid = (start + end) / 2;
    build(arr, 2 * node + 1, start, mid);
    build(arr, 2 * node + 2, mid + 1, end);
    tree[node] = tree[2 * node + 1] + tree[2 * node + 2]; // Merge function (sum)
  }

  public void update(int index, int value) {
    update(0, 0, n - 1, index, value);
  }

  private void update(int node, int start, int end, int idx, int value) {
    if (start == end) {
      tree[node] = value; // Update the leaf node
      return;
    }
    int mid = (start + end) / 2;
    if (idx <= mid) {
      update(2 * node + 1, start, mid, idx, value);
    } else {
      update(2 * node + 2, mid + 1, end, idx, value);
    }
    tree[node] = tree[2 * node + 1] + tree[2 * node + 2]; // Recalculate sum
  }

  public int query(int left, int right) {
    return query(0, 0, n - 1, left, right);
  }

  private int query(int node, int start, int end, int l, int r) {
    if (r < start || l > end)
      return 0; // Completely outside
    if (l <= start && end <= r)
      return tree[node]; // Completely inside
    int mid = (start + end) / 2;
    int leftSum = query(2 * node + 1, start, mid, l, r);
    int rightSum = query(2 * node + 2, mid + 1, end, l, r);
    return leftSum + rightSum;
  }
}
