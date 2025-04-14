import java.io.*;
import java.util.*;

public class Main {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FastReader in = new FastReader();
  static int mod = (int) 1e9 + 7;

  public static void main(String[] args) {
    int t = in.nextInt();
    while (t-- > 0) {
      solve();
    }
    out.flush();
  }

  static void solve() {
    int n = in.nextInt();
    int a = in.nextInt();
    int b = in.nextInt();
    long[] x = new long[n + 1];
    for (int i = 1; i <= n; i++) {
      x[i] = in.nextLong();
    }
    long[] p = new long[n + 1];
    for (int i = 1; i <= n; i++) {
      p[i] = (p[i - 1] + x[i]);
    }
    long ans = Long.MAX_VALUE;
    for (int i = 0; i <= n; i++) {
      ans = Math.min(ans, (a + b) * (x[i] - x[0]) + b * (p[n] - p[i] - (long) (n - i) * x[i]));
    }
    out.println(ans);
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