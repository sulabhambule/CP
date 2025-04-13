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
    long n = in.nextLong();
    int m = in.nextInt();
    long k = in.nextLong();

    long[] a = new long[m];
    for (int i = 0; i < m; i++) {
      a[i] = in.nextLong();
    }
    long count = 0;
    boolean f = false;
    for (long i : a) {
      if (i == (n + k - 1) / k) {
        count++;
      }
      if (i > (n + k - 1) / k) {
        f = true;
        break;
      }
    }
    if (f) {
      out.println("NO");
      return;
    }

    if (count <= (long) (n - 1) % k + 1) {
      out.println("YES");
    } else {
      out.println("NO");
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