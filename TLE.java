
import java.io.*;
import java.util.*;

// Author : Sulabh Ambule
public class TLE {

  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static long MOD = (long) (1e9 + 7);
  // static long MOD = 998244353;
  static FastReader in = new FastReader();

  public static void main(String[] args) throws Exception {
    // int cf = in.nextInt();
    // while (cf-- > 0) {
      Accepted();
    // }
    out.flush();
    out.close();
  }

  /*
   * 
   * 
   * || जय श्री राम ||
   * 
   * 
   */
  private static void Accepted() {
    int n = in.nextInt();
    int[] a = new int[n];
    for (int i = 0; i < a.length; i++) {
      a[i] = in.nextInt();
    } 
    int m = in.nextInt();
    int[] b = new int[m];
    for (int i = 0; i < b.length; i++) {
      b[i] = in.nextInt();
    }

    Arrays.sort(a);
    Arrays.sort(b);

    int i = 0, j = 0;
    int ans = 0;
    while(i < n && j < m) {
      if(Math.abs(a[i] - b[j]) <= 1) {
        ans++;
        i++;
        j++;
      } else if(a[i] < b[j]) {
        i++;
      } else {
        j++;
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

  // static class Pair {
  // long first;
  // long second
  // Pair(long f, long s) {
  // this.first = f;
  // this.second = s;
  // }
  // }

  // static class Pair implements Comparable<Pair> {
  // long first;
  // long second;

  // Pair(long first, long x) {
  // this.first = first;
  // this.second = x;
  // }

  // @Override
  // public int compareTo(Pair other) {
  // return Long.compare(this.second, other.second);
  // }
  // }

}
