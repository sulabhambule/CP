import java.io.*;
import java.util.*;

// In this question we have n segments and we need to find the no of seg that can be removed so that the rest of seg intersect at a point / some range. 

//  the logic is that we are iterating on each seg and by using the binary search we are finding the no of seg that not overlap with itering seg.

// BS logic for seg  [L, R] the seg (l, r) not intersect if l > R or r < L 

// CF : https://codeforces.com/contest/1462/problem/F

public class seg1 {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FASTIO in = new FASTIO();
  static final int inf = (int) 1e9;
  static final long INF = (long) 1e18;
  // static final int mod = (int) 1e9 + 7;
  static final int mod = 998244353;

  public static void main(String[] args) {
    int t = in.nextInt();
    while (t-- > 0)
      solve();
    out.close();
  }

  static void solve() {
    int n = in.nextInt();
    List<int[]> coordinates = new ArrayList<>();
    int[] l = new int[n], r = new int[n];
    for (int i = 0; i < n; i++) {
      l[i] = in.nextInt();
      r[i] = in.nextInt();
      coordinates.add(new int[] { l[i], r[i] });
    }
    Arrays.sort(l);
    Arrays.sort(r);
    int ans = n - 1;
    for (int[] c : coordinates) {
      int L = c[0], R = c[1];
      // we need to find the no of pairs whose l > R and r < L
      int right = bs(l, R); // l[j] > R
      int left = bs2(r, L); // r[j] < L
      if (right == -1) {
        right = 0;
      } else {
        right = n - right;
      }
      if (left == -1) {
        left = 0;
      } else {
        left++;
      }
      ans = Math.min(ans, (left + right));
    }

    out.println(ans);
  }

  static int bs2(int[] arr, int tar) {
    int index = -1;
    int low = 0, high = arr.length - 1;
    while (low <= high) {
      int mid = (low + high) / 2;
      if (arr[mid] < tar) {
        index = mid;
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return index;
  }

  static int bs(int[] arr, int tar) {
    int index = -1;
    int low = 0, high = arr.length - 1;
    while (low <= high) {
      int mid = (low + high) / 2;
      if (arr[mid] > tar) {
        index = mid;
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return index;
  }

  static class FASTIO {
    BufferedReader br;
    StringTokenizer st;

    public FASTIO() {
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
        st = null;
        str = br.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return str;
    }
  }
}
