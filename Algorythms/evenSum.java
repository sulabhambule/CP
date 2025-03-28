/* Alice start the game, we have zero and ones
 * if at the end sum of alice numbers is even he wins else bob win
 * cf link : https://codeforces.com/contest/1738/problem/C
 */

import java.io.*;
import java.util.*;

public class evenSum {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FASTIO in = new FASTIO();
  static final int MOD = (int) 1e9 + 7;
  // static final int MOD = 998244353;
  static final int inf = (int) 1e9;

  public static void main(String[] Hi) throws IOException {
    int cp = in.nextInt();
    while (cp-- > 0) {
      solve();
    }

    out.close();
  }

  static void solve() {
    int n = in.nextInt();
    long[] a = new long[n];
    for (int i = 0; i < n; i++) {
      a[i] = in.nextLong();
    }
    int even = 0, odd = 0;
    for (long num : a) {
      if (num % 2 == 0) {
        even++;
      } else {
        odd++;
      }
    }
    if (odd % 4 == 2) {
      out.println("Bob");
    } else if (odd % 4 == 3) {
      out.println("Alice");
    } else if (odd % 4 == 0) {
      out.println("Alice");
    } else if (even % 2 == 1) {
      out.println("Alice");
    } else {
      out.println("Bob");
    }
  }

  /*---------------------------------------------------------------------------------------------------------*/

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
  }
}
