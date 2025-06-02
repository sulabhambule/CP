import java.io.*;
import java.util.*;

public class Main {
  static PrintWriter out = new PrintWriter(System.out);
  static FastReader in = new FastReader();
 

  public static void main(String[] args) {
    int t = in.nextInt();
   
    while (t-- > 0) {
      solve();
    }
    out.flush();
  }

  static void solve() {
    int n = in.nextInt();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = in.nextInt();
    }

  }

  static class FastReader {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

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
