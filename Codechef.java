import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FastReader in = new FastReader();

  // static long MOD = (long)1e9 + 7;
  public static void main(String[] args) throws java.lang.Exception {
    int t = in.nextInt();
    while (t-- > 0) {
      Accepted();
    }
    // out.flush();
    out.close();
  }

  private static void Accepted() {
    int n = in.nextInt(), max = 0;
    int[] a = new int[n], b = new int[n], sum = new int[n + 1], p = new int[n];
    for (int i = 0; i < n; i++) {
      p[i] = i + 1;
    }
    for (int i = 0; i < n; i++) {
      a[i] = in.nextInt();
    }

    for (int i = 0; i < n; i++) {
      b[i] = (n + 1) - a[i];
    }
    for (int i = 0; i < n; i++) {
      sum[b[i]]++;
      if (sum[b[i]] > max)
        max = sum[b[i]];
    }

    if (max != n) {
      for (int i = 0; i < n - 1; i++) {
        if (p[i] == b[i]) {
          int t = p[i];
          p[i] = p[i + 1];
          p[i + 1] = t;
        }
      }
      if (p[n - 1] == b[n - 1]) {
        for (int j = 0; j < n - 1; j++) {
          if (b[j] != b[n - 1]) {
            int t = p[n - 1];
            p[n - 1] = p[j];
            p[j] = t;
            break;
          }
        }
      }
      for (int i = 1; i <= n; i++) {
        out.print(p[i - 1] + " ");
      }
      out.println();
    } else {
      out.println("-1");
    }
  }

  public static long fun(int rem, List<Long> ls) {
    long sum = 0;
    for (int i = 0; i < rem; i++)
      sum += ls.get(i);
    return sum;
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
