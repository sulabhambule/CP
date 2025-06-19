import java.io.*;
import java.util.*;

public class Main {
  static FastReader in = new FastReader();
  static PrintWriter out = new PrintWriter(System.out);

  public static void main(String[] args) {
    int t = in.nextInt();
    while (t-- > 0) {
      int n = in.nextInt();
      long sum = 0;
      long[] a = new long[n];
      for (int i = 0; i < n; i++) {
        a[i] = in.nextLong();
        sum += a[i];
      }
      HashMap<Long, Integer> map = new HashMap<>();
      long ans = 0;
      long prod = sum * (n - 2);
      if (prod % n != 0) {
        out.println(0);
        continue;
      }
      long tar = sum - (prod / n);
      for (long i : a) {
        ans += map.getOrDefault(tar - i, 0);
        map.put(i, map.getOrDefault(i, 0) + 1);
      }
      out.println(ans);
    }
    out.flush();
  }

  static class FastReader {
    BufferedReader br;
    StringTokenizer st;

    FastReader() {
      br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
      while (st == null || !st.hasMoreTokens()) {
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException e) {
          throw new RuntimeException(e);
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
