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
    long[] h = new long[n];
    for (int i = 0; i < h.length; i++) {
      h[i] = in.nextLong();
    }
    long ans = -1;
    long low = 0;
    long high = (long) 1e9;
    while (low <= high) {
      long mid = (low + high) / 2;
      if (isPossible(mid, h)) {
        ans = mid;
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    out.println(ans);
  }

  private static boolean isPossible(long x, long[] h) {
    int n = h.length;
    long[] cur_h = h.clone();
    for (int i = n - 1; i >= 2; --i) {
      if (cur_h[i] < x) {
        return false;
      }
      long d = Math.min(h[i], cur_h[i] - x) / 3;
      cur_h[i - 1] += d;
      cur_h[i - 2] += 2 * d;
    }
    return cur_h[0] >= x && cur_h[1] >= x;
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