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
    int n = in.nextInt();
    int m = in.nextInt();
    int[] prefix = new int[n + 1];
    String s = in.next();
    for (int i = 0; i < n; i++) {
      if (s.charAt(i) == '0') {
        prefix[i + 1] = 1 + prefix[i];
      } else {
        prefix[i + 1] += prefix[i];
      }
    }
    boolean[] leftSorted = new boolean[n];
    boolean[] rightSorted = new boolean[n];

    precomputeSortedInfo(s, leftSorted, rightSorted);

    int[][] arr = new int[m][2];
    for (int i = 0; i < m; i++) {
      int l = in.nextInt();
      int r = in.nextInt();
      arr[i][0] = l;
      arr[i][1] = r;
    }
    Arrays.sort(arr, (x, y) -> {
      if (x[0] == y[0]) {
        return Integer.compare(x[1], y[1]);
      }
      return Integer.compare(x[0], y[0]);
    });
    int ans = m;
    HashMap<Integer, Integer> map = new HashMap<>();
    int count = 0;
    for (int i = 0; i < m; i++) {
      int l = arr[i][0];
      int r = arr[i][1];
      int sum = prefix[r] - prefix[l - 1];
      if (l == r || isSortedInRange(leftSorted, rightSorted, l - 1, r - 1)) {
        count++;
      }
      if (map.containsKey(l)) {
        if (map.get(l) == sum) {
          ans--;
        }
      }
      map.put(l, sum);
    }
    System.out.println(count);
    ans -= (Math.max(0, count - 1));
    System.out.println(ans);
  }

  public static void precomputeSortedInfo(String binaryString, boolean[] leftSorted, boolean[] rightSorted) {
    int n = binaryString.length();
    leftSorted[0] = true;
    for (int i = 1; i < n; i++) {
      if (binaryString.charAt(i) >= binaryString.charAt(i - 1)) {
        leftSorted[i] = leftSorted[i - 1];
      } else {
        leftSorted[i] = false;
      }
    }

    rightSorted[n - 1] = true;
    for (int i = n - 2; i >= 0; i--) {
      if (binaryString.charAt(i) <= binaryString.charAt(i + 1)) {
        rightSorted[i] = rightSorted[i + 1];
      } else {
        rightSorted[i] = false;
      }
    }
  }

  public static boolean isSortedInRange(boolean[] leftSorted, boolean[] rightSorted, int l, int r) {
    if (l == 0) {
      return rightSorted[r];
    }
    return leftSorted[l - 1] && rightSorted[r];
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
