
import java.io.*;
import java.util.*;

// link : https://www.spoj.com/problems/PR003004/

// For a pair of integers a and b, the digit sum of the interval [a, b] is defined as the sum of all digits occurring in all numbers between (and including) a and b. 

public class Sum {
  static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static Scanner in = new Scanner(System.in);

  static long[][][] dp;

  static void solve() {
    long a = in.nextLong();
    long b = in.nextLong();
    if (a != 0) {
      a--;
    }
    String L = Long.toString(a);
    String R = Long.toString(b);

    dp = new long[R.length() + 1][2][3000];
    for (long[][] row : dp) {
      for (long[] inner : row) {
        Arrays.fill(inner, -1);
      }
    }
    long sumR = fun(0, 1, 0, R);

    dp = new long[L.length() + 1][2][3000];
    for (long[][] row : dp) {
      for (long[] inner : row) {
        Arrays.fill(inner, -1);
      }
    }
    long sumL = fun(0, 1, 0, L);

    out.println(sumR - sumL);
  }

  public static long fun(int pos, int tight, int sum, String str) {
    if (pos == str.length())
      return sum;
    if (dp[pos][tight][sum] != -1)
      return dp[pos][tight][sum];
    long ans = 0;
    int limit = (tight == 1) ? str.charAt(pos) - '0' : 9;
    for (int d = 0; d <= limit; d++) {
      int newTight = (tight == 1 && d == limit) ? 1 : 0;
      ans += fun(pos + 1, newTight, sum + d, str);
    }
    return dp[pos][tight][sum] = ans;
  }

  public static void main(String[] args) {
    int t = in.nextInt();

    while (t-- > 0) {
      solve();
    }
    out.close();
  }
}

