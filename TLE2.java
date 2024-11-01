
/**********|| JAI SHREE RAM ||**************/

import java.io.*;
import java.util.*;

// Author : Sulabh Ambule

public class TLE2 {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static long MOD = (long) (1e9 + 7);
  // static long MOD = 998244353;
  static FastReader in = new FastReader();

  public static void main(String[] args) throws Exception {
    int codeforces = in.nextInt();
    while (codeforces-- > 0) {
      Accepted();
    }
    out.flush();
    out.close();
  }

  private static void Accepted() {
    System.out.println(3 ^ 1);
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

// descrete log ke niche nhi krna hai
//