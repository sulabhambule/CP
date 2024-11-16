
import java.io.*;
import java.util.*;

// Author: Sulabh Ambule
public class XORpalindrome {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FastReader in = new FastReader();

  public static void main(String[] args) throws Exception {
    int cf = in.nextInt();
    while (cf-- > 0) {
      Accepted();
    }
    out.flush();
    out.close();
  }

  // Lets, hope for best?

  private static void Accepted() {
    int n = in.nextInt();
    long[] a = new long[n];
    long max = 0, countEven = 0, countOdd = 0;
    for (int i = 0; i < n; i++) {
      a[i] = in.nextLong();
      if (a[i] % 2 == 1) {
        countOdd++;
        max = Math.max(max, a[i]);
      } else {
        countEven++;
      }
    }

    if (countEven == n || countOdd == n || n == 1) {
      System.out.println(0);
      return;
    }

    Arrays.sort(a);
    int ans = 0;

    for (long num : a) {
      if (num % 2 == 0) {
        if (max > num) {
          ans++;
          max += num;
        } else {
          ans += 2;
          max += 2 * num;
        }
      }
    }

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
