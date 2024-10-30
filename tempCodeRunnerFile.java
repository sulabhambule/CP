/* || JAI SHREE RAM || */

import java.io.*;
import java.util.*;

// Author : Sulabh Ambule

public class TLE {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static long MOD = (long) (1e9 + 7);
  // static long MOD = 998244353;
  static FastReader in = new FastReader();

  public static void main(String[] args) throws Exception {
    int cases = in.nextInt();
    while (cases-- > 0) {
      Accepted();
    }
    out.flush();
    out.close();
  }

  private static void Accepted() {
    int n = in.nextInt();
    int[] a = new int[n];
    String s = in.next();
    for (int i = 0; i < a.length; i++) {
      a[i] = (s.charAt(i) == '1') ? 1 : 0;
    }
    int ans = 0;
    long sum = n * (n + 1) / 2;
    for (int i = n - 1; i >= 3; i--) {
      if (a[i] == 1) {
        sum -= (i + 1);
      }
    }

    System.out.println(ans);
  }

  private static boolean isPossible(long k, long[] a) {
    int n = a.length;
    int count = 0;
    long lastPainted = -1;

    for (int i = 0; i < n; i++) {
      // If the current cell can be painted without exceeding k
      if (lastPainted == -1 || a[i] - lastPainted > k) {
        if (count == 1) { // If one additional cell is already used
          return false; // Can't paint this cell
        }
        lastPainted = a[i]; // Paint the current cell
        count++; // Increment painted count
      }
    }

    return true;

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