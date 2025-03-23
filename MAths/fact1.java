package Maths;
// atcoder problem Link: https://atcoder.jp/contests/abc148/tasks/abc148_e

// Heree the f(n) - n * f(n - 2).
// and we need to find out the number of trailing zeros in the f(n).

public class fact1 {
  public static void main(String[] args) {

  }

  private static void Accepted() {
    long n = (long) 1e18;
    if (n % 2 == 1) {
      System.out.println(0);
      return;
    }

    long ans = 0;
    long base = 10;
    while (base <= n) {
      ans += n / base;
      base *= 5;
    }
    System.out.println(ans);
  }

  // if we neea to find out the number o trailing zeros in factorial of n. n!
  // then the code is

  public static int findTrailingZeros(int n) {
    int count = 0;

    // Count factors of 5 in the range
    for (int i = 5; n / i >= 1; i *= 5) {
      count += n / i;
    }

    return count;
  }
}
