import java.io.*;
import java.util.*;
// Author : Sulabh Ambule

public class TLE {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static long MOD = (long) (1e9 + 7);
  // static long MOD = 998244353;
  static FastReader in = new FastReader();

  public static void main(String[] args) throws Exception {
    int cf = in.nextInt();
    while (cf-- > 0) {
      Accepted();
    }
    out.flush();
    out.close();
  }

  /*
   * 
   * 
   * || JAI SHREE RAM ||
   * 
   * 
   */
  private static void Accepted() {
    int n = in.nextInt();
    String s = in.next();
    int count = 0;
    for (int i = 0; i < n; i++) {
      if (s.charAt(i) == '*') {
        count++;
      }
    }
    if (count == n) {
      System.out.println(0);
      return;
    }
    int m = (count + 1) / 2;
    int idx = 0;
    for (int i = 0; i < n; i++) {
      if (s.charAt(i) == '*') {
        m--;
      }
      if (m == 0) {
        idx = i;
        break;
      }
    }
    long ans = 0;
    int idx2 = idx;

    for (int i = idx + 1; i < n; i++) {
      if (s.charAt(i) == '*') {
        ans += i - idx2 - 1;
        idx2++;
      }
    }
    idx2 = idx;
    for (int i = idx - 1; i >= 0; i--) {
      if (s.charAt(i) == '*') {
        ans += idx2 - i - 1;
        idx2--;
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

  // static class Pair {
  // long first;
  // long second
  // Pair(long f, long s) {
  // this.first = f;
  // this.second = s;
  // }
  // }

  static class Pair implements Comparable<Pair> {
    long first;
    long second;

    Pair(long first, long x) {
      this.first = first;
      this.second = x;
    }

    @Override
    public int compareTo(Pair other) {
      return Long.compare(this.second, other.second);
    }
  }
}