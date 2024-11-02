
/*            || जय श्री राम ||           */

import java.io.*;
import java.util.*;

// Author : Sulabh Ambule

public class TLE {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  // static long MOD = (long) (1e9 + 7);
  static long MOD = 998244353;
  static FastReader in = new FastReader();

  public static void main(String[] args) throws Exception {
    // int WrongAnswer = in.nextInt();
    // while (WrongAnswer-- > 0) {
    Accepted();
    // }
    out.flush();
    out.close();
  }

  private static void Accepted() {
    int a = in.nextInt();
    int b = in.nextInt();
    int c = in.nextInt();

    if (canBeExpressed(a, b, c)) {
      out.println("YES");
    } else {
      out.println("NO");
    }
  }


  public static long gcd(long a, long b) {
    if (a == 0)
      return b;
    return gcd(b % a, a);
  }


  private static boolean canBeExpressed(int a, int b, int c) {
    for (int x = 0; x <= c / a; x++) {
      int remainder = c - x * a;
      if (remainder % b == 0) {
        return true;
      }
    }
    return false;
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

  static class Pair {
    long first;
    long second;

    Pair(long f, long s) {
      this.first = f;
      this.second = s;
    }
  }
}