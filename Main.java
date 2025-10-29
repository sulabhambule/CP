import java.io.*;
import java.util.*;

public class Main {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FASTIO in = new FASTIO();
  static final int inf = (int) 1e9;
  static final long INF = (long) 1e18;
  // static final int mod = (int) 1e9 + 7;
  static final int mod = 998244353;
  static final long BASE = 911382323;
  static final long MOD = 972663749;

  public static void main(String[] args) {
    int t = in.nextInt();
    while (t-- > 0)
      solve();
    out.close();
  }

  static void solve() {
    int n = in.nextInt();
    int[] l = new int[n], r = new int[n];
    long ans = 0;
    long[][] LR = new long[n][3];
    for (int i = 0; i < n; i++) {
      l[i] = in.nextInt();
      r[i] = in.nextInt();
      ans += r[i] - l[i];
      LR[i] = new long[] { l[i], r[i], l[i] + r[i] };
    }
    Arrays.sort(LR, (x, y) -> Long.compare(y[2], x[2]));
    // sort on the basis of l + r values in desc
    for (int i = 0; i < n / 2; i++) {
      ans += LR[i][1]; // + R
    }
    for (int i = n / 2; i < n / 2 + n / 2; i++) {
      ans -= LR[i][0]; // - L
    }
    if (n % 2 == 0) {
      out.println(ans);
      return;
    }
    long val = ans;
    for (int i = n - 2; i >= n / 2; i--) {
      val -= LR[i + 1][0]; // -L
      val += LR[i][0];
      ans = Math.max(ans, val);
    }
    for (int i = n / 2 - 1; i >= 0; i--) {
      val += LR[i + 1][1]; // +R
      val -= LR[i][1];
      ans = Math.max(ans, val);
    }
    out.println(ans);
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
