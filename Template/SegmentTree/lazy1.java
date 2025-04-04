
public class lazy1 {

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
    return (a + b);
  }

  private void push(int start, int end, int node) {
    if (lazy[node] != 0) {
      // there is need of the update
      st[node] = (end - start + 1) * lazy[node];
      if (start != end) {
        // this is not the root node, so push the updates to the children
        lazy[2 * node + 1] = lazy[node];
        lazy[2 * node + 2] = lazy[node];
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
      return 0;
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
      lazy[node] = value;
      push(start, end, node);
      return;
    }
    int mid = (start + end) / 2;
    update(start, mid, 2 * node + 1, l, r, value);
    update(mid + 1, end, 2 * node + 2, l, r, value);
    st[node] = combine(st[2 * node + 1], st[2 * node + 2]);
  }
}
