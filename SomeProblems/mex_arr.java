package SomeProblems;

import java.io.*;
import java.util.*;

// Author : Sulabh Ambule
public class mex_arr {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static long MOD = (long) (1e9 + 7);
  // static long MOD = 998244353;
  static FastReader in = new FastReader();

  public static void main(String[] args) throws Exception {
    int cf = in.nextInt();
    while (cf-- > 0) {
      Accepted();
    }
    out.flush();
    out.close();
  }

  /*
   * 
   * || जय श्री राम ||
   * 
   */

  private static void Accepted() {
    int n = in.nextInt();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = in.nextInt();
    }
    ArrayList<Integer> list = new ArrayList<>();
    int max = (int) 2e5;
    boolean[] visited = new boolean[max + 1];
    long[] count = new long[max + 1];
    for (int i = 0; i < n; i++) {
      count[a[i]]++;
    }
    int mex = 0, j = 0;
    for (int i = 0; i < n; i++) {
      if (a[i] == mex) {
        count[a[i]]--;
        visited[a[i]] = true;
        while (visited[mex]) {
          mex++;
        }
        if (count[mex] == 0) {
          // there is num equal to mex in future arr. so break the k value.
          list.add(mex); // mex is added to the answerr
          mex = 0;
          for (int k = j; k <= i; k++) {
            visited[a[k]] = false;
          }
          j = i + 1;
        }
      } else if (count[mex] == 0) {
        // again cut the k here.
        list.add(mex);
        mex = 0;
        count[a[i]]--;
        j = i + 1;
      } else {
        count[a[i]]--;
        visited[a[i]] = true;
      }
    }
    System.out.println(list.size());
    for (int ii : list) {
      System.out.print(ii + " ");
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

    String nextLine() {
      String str = "";
      try {
        str = br.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return str;
    }
  }

  // static class Pair {
  // long first;
  // long second
  // Pair(long f, long s) {
  // this.first = f;
  // this.second = s;
  // }
  // }

  // static class Pair implements Comparable<Pair> {
  // long first;
  // long second;

  // Pair(long first, long x) {
  // this.first = first;
  // this.second = x;
  // }

  // @Override
  // public int compareTo(Pair other) {
  // return Long.compare(this.second, other.second);
  // }
  // }

}
