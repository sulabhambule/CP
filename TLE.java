import java.io.*;
import java.util.*;

// Author : Sulabh Ambule
public class TLE {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static long MOD = (long) (1e9 + 7);
  // static long MOD = 998244353;
  // Codeforces Edu-Round 170 (Div 2)
  static FastReader in = new FastReader();

  public static void main(String[] args) throws Exception {
    // int cf = in.nextInt();
    // while (cf-- > 0) {
    Accepted();
    // }
    out.flush();
    out.close();
  }

  /*
   * 
   * || जय श्री राम ||
   * 
   */

  private static void Accepted() {
    int t = in.nextInt();
    int[] a = new int[t];
    for (int i = 0; i < a.length; i++) {
      a[i] = in.nextInt();
    }

    int[] b = new int[t];
    for (int i = 0; i < a.length; i++) {
      b[i] = in.nextInt();
    }

    for (int i = 0; i < t; i++) {
      System.out.println(binPow(2, b[i], MOD));
    }
  }

  static long binPow(long a, long b, long MOD) {
    long result = 1;
    a = a % MOD; // in case 'a' is larger than MOD

    while (b > 0) {
      if ((b & 1) == 1) {
        result = (result * a) % MOD;
      }
      a = (a * a) % MOD;
      b >>= 1;
    }
    return result;
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

  // static class Pair implements Comparable<Pair> {
  // long first;
  // long second;

  // Pair(long first, long x) {
  // this.first = first;
  // this.second = x;
  // }

  // @Override
  // public int compareTo(Pair other) {
  // return Long.compare(this.second, other.second);
  // }
  // }

}
