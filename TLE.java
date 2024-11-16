import java.io.*;
import java.util.*;

// Author: Sulabh Ambule
public class TLE {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FastReader in = new FastReader();
  static long MOD = (long)1e9 + 7;

  public static void main(String[] args) throws Exception {
    int cf = in.nextInt();
    while (cf-- > 0) {
      Accepted();
    }
    // out.flush();
    out.close();
  }

  // Lets, hope for best.

  private static void Accepted() {
    int r = in.nextInt();
    int c = in.nextInt();
    int k = in.nextInt();
    String h = in.next();
    String v = in.next();

    char[] hor = h.toCharArray();
    char[] ver = v.toCharArray();
    int a = 0, b = 0;
    for (char ch : hor) {
      if (ch == '#') {
        a++;
      }
    }
    for (char ch : ver) {
      if (ch == '#') {
        b++;
      }
    }
    if (k > a * b || Math.max(a, b) > k) {
      out.println("IMPOSSIBLE");
      return;
    }

    if (a == 0 && b == 0) {
      if (k == 0) {
        out.println("POSSIBLE");
        for (int i = 0; i < r; i++) {
          for (int j = 0; j < c; j++) {
            out.print('.');
          }
          out.println();
        }
        return;
      } else {
        out.println("IMPOSSIBLE");
        return;
      }
    }

    if (a == 0 || b == 0) {
      out.println("IMPOSSIBLE");
      return;
    }

    char[][] ans = new char[r][c];
    for (int i = 0; i < r; i++) {
      Arrays.fill(ans[i], '.');
    }

    char[] row = h.toCharArray();
    char[] cols = v.toCharArray();
    int max = a * b;
    for (int i = 0; i < r; i++) {
      if (k == 0)
        break;
      for (int j = 0; j < c; j++) {
        if (k == 0)
          break;
        if (hor[i] == '#' && ver[j] == '#') {
          int newa = a, newb = b;
          if (row[i] == '#')
            newa--;
          if (cols[j] == '#')
            newb--;

          if (Math.max(newa, newb) < k) {
            ans[i][j] = '@';
            a = newa;
            b = newb;
            k--;
            row[i] = '.';
            cols[j] = '.';
          }
        }
      }
    }

    if (k > 0) {
      System.out.println("IMPOSSIBLE");
      return;
    }

    out.println("POSSIBLE");
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        out.print(ans[i][j]);
      }
      out.println();
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
