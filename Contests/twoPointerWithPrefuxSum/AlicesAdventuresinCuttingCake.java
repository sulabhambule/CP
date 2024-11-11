
import java.io.*;
import java.util.*;

// Author: Sulabh Ambule
public class AlicesAdventuresinCuttingCake {
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

  // Lets, hope for best.

  private static void Accepted() {
    int n = in.nextInt();
    int m = in.nextInt();
    long v = in.nextLong();
    long[] a = new long[n + 1];
    for (int i = 1; i < n + 1; i++)
      a[i] = in.nextLong();

    long[] prefSum = new long[n + 1];
    for (int i = 1; i < n + 1; i++) {
      prefSum[i] = prefSum[i - 1] + a[i];
    }
    List<Integer> v1 = new ArrayList<>();
    List<Integer> v2 = new ArrayList<>();
    v1.add(1);
    v2.add(n);
    long sum = 0;
    for (int i = 1; i < n + 1; i++) {
      sum += a[i];
      if (sum >= v) {
        v1.add(i + 1);
        sum = 0;
      }
    }
    sum = 0;
    for (int i = n; i >= 1; i--) {
      sum += a[i];
      if (sum >= v) {
        v2.add(i - 1);
        sum = 0;
      }
    }
    if (v1.size() - 1 < m) {
      System.out.println(-1);
      return;
    }
    long ans = 0;
    for (int i = 0; i < m + 1; i++) {
      int l = v1.get(i);
      int r = v2.get(m - i);
      long sum2 = prefSum[r] - prefSum[l - 1];
      ans = Math.max(ans, sum2);
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
