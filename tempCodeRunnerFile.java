import java.io.*;
import java.util.*;

public class TLE {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FastReader in = new FastReader();
  private static final int MOD = (int) 1e9 + 7;

  public static void main(String[] args) throws Exception {
    int T = in.nextInt();
    while (T-- > 0) {
      TLAM();
    }
    out.close();
  }

  private static void TLAM() {
    int n = in.nextInt();
    int m = in.nextInt();
    int k = in.nextInt();
    String s = in.next();
    int count = 0;
    int ans = 0;
    for (int i = 0; i < n; i++) {
      if (s.charAt(i) == '1') {
        count = 0;
        continue;
      }
      count++;
      if (count == m) {
        ans++;
        i += (k - 1);
        count = 0;
      }
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

    double nextDouble() {
      return Double.parseDouble(next());
    }
  }
}
