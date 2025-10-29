import java.util.*;

public class isPal {
  static final long BASE = 911382323;
  static final long MOD = 972663749;

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String s = in.next(); // original String
    String sRev = new StringBuilder(s).reverse().toString(); // reversed String

    HashInterval hs = new HashInterval(s);
    HashInterval hsRev = new HashInterval(sRev);

    int n = s.length();

    if (isPalindrome(hs, hsRev, n, 0, n - 1)) {
      System.out.println("palindrome");
    }
  }

  static class HashInterval {
    long[] hash, power;
    int n;

    HashInterval(String s) {
      n = s.length();
      hash = new long[n + 1];
      power = new long[n + 1];
      power[0] = 1;
      for (int i = 0; i < n; i++) {
        hash[i + 1] = (hash[i] * BASE + s.charAt(i)) % MOD;
        power[i + 1] = (power[i] * BASE) % MOD;
      }
    }

    long hashInterval(int l, int r) { // [l, r)
      long res = (hash[r] - hash[l] * power[r - l]) % MOD;
      if (res < 0)
        res += MOD;
      return res;
    }
  }

  static boolean isPalindrome(HashInterval hs, HashInterval hsRev, int n, int l, int r) {
    int lR = n - 1 - r;
    int rR = n - 1 - l;
    return hs.hashInterval(l, r + 1) == hsRev.hashInterval(lR, rR + 1);
  }
}
