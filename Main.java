import java.io.*;
import java.util.*;

public class Main {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FastReader in = new FastReader();
  static int mod = (int) 1e9 + 7;
  static final int inf = (int) 1e9;
  static final long INF = (long) 1e18;

  public static void main(String[] args) {
    int t = in.nextInt();
    while (t-- > 0) {
      solve();
    }
    out.flush();
  }

  static void solve() {
    int n = in.nextInt();
    long[] a = new long[n];
    List<List<Integer>> ls = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      a[i] = in.nextLong();
    }

    int ans = 0;
    int[] left = new int[n];
    fun(a, left);
    int index = 0;
    int r = -1;
    int[] right = new int[n];
    right[n - 1] = n - 1;
    for (int i = n - 2; i >= 0; i--) {
      if (a[i + 1] > a[i]) {
        right[i] = i;
      } else {
        right[i] = right[i + 1];
      }
    }
    int l = 0;

    for (int i = 0; i < n; i++) {
      ls.add(new ArrayList<>());
    }
    for (int i = 0; i < n; i++) {
      ls.get(left[i]).add(right[i]);
    }

    while (index < n) {
      while (l <= index) {
        for (int e : ls.get(l)) {
          if (e > r) {
            r = e;
          }
        }
        l++;
      }
      ans++;
      index = r + 2 - 1;
    }

    out.println(ans);
  }

  static void fun(long[] a, int[] left) {
    left[0] = 0;
    int n = a.length;
    for (int i = 1; i < n; i++) {
      if (a[i - 1] > a[i]) {
        left[i] = i;
      } else {
        left[i] = left[i - 1];
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
  }
}
