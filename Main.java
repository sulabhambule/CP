import java.io.*;
import java.util.*;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

  public static void main(String[] args) throws IOException {
    int t = nextInt();
    while (t-- > 0) {
      solve();
    }
    out.flush();
  }

  static void solve() throws IOException {

  }

  // Fast input routines
  static String next() throws IOException {
    while (st == null || !st.hasMoreTokens()) {
      String line = br.readLine();
      if (line == null)
        return null;
      st = new StringTokenizer(line);
    }
    return st.nextToken();
  }

  static int nextInt() throws IOException {
    return Integer.parseInt(next());
  }

  static long nextLong() throws IOException {
    return Long.parseLong(next());
  }
}
