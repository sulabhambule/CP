import java.io.*;
import java.util.*;

public class TLE {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FastReader in = new FastReader();
  static long MOD = (long) 1e9 + 7;
  // static long MOD = (long) 998244353;

  public static void main(String[] args) throws Exception {
    int cf = 1;
    sieve((int) 1e6 + 2);
    while (cf-- > 0) {
      Accepted();
    }
    out.close();
  }

  private static void Accepted() {
    int n = in.nextInt();
    if (n > 2) {
      System.out.println(2);
    } else {
      System.out.println(1);
    }
    for (int i = 2; i <= n + 1; i++) {
      if (isPrime[i]) {
        System.out.print(1 + " ");
      } else {
        System.out.print(2 + " ");
      }
    }
  }

  static boolean[] isPrime;
  static ArrayList<Integer> primes;

  public static void sieve(int n) {
    isPrime = new boolean[n + 1];
    primes = new ArrayList<>();
    Arrays.fill(isPrime, true);
    isPrime[0] = false;
    isPrime[1] = false;
    for (int i = 2; i * i <= n; i++) {
      if (isPrime[i]) {
        for (int j = i * i; j <= n; j += i) {
          isPrime[j] = false;
        }
      }
    }
    for (int i = 2; i <= n; i++) {
      if (isPrime[i]) {
        primes.add(i);
      }
    }
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
