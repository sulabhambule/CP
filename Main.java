import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
  static FastReader in = new FastReader();
  static PrintWriter out = new PrintWriter(System.out);
  static int mod = (int) 1e9 + 7;

  public static void main(String[] args) {
    int t = in.nextInt();
    while (t-- > 0) {
      solve();
    }
    out.flush();
  }

  static void solve() {

  }

  static class FastReader {
    BufferedReader br;
    StringTokenizer st;

    FastReader() {
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
  }
}
