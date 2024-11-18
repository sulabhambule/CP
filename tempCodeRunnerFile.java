import java.io.*;
import java.util.*;

public class TLE {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FastReader in = new FastReader();
  // static long MOD = (long)1e9 + 7;

  public static void main(String[] args) throws Exception {
    int cf = in.nextInt();
    while (cf-- > 0) {
      Accepted();
    }
    // out.flush();
    out.close();
  }

  // Lets, hope for best.

  private static void Accepted() {
    int n = in.nextInt();
    int m = in.nextInt();
    long k = in.nextInt();
    long[] a = new long[n];
    long Asum = 0, Bsum = 0;
    for (int i = 0; i < n; i++) {
      a[i] = in.nextLong();
      Asum += a[i];
    }
    long[] b = new long[m];
    for (int i = 0; i < m; i++) {
      b[i] = in.nextLong();
      Bsum += b[i];
    }

    Arrays.sort(a);
    Arrays.sort(b);

    if (k % 2 == 1) {
      if (a[0] < b[m - 1]) {
        Asum -= a[0];
        Asum += b[m - 1];
      }
      System.err.println(Asum);
    } else {
      if (a[0] < b[m - 1]) {
        Asum -= a[0];
        Asum += b[m - 1];
        Asum += Math.min(a[0], b[0]);
        Asum -= Math.max(a[n - 1], b[m - 1]);
      } else {
        Asum -= a[n - 1];
        Asum += b[0];
      }
      System.out.println(Asum);
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

    double nextDouble() {
      return Double.parseDouble(next());
    }

    String nextLine() {
      String str = "";
      try {
        str = br.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return str;
    }
  }
}
