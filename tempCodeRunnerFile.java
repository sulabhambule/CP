import java.io.*;
import java.util.*;

public class TLE {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FastReader in = new FastReader();
  private static final int MOD = (int) 1e9 + 7;

  public static void main(String[] args) throws Exception {
    int t = in.nextInt();
    while (t-- > 0) {
      TLAM();
    }
    out.close();
  }

  private static void TLAM() {
    long n = in.nextLong();
    int d = in.nextInt();
    List<Integer> result = new ArrayList<>();
    if (n >= 4) {
      result.add(1);
      result.add(3);
      if (d == 5) {
        result.add(5);
      }
      result.add(7);
      if (d != 4 && d != 2 && d != 8) {
        result.add(9);
      }
    } else {
      fun(result, n, d);
    }
    Collections.sort(result);
    for (int i : result) {
      System.out.print(i + " ");
    }
    System.out.println();
  }

  private static void fun(List<Integer> result, long n, int d) {
    result.add(1);
    String s = "";
    int nn;
    if (n == 2) {
      nn = 2;
    } else {
      nn = 6;
    }
    for (int i = 1; i <= nn; i++) {
      s += d;
    }
    // System.out.println(s);
    for (int i = 3; i <= 9; i++) {
      if (i % 2 != 0) {
        long num = Integer.parseInt(s);
        if (num % i == 0) {
          result.add(i);
        }
      }
    }
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
