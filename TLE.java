import java.io.*;
import java.util.*;
// Author : Sulabh Ambule

public class TLE {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static long MOD = (long) (1e9 + 7);
  // static long MOD = 998244353;
  static FastReader in = new FastReader();

  public static void main(String[] args) throws Exception {
    init();
    int cases = in.nextInt();
    while (cases-- > 0) {
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

  static int max = (int) 1e5;
  static ArrayList<Integer>[] factors = new ArrayList[max + 5];

  private static void init() {
    for (int i = 0; i <= max; i++) {
      factors[i] = new ArrayList<>();
    }

    for (int i = 1; i <= max; i++) {
      for (int j = i; j <= max; j += i) {
        factors[j].add(i);
      }
    }
  }

  private static void Accepted() {
    int n = in.nextInt();
    int m = in.nextInt();
    int[] a = new int[n];
    for (int i = 0; i < a.length; i++) {
      a[i] = in.nextInt();
    }

    Arrays.sort(a);

    int l = 0;
    int ans = Integer.MAX_VALUE;
    int count = 0;
    int[] freq = new int[max + 1];

    for (int r = 0; r < n; r++) {
      for (int x : factors[a[r]]) {
        if (x > m)
          break;
        freq[x]++;
        if (freq[x] == 1) {
          count++;
        }
      }

      while (count == m) {
        ans = Math.min(ans, a[r] - a[l]);

        for (int x : factors[a[l]]) {
          if (x > m)
            break;
          freq[x]--;
          if (freq[x] == 0) {
            count--;
          }
        }
        l++;
      }
    }

    if (ans == Integer.MAX_VALUE) {
      out.println(-1);
    } else {
      out.println(ans);
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