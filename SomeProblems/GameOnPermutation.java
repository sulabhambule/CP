// problem link : https://codeforces.com/contest/1860/problem/C
// rating 1400

import java.io.*;
import java.util.*;

public class GameOnPermutation {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FastReader in = new FastReader();
  static long MOD = (long) 1e9 + 7;
  // static long MOD = (long) 998244353;

  public static void main(String[] args) throws Exception {
    int cf = in.nextInt();
    while (cf-- > 0) {
      Accepted();
    }
    out.close();
  }

  private static void Accepted() {
    int n = in.nextInt();
    int[] p = new int[n];
    for (int i = 0; i < n; i++) {
      p[i] = in.nextInt();
    }
    int ans = 0;
    int mn = n + 1, mnWin = n + 1;
    for (int i = 0; i < n; i++) {
      if (p[i] > mn && p[i] < mnWin) {
        ans++;
        mnWin = p[i];
      }
      mn = Math.min(mn, p[i]);
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
