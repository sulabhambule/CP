
import java.io.*;
import java.util.*;

public class LPS2 {
  static final long INF = Long.MAX_VALUE / 2;
  static FastReader in = new FastReader();
  static PrintWriter out = new PrintWriter(System.out);
  static int mod = (int) 1e9 + 7;
  // static int mod = 998244353;

  public static void main(String[] args) throws IOException {
    int t = 1;
    while (t-- > 0) {
      solve();
    }
    out.close();
  }

  static void solve() {
    String s = in.next();
    int n = s.length();
    int[] lps = new int[n];
    lps[0] = 0;
    for (int i = 1; i < n; i++) {
      int len = lps[i - 1];
      while (len > 0 && s.charAt(i) != s.charAt(len)) {
        len = lps[len - 1];
      }
      if (s.charAt(i) == s.charAt(len))
        len++;
      lps[i] = len;
    }
  }

  static class FastReader {
    BufferedReader br;
    StringTokenizer st;

    public FastReader() {
      br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
      while (st == null || !st.hasMoreTokens()) {
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException e) {
          throw new RuntimeException(e);
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