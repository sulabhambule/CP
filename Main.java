import java.io.*;
import java.util.*;

public class Main {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FastReader in = new FastReader();

  public static void main(String[] args) throws Exception {
    int cf = in.nextInt();
    while (cf-- > 0) {
      Accepted();
    }
    out.close();
  }

  private static void Accepted() {
    int n = in.nextInt();
    int[] ar = new int[n];
    int[] pref = new int[n];
    int[] suff = new int[n];
    int[] ans = new int[n];
    for (int i = 0; i < n; i++) {
      ar[i] = in.nextInt();
    }
    pref[0] = ar[0];
    for (int i = 1; i < n; i++) {
      pref[i] = Math.max(pref[i - 1], ar[i]);
    }
    suff[n - 1] = ar[n - 1];
    for (int i = n - 2; i >= 0; i--) {
      suff[i] = Math.min(suff[i + 1], ar[i]);
    }
    ans[n - 1] = pref[n - 1];
    for (int i = n - 2; i >= 0; i--) {
      if (pref[i] > suff[i + 1]) {
        ans[i] = ans[i + 1];
      } else {
        ans[i] = pref[i];
      }
    }
    for (int i = 0; i < n; i++) {
      System.out.print(ans[i] + " ");
    }
    System.out.println();
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
