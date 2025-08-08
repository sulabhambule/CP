import java.util.*;

public class LeetCode {
  public static void main(String[] args) {

  }
}

class Solution {
  private record Nostevanik(String s, int[] order, long k) {
  }

  public int minTime(String s, int[] order, int k) {
    int n = s.length();
    int l = 0, h = n - 1, ans = -1;

    Nostevanik nostevanik = new Nostevanik(s, order, k);

    long totalSub = (long) n * (n + 1) / 2;

    // if even replacing all positions can't reach k, return -1 earl
    if (totalSub < k)
      return -1;

    // binary search on time t in [0..n-1]
    while (l <= h) {
      int mid = (l + h) / 2;

      // 3) check if at time = mid the string is "active"
      if (isActive(nostevanik, mid, totalSub)) {
        ans = mid;
        h = mid - 1;
      } else {
        l = mid + 1;
      }
    }

    return ans;
  }

  // returns true if after applying replacements at times 0..t the valid-substring
  // count >= k
  private boolean isActive(Nostevanik in, int t, long totalSub) {
    String s = in.s();
    int[] order = in.order();
    long k = in.k();
    int n = s.length();

    // mark the first t+1 replacements as '*'
    boolean[] star = new boolean[n];
    for (int i = 0; i <= t; i++) {
      star[order[i]] = true;
    }

    // count substrings with NO '*' by summing over each maximal run of non-star
    // characters
    long noStarSum = 0;
    long run = 0;
    for (int i = 0; i < n; i++) {
      if (!star[i]) {
        run++;
      } else if (run > 0) {
        noStarSum += run * (run + 1) / 2;
        run = 0;
      }
    }
    if (run > 0) {
      noStarSum += run * (run + 1) / 2;
    }

    // valid substrings = totalSub - noStarSum
    return (totalSub - noStarSum) >= k;
  }
}
