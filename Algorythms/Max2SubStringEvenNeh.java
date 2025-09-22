
import java.io.*;
import java.util.*;

// cf link : https://codeforces.com/problemset/problem/1660/D

public class Max2SubStringEvenNeh {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FASTIO in = new FASTIO();
  static long INF = Long.MAX_VALUE / 2;
  static int inf = Integer.MAX_VALUE / 2;
  static int mod = (int) 1e9 + 7;

  public static void main(String[] args) {
    int t = in.nextInt();
    while (t-- > 0) {
      solve();
    }
    out.flush();
    out.close();
  }

  static void solve() {
    int n = in.nextInt();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = in.nextInt();
    }

    int ans = 0;
    int ap = n, as = 0;

    for (int i = 0, l = -1; i <= n; i++) {
      if (i == n || a[i] == 0) {
        int cnt = 0;
        boolean neg = false;
        int left = -1, right = -1;
        int cl = 0, cr = 0;

        for (int j = l + 1; j < i; j++) {
          neg ^= a[j] < 0;
          if (a[j] < 0) {
            right = j;
            cr = 0;
          }

          if (Math.abs(a[j]) == 2) {
            cnt++;
            cr++;
            if (left == -1)
              cl++;
          }

          if (a[j] < 0 && left == -1) {
            left = j;
          }
        }

        if (neg) {
          if (cnt - cl > cnt - cr) {
            right = i;
            cnt -= cl;
          } else {
            left = l;
            cnt -= cr;
          }
        } else {
          left = l;
          right = i;
        }

        if (ans < cnt) {
          ans = cnt;
          ap = left + 1;
          as = n - right;
        }

        l = i;
      }
    }

    out.println(ap + " " + as);
  }

  static class FASTIO {
    BufferedReader br;
    StringTokenizer st;

    public FASTIO() {
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
        st = null;
        str = br.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return str;
    }
  }
}