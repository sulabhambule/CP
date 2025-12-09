import java.io.*;
import java.util.*;

// CF LINK : https://codeforces.com/contest/1437/problem/D

// this is a problem on the bfs also the increasing visting of the  child and we need to find such a tree having the same bfs order as a also minimize the hieght of the tree , we need to print the height of the tree.

//  LOGIC :  a1=1, then let's find the maximum r1 that segment a2,…,ar1 is in ascending order — they will be the children of a1 and the only vertices of depth 1. Next search the maximum r2 such that segment ar1+1,…,ar2 is in ascending order — they will be the children of a2, and so on. It's easy to see that this strategy maximizes the number of vertices of each depth level, so minimize the height of the tree.

public class MinimalHieghtTree {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FASTIO in = new FASTIO();
  static final int inf = (int) 1e9;
  static final long INF = (long) 1e18;
  // static final int mod = (int) 1e9 + 7;
  static final int mod = 998244353;
  static final long BASE = 911382323;
  static final long MOD = 972663749;

  public static void main(String[] args) {
    int t = in.nextInt();
    while (t-- > 0)
      solve();
    out.close();
  }

  static void solve() {
    int n = in.nextInt();
    int[] a = new int[n];
    for (int i = 0; i < n; i++)
      a[i] = in.nextInt();
    int[] height = new int[n];
    int last = 0;
    for (int i = 1; i < n; i++) {
      if (i == 1) {
        height[i] = height[last] + 1;
        continue;
      }
      if (a[i] < a[i - 1]) {
        last++;
      }
      height[i] = height[last] + 1;
    }
    out.println(height[n - 1]);
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
