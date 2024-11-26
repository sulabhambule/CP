import java.io.*;
import java.util.*;

public class TLE {
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
    int k = in.nextInt();
    int count = 0;
    int[][] arr = new int[n][2];
    for (int i = 0; i < n; i++) {
      arr[i][0] = in.nextInt();
      arr[i][1] = in.nextInt();
    }
    int cm = 0;
    for (int i = 0; i < n; i++) {
      int l = arr[i][0];
      int r = arr[i][1];
      if (k >= l && k <= r) {
        cm++;
      } else {
        count++;
      }
    }
    if (count == n) {
      System.out.println("NO");
      return;
    }
    int a1 = 0, a2 = 0;
    for (int i = 0; i < n; i++) {
      int l = arr[i][0];
      int r = arr[i][1];
      if (l == k) {
        a1 = 1;
      }
      if (r == k) {
        a2 = 1;
      }
    }
    if (a1 == 1 && a2 == 1) {
      System.out.println("YES");
    } else {
      System.out.println("NO");
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
