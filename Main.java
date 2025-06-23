import java.io.*;
import java.util.*;

public class Main {
  static FastReader in = new FastReader();
  static PrintWriter out = new PrintWriter(System.out);
  static int mod = (int) 1e9 + 7;

  public static void main(String[] args) {
    int t = 1;
    while (t-- > 0) {
      solve();
    }
    out.flush();
  }

  static void solve() {
    
  }

  static long modDiv(long x, long y, long mod) {
    // x * y^(MOD-2) % MOD
    return (x * modPow(y, mod - 2, mod)) % mod;
  }

  static long modPow(long base, long exp, long mod) {
    long result = 1;
    base = base % mod;
    while (exp > 0) {
      if ((exp & 1) == 1) {
        result = (result * base) % mod;
      }
      base = (base * base) % mod;
      exp >>= 1;
    }
    return result;
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
