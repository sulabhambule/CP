import java.io.*;
import java.util.*;

public class TLE {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FastReader in = new FastReader();
  private static final int MOD = (int) 1e9 + 7;

  public static void main(String[] args) throws Exception {
    int t = in.nextInt();
    while (t-- > 0) {
      TLAM();
    }
    out.close();
  }

  private static void TLAM() {
    int n = in.nextInt();
    long[] a = new long[n];
    for (int i = 0; i < n; i++) {
      a[i] = in.nextLong();
    }
    int pos = -1;
    for (int i = 0; i < n; i++) {
      if (a[i] != -1 && a[i] != 1) {
        pos = i;
        break;
      }
    }

    if (pos == -1) {
      long left = minSubarraySum(a, 0, n - 1);
      long right = maxSubarraySum(a, 0, n - 1);
      System.out.println(right - left + 1);
      for (long i = left; i <= right; i++) {
        System.out.print(i + " ");
      }
      System.out.println();
    } else {
      long left1 = minSubarraySum(a, 0, pos - 1);
      long left2 = maxSubarraySum(a, 0, pos - 1);
      long right1 = minSubarraySum(a, pos + 1, n - 1);
      long right2 = maxSubarraySum(a, pos + 1, n - 1);

      HashSet<Long> set = new HashSet<>();
      for (long i = left1; i <= left2; i++) {
        set.add(i);
      }
      for (long i = right1; i <= right2; i++) {
        set.add(i);
      }

      long leftMin = 0, leftMax = 0, rightMin = 0, rightMax = 0;
      long curr = 0;
      for (int i = pos - 1; i >= 0; i--) {
        curr += a[i];
        leftMin = Math.min(leftMin, curr);
        leftMax = Math.max(leftMax, curr);
      }
      curr = 0;
      for (int i = pos + 1; i < n; i++) {
        curr += a[i];
        rightMax = Math.max(rightMax, curr);
        rightMin = Math.min(rightMin, curr);
      }

      for (long i = leftMin + rightMin; i <= leftMax + rightMax; i++) {
        set.add(i + a[pos]);
      }

      System.out.println(set.size());
      List<Long> ans = new ArrayList<>();
      for (long i : set) {
        ans.add(i);
      }
      Collections.sort(ans);
      for (long i : ans) {
        System.out.print(i + " ");
      }
      System.out.println();
    }
  }

  private static long maxSubarraySum(long[] a, int left, int right) {
    long curr = 0, maxSum = 0;
    for (int i = left; i <= right; i++) {
      curr += a[i];
      maxSum = Math.max(maxSum, curr);
      if (curr < 0) {
        curr = 0;
      }
    }
    return maxSum;
  }

  private static long minSubarraySum(long[] a, int left, int right) {
    long curr = 0, maxSum = 0;
    for (int i = left; i <= right; i++) {
      curr -= a[i];
      maxSum = Math.max(maxSum, curr);
      if (curr < 0) {
        curr = 0;
      }
    }
    return -maxSum;
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
  }
}
