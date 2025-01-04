import java.io.*;
import java.util.*;

public class TLE {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FastReader in = new FastReader();
  // private static final int MOD = (int) 1e9 + 7;
  private static final int MOD = 998244353;

  public static void main(String[] args) {
    int T = 1;
    while (T-- > 0) {
      solve();
    }
    out.close();
  }

  private static void solve() {
    int n = in.nextInt();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = in.nextInt();
    }
    String ans = "";
    int prev = 0;
    int l = 0, r = n - 1;
    while (l <= r) {
      if (a[l] < a[r]) {
        if (a[l] > prev) {
          prev = a[l];
          l++;
          ans += 'L';
        } else if (a[r] > prev) {
          prev = a[r];
          r--;
          ans += 'R';
        } else {
          break;
        }
      } else if (a[r] < a[l]) {
        if (a[r] > prev) {
          prev = a[r];
          r--;
          ans += 'R';
        } else if (a[l] > prev) {
          prev = a[l];
          l++;
          ans += 'L';
        } else {
          break;
        }
      } else if (a[l] == a[r] && a[l] > prev) {
        prev = a[l];
        int ll = l, rr = r;
        int lPrev = prev, rPrev = prev;
        for (int k = l + 1; k < r; k++) {
          if (a[k] > lPrev) {
            ll++;
            lPrev = a[k];
          } else {
            break;
          }
        }
        for (int k = r - 1; k > l; k--) {
          if (a[k] > rPrev) {
            rr++;
            rPrev = a[k];
          } else {
            break;
          }
        }

        if (ll <= rr) {
          ans += 'L';
          for (int k = l + 1; k < r; k++) {
            if (a[k] > prev) {
              prev = a[k];
            } else {
              break;
            }
          }
          break;
        } else {
          for (int k = r - 1; k > l; k--) {
            if (a[k] > prev) {
              prev = a[k];
            } else {
              break;
            }
          }
          break;
        }
      } else {
        break;
      }
    }

    System.out.println(ans.length());
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
  }
}
