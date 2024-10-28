import java.io.*;
import java.util.*;

// Author: Sulabh Ambule 
public class TLE {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FastReader in = new FastReader();

  public static void main(String[] args) throws Exception {
    int cf = in.nextInt();
    while (cf-- > 0) {
      Accepted();
    }
    out.flush();
    out.close();
  }

  private static void Accepted() {
    long x = in.nextLong();
    long n = in.nextLong();
    int ans = 1;
    for (int i = 1; i * i <= x; i++) {
      if (x % i == 0) {
        if (i <= x / n) {
          ans = Math.max(ans, i);
        }
        if (x / i <= x / n) {
          ans = (int) Math.max(ans, x / i);
        }
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
