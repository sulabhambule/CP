import java.util.*;

public class countPalSubs {

  static int mod = (int) 1e9 + 7;

  public int countPalindromicSubsequences(String s) {
    int n = s.length();
    long[][] dp = new long[n][n];
    int[] next = new int[n], prev = new int[n];
    HashMap<Character, Integer> map = new HashMap<>();

    for (int i = 0; i < n; i++) {
      char ch = s.charAt(i);
      if (!map.containsKey(ch)) {
        prev[i] = -1;
      } else {
        prev[i] = map.get(ch);
      }
      map.put(ch, i);
    }
    map.clear();
    for (int i = n - 1; i >= 0; i--) {
      char ch = s.charAt(i);
      if (!map.containsKey(ch)) {
        next[i] = -1;
      } else {
        next[i] = map.get(ch);
      }
      map.put(ch, i);
    }

    for (int gap = 0; gap < n; gap++) {
      for (int i = 0, j = gap; j < n; i++, j++) {
        if (gap == 0) {
          dp[i][j] = 1;
        } else if (gap == 1) {
          dp[i][j] = 2;
          // there are 3 pal subs. but the 2 are unique.
        } else {
          char start = s.charAt(i);
          char end = s.charAt(j);

          if (start != end) {
            dp[i][j] = ((dp[i + 1][j] + dp[i][j - 1]) % mod
                - dp[i + 1][j - 1] + mod) % mod;
            // for more understanding refer the 2D gap table of string.
          } else {
            int nxtIdx = next[i];
            int pvIdx = prev[j];
            if (nxtIdx > pvIdx) {
              dp[i][j] = (((2 * dp[i + 1][j - 1]) % mod) + 2) % mod;
            } else if (nxtIdx == pvIdx) {
              dp[i][j] = (((2 * dp[i + 1][j - 1]) % mod) + 1) % mod;
            } else {
              dp[i][j] = ((2 * dp[i + 1][j - 1]) % mod -
                  dp[nxtIdx + 1][pvIdx - 1] + mod) % mod;
            }
          }
        }
      }
    }

    return (int) dp[0][n - 1] % mod;
  }
}