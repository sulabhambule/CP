import java.io.*;
import java.util.*;

public class Main {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FastReader in = new FastReader();

  public static void main(String[] args) {
    int n = in.nextInt();
    long[] x = new long[n];
    for (int i = 0; i < n; i++) {
      x[i] = in.nextLong();
    }
    for (int i = 1; i < n; i += 2) {
      x[i] *= -1;
    }
    int q = in.nextInt();
    SegTree tree1 = new SegTree(n, x);
    while (q-- > 0) {
      int type = in.nextInt();
      if (type == 0) {
        int index = in.nextInt() - 1;
        int val = in.nextInt();
        if (index % 2 == 0) {
          tree1.makeUpdate(index, val);
        } else {
          tree1.makeUpdate(index, -val);
        }
      } else {
        int l = in.nextInt() - 1;
        int r = in.nextInt() - 1;
        if (l % 2 == 0) {
          out.println(tree1.makeQuery(l, r).val);
        } else {
          out.println(-1 * tree1.makeQuery(l, r).val);
        }
      }
    }
    out.close();
  }

  static class FastReader {
    BufferedReader br;
    StringTokenizer st;

    public FastReader() {
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
  }
}

class SegTree {
  private Node[] tree;
  private long[] arr;
  private int n;
  private int s;

  public SegTree(int a_len, long[] a) {
    this.arr = a;
    this.n = a_len;
    this.s = 1;
    while (s < 2 * n) {
      s = s << 1;
    }
    tree = new Node[s];
    Arrays.fill(tree, new Node());
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

  private void update(int start, int end, int index, int queryIndex, Update u) {
    if (start == end) {
      u.apply(tree[index]);
      return;
    }
    int mid = (start + end) / 2;
    if (mid >= queryIndex) {
      update(start, mid, 2 * index, queryIndex, u);
    } else {
      update(mid + 1, end, 2 * index + 1, queryIndex, u);
    }
    tree[index].merge(tree[2 * index], tree[2 * index + 1]);
  }

  private Node query(int start, int end, int index, int left, int right) {
    if (start > right || end < left) {
      return new Node();
    }
    if (start >= left && end <= right) {
      return tree[index];
    }
    int mid = (start + end) / 2;
    Node l = query(start, mid, 2 * index, left, right);
    Node r = query(mid + 1, end, 2 * index + 1, left, right);
    Node ans = new Node();
    ans.merge(l, r);
    return ans;
  }

  public void makeUpdate(int index, long val) {
    Update newUpdate = new Update(val);
    update(0, n - 1, 1, index, newUpdate);
  }

  public Node makeQuery(int left, int right) {
    return query(0, n - 1, 1, left, right);
  }
}

class Node {
  long val;

  public Node() {
    this.val = 0;
  }

  public Node(long p1) {
    this.val = p1;
  }

  public void merge(Node l, Node r) {
    this.val = l.val + r.val;
  }
}

class Update {
  long val;

  public Update(long p1) {
    this.val = p1;
  }

  public void apply(Node a) {
    a.val = val;
  }
}
