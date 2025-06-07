import java.io.*;
import java.util.*;

public class Simple {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FASTIO in = new FASTIO();
  static int mod = (int) 1e9 + 7;
  static int LOG = 20;
  static int[][] up;

  public static void main(String[] hi) {
    int n = in.nextInt(), q = in.nextInt();
    up = new int[n + 1][LOG];
    for (int i = 1; i <= n; i++) {
      int ti = in.nextInt();
      up[i][0] = ti;
    }
    for (int i = 1; i < LOG; i++) {
      for (int node = 1; node <= n; node++) {
        up[node][i] = up[up[node][i - 1]][i - 1];
      }
    }

    for (int i = 0; i < q; i++) {
      int node = in.nextInt();
      long k = in.nextLong();

      for (int j = LOG - 1; j >= 0; j--) {
        if (((1L << j) & k) != 0) {
          node = up[node][j];
        }
      }
      out.println(node);
    }

    out.flush();
    out.close();
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

    String nextLine() {
      try {
        st = null;
        return br.readLine();
      } catch (IOException e) {
        e.printStackTrace();
        return null;
      }
    }

    double nextDouble() {
      return Double.parseDouble(next());
    }
  }
}