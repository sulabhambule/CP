
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
   * || जय श्री राम ||
   * 
   * 
   */
  private static void Accepted() {
    int n = in.nextInt();
    long l = in.nextLong();
    long r = in.nextLong();
    List<Long> v = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      v.add(in.nextLong());
    }

    Collections.sort(v);
    long ans = 0;
    for (int i = 0; i < n; i++) {
      long upper = upperBound(r - v.get(i), v);
      long lower = lowerBound(l - v.get(i), v);
      ans += (upper - lower);

      if (2 * v.get(i) >= l && 2 * v.get(i) <= r) {
        ans--;
      }
    }

    System.out.println(ans / 2);
  }

  public static int upperBound(long key, List<Long> v) {
    int pos = Collections.binarySearch(v, key);
    if (pos >= 0) {
        pos++;
        while (pos < v.size() && v.get(pos) == key) {
            pos++;
        }
    } else {
        pos = -pos - 1;
    }
    return pos;
}
  
public static int lowerBound(long key, List<Long> v) {
    int pos = Collections.binarySearch(v, key);
    if (pos < 0) {
        pos = -pos - 1;
    }
    return pos;
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
