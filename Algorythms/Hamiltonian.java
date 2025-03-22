
import java.io.*;
import java.util.*;

// cf problem link : https://codeforces.com/contest/1766/problem/C

public class Hamiltonian {
  static FastReader in;
  static PrintWriter out;
  static int MOD = (int) 1e9 + 7;

  static void solve() {
    int n = in.nextInt();
    char[][] ss = new char[2][n];
    for (int i = 0; i < 2; i++) {
      String s = in.next();
      for (int j = 0; j < n; j++) {
        ss[i][j] = s.charAt(j);
      }
    }
    int pos = -1;
    for (int i = 0; i < n; i++) {
      if (ss[0][i] != ss[1][i]) {
        pos = i;
      }
    }
    if (pos == -1) {
      out.println("YES");
      return;
    }
    boolean ok = true;
    int curr = (ss[0][pos] == 'B' ? 0 : 1);
    for (int i = pos + 1; i < n; i++) {
      if (ss[curr][i] == 'W') {
        ok = false;
      }
      if (ss[curr ^ 1][i] == 'B') {
        curr ^= 1;
      }
    }
    curr = (ss[0][pos] == 'B' ? 0 : 1);
    for (int i = pos - 1; i >= 0; i--) {
      if (ss[curr][i] == 'W') {
        ok = false;
      }
      if (ss[curr ^ 1][i] == 'B') {
        curr ^= 1;
      }
    }
    out.println(ok ? "YES" : "NO");
  }

  public static void main(String[] args) {
    in = new FastReader();
    out = new PrintWriter(System.out);
    int t = in.nextInt();
    while (t-- > 0) {
      solve();
    }
    out.close();
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