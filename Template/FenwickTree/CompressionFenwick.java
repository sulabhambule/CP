
import java.io.*;
import java.util.*;

public class CompressionFenwick {
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
    int[] a = new int[n], b = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = in.nextInt();
      b[i] = in.nextInt();
    }

    // now as we are making the bit for the b value but it is very large so we
    // compress it
    Integer[] order = new Integer[n];
    for (int i = 0; i < n; i++) {
      order[i] = i;
    }
    Arrays.sort(order, (x, y) -> Integer.compare(b[x], b[y]));
    for (int i = 0; i < n; i++) {
      b[order[i]] = i;
    }

    // now sort the b arr on the basis of the a values
    Arrays.sort(order, Comparator.comparingInt(i -> a[i]));

    Fenwick fenw = new Fenwick(n);
    long ans = 0;

    for (int i = n - 1; i >= 0; i--) {
      ans += fenw.query(b[order[i]]);
      fenw.update(b[order[i]], 1);
    }

    out.println(ans);

  }

  static class Fenwick {
    int n;
    int[] tree;

    public Fenwick(int n) {
      this.n = n;
      tree = new int[n + 1];
    }

    void update(int index, int delta) {
      for (++index; index <= n; index += (index & -index)) {
        tree[index] += delta;
      }
    }

    int query(int index) {
      int res = 0;
      while (index > 0) {
        res += tree[index];
        index -= index & -index;
      }

      return res;
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