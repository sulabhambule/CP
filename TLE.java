
import java.io.*;
import java.util.*;

// Author : Sulabh Ambule
public class TLE {

  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static long MOD = (long) (1e9 + 7);
  // static long MOD = 998244353;
  static FastReader in = new FastReader();

  public static void main(String[] args) throws Exception {
    int cf = in.nextInt();
    while (cf-- > 0) {
      Accepted();
    }
    out.flush();
    out.close();
  }

  /*
   * 
   * 
   * || JAI SHREE RAM ||
   * 
   * 
   */
  private static void Accepted() {
    int n = in.nextInt();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = in.nextInt();
    }

    int[] leftMex = new int[n];
    boolean[] present = new boolean[n + 1];
    int currentMex = 0;

    for (int i = 0; i < n; i++) {
      present[a[i]] = true;

      while (currentMex <= n && present[currentMex]) {
        currentMex++;
      }
      leftMex[i] = currentMex;
    }

    int[] rightMex = new int[n];
    Arrays.fill(present, false);
    currentMex = 0;

    for (int i = n - 1; i >= 0; i--) {
      present[a[i]] = true;

      while (currentMex <= n && present[currentMex]) {
        currentMex++;
      }
      rightMex[i] = currentMex;
    }

    for (int i = 0; i < n - 1; i++) {
      if (leftMex[i] == rightMex[i + 1]) {
        out.println(2);
        out.println(1 + " " + (i + 1));
        out.println((i + 2) + " " + n);
        return;
      }
    }

    out.println(-1);
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

  // static class Pair {
  // long first;
  // long second
  // Pair(long f, long s) {
  // this.first = f;
  // this.second = s;
  // }
  // }

  static class Pair implements Comparable<Pair> {
    long first;
    long second;

    Pair(long first, long x) {
      this.first = first;
      this.second = x;
    }

    @Override
    public int compareTo(Pair other) {
      return Long.compare(this.second, other.second);
    }
  }

}
