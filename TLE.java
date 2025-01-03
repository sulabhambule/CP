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
    String s = in.next();
    int n = s.length();
    int left = 0;
    int right = n - 1;
    char[] arr = s.toCharArray();
    if (n == 1) {
      System.out.println(arr[0]);
      return;
    }
    Arrays.sort(arr);
    char[] ans = new char[n];

    for (int i = 0; i < n; i += 2) {
      if (i == n - 1) {
        ans[left] = arr[i];
        break;
      }
      if (arr[i] == arr[i + 1]) {
        ans[left] = arr[i];
        ans[right] = arr[i];
        left++;
        right--;
      } else {
        if (arr[i + 1] == arr[n - 1]) {
          int len = n - i - 1;
          int leftHalf = (len + 1) / 2;
          int rightHalf = len - leftHalf;
          for (int k = 0; k < leftHalf; k++) {
            ans[left++] = arr[i + 1];
          }
          ans[left++] = arr[i];
          for (int k = 0; k < rightHalf; k++) {
            ans[right--] = arr[i + 1];
          }
        } else {
          ans[right--] = arr[i];
          ans[left++] = arr[i + 1];
          for (int j = i + 2; j < n; j++) {
            ans[left++] = arr[j];
          }
        }
        break;
      }
    }

    for (char c : ans) {
      System.out.print(c);
    }
    System.out.println();
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
