
import java.io.*;
import java.util.*;

public class Main {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FastReader in = new FastReader();
  static int mod = (int) 1e9 + 7;
  static final int inf = (int) 1e9;
  static final long INF = (long) 1e18;

  public static void main(String[] args) {
    int t = in.nextInt();
    while (t-- > 0) {
      solve();
    }
    out.flush();
  }

  static void solve() {
    int n = in.nextInt();
    List<node> cells = new ArrayList<>(n * n);
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        cells.add(new node(i, j, n));
      }
    }
    Collections.sort(cells);
    int[][] ans = new int[n][n];
    for (int v = 0; v < n * n; v++) {
      node c = cells.get(v);
      ans[c.i][c.j] = v;
    }
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        out.print(ans[i][j]);
        if (j + 1 < n)
          out.print(' ');
      }
      out.println();
    }
  }

  static class node implements Comparable<node> {
    int i, j;
    long value;

    node(int i, int j, int n) {
      this.i = i;
      this.j = j;
      this.value = 1L * (i + 1) * (j + 1) * (n - i) * (n - j);
    }

    @Override
    public int compareTo(node o) {
      return Long.compare(o.value, this.value);
    }
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
