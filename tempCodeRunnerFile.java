import java.io.*;
import java.util.*;

public class TLE {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FastReader in = new FastReader();
  // private static final int MOD = (int) 1e9 + 7;
  private static final int MOD = 998244353;

  public static void main(String[] args) {
    int T = 1;
    while (T-- > 0) {
      solve();
    }
    out.close();
  }

  private static void solve() {
    int n = in.nextInt();
    long r = in.nextLong();
    int avg = in.nextInt();
    long[][] a = new int[n][2];
    long sum = 0;
    for (int i = 0; i < n; i++) {
      a[i][0] = in.nextLong();
      sum += a[i][0];
      a[i][1] = in.nextLong();
    }
    int req = n * avg;

    if (req < sum) {
      System.out.println(0);
      return;
    }
    Arrays.sort(a, (x, y) -> (x[1] - y[1]));
    long rem = req - sum;
    long ans = 0;
    for (int i = 0; i < n; i++) {
      if (rem == 0) {
        break;
      }
      long till = r - a[i][0];
      if (till > 0) {
        long val = a[i][1];
        ans += (Math.min(rem, till) * val);
        rem -= Math.min(rem, till);
      }
    }

    System.out.println(ans);
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
  }
}
