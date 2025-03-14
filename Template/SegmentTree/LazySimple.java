
import java.util.*;

public class LazySimple {
  private int n;
  private int[] st;
  private int[][] lazy;

  public void init(int _n) {
    this.n = _n;
    st = new int[4 * n];
    lazy = new int[4 * n][2];
  }

  private int combine(int a, int b) {
    return a + b;
  }

  private void push(int start, int end, int node) {
    if (lazy[node][1] != 0) {
      // means that the common difference is greater than zero, we need to update it.
      int len = end - start + 1;
      st[node] += len * (lazy[node][0] - lazy[node][1]) + lazy[node][1] * len * (len + 1) / 2;

      if (start != end) {
        int mid = (start + end) / 2;
        int diff = (mid + 1) - start;
        lazy[2 * node + 1][0] += lazy[node][0];
        lazy[2 * node + 2][0] += lazy[node][0] + diff * lazy[node][1];
        lazy[2 * node + 1][1] += lazy[node][1];
        lazy[2 * node + 2][1] += lazy[node][1];
      }
      lazy[node] = new int[] { 0, 0 };
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
      lazy[node] = new int[] { start - l + 1, 1 };
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
