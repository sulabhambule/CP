import java.io.*;
import java.util.*;

public class TLE {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FastReader in = new FastReader();
  // private static final int MOD = (int) 1e9 + 7;
  private static final int MOD = 998244353;

  public static void main(String[] args) {
    int T = in.nextInt();
    while (T-- > 0) {
      solve();
    }
    out.close();
  }

  private static void solve() {
    long a = in.nextLong();
    long b = in.nextLong();
    long r = in.nextLong();
    if (a > b) {
      long temp = a;
      a = b;
      b = temp;
    }
    boolean firstBit = true;
    long x = 0;

    for (int i = 60; i >= 0; i--) {
      if (((1L << i) & a) == ((1L << i) & b)) {
        continue;
      }

      if (firstBit) {
        firstBit = false;
      } else {
        if (((1L << i) & a) == 0 && (x | (1L << i)) <= r) {
          a ^= (1L << i);
          b ^= (1L << i);
          x |= (1L << i);
        }
      }
    }
    System.out.println(b - a);
  }

  // static class Pair {
  // int first, second;

  // Pair(int first, int second) {
  // this.first = first;
  // this.second = second;
  // }
  // @Override
  // public boolean equals(Object obj) {
  // if (obj == this)
  // return true;
  // if (!(obj instanceof Pair))
  // return false;
  // Pair pair = (Pair) obj;
  // return pair.first == this.first && pair.second == this.second;
  // }
  // @Override
  // public int hashCode() {
  // return Objects.hash(first, second);
  // }
  // }

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