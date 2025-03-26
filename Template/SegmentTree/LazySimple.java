
import java.util.*;

public class LazySimple {
  private int n;
  private long[] st;
  private long[] lazy;

  public void init(int _n) {
    this.n = _n;
    st = new long[4 * n];
    lazy = new long[4 * n];
  }

  private long combine(long a, long b) {
    return a + b;
  }

  private void push(int start, int end, int node) {
    if (lazy[node] != 0) {
      st[node] += (end - start + 1) * lazy[node];
      if (start != end) {
        lazy[2 * node + 1] += lazy[node];
        lazy[2 * node + 2] += lazy[node];
      }
      lazy[node] = 0;
    }
  }

  private void build(int start, int end, int node, long[] v) {
    if (start == end) {
      st[node] = v[start];
      return;
    }
    int mid = (start + end) / 2;
    build(start, mid, 2 * node + 1, v);
    build(mid + 1, end, 2 * node + 2, v);
    st[node] = combine(st[2 * node + 1], st[2 * node + 2]);
  }

  private long query(int start, int end, int l, int r, int node) {
    push(start, end, node);
    if (start > r || end < l)
      return 0;
    if (start >= l && end <= r)
      return st[node];
    int mid = (start + end) / 2;
    long q1 = query(start, mid, l, r, 2 * node + 1);
    long q2 = query(mid + 1, end, l, r, 2 * node + 2);
    return combine(q1, q2);
  }

  private void update(int start, int end, int node, int l, int r, long value) {
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

  public void build(long[] v) {
    build(0, n - 1, 0, v);
  }

  public long query(int l, int r) {
    return query(0, n - 1, l, r, 0);
  }

  public void update(int l, int r, long x) {
    update(0, n - 1, 0, l, r, x);
  }
}
