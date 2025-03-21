
import java.io.*;
import java.util.*;

public class zigzagInGrid {

  /* Traverse the 2D grid in the zigzag manner */

  static FastReader in;
  static PrintWriter out;
  static int MOD = (int) 1e9 + 7;

  static void solve() {
    int n = in.nextInt();
    int m = in.nextInt();
    int k = in.nextInt();
    List<int[]> cells = new ArrayList<>();

    // Snake-like traversal of the table
    for (int i = 0; i < n; i++) {
      if (i % 2 == 0) {
        for (int j = 0; j < m; j++) {
          cells.add(new int[] { i + 1, j + 1 });
        }
      } else {
        for (int j = m - 1; j >= 0; j--) {
          cells.add(new int[] { i + 1, j + 1 });
        }
      }
    }

    int index = 0;
    for (int i = 0; i < k; i++) {
      int size = (i == k - 1) ? (cells.size() - index) : 2;
      // Last tube gets the remaining cells
      out.print(size);

      for (int j = 0; j < size; j++, index++) {
        out.print(" " + cells.get(index)[0] + " " + cells.get(index)[1]);
      }
      out.println();
    }
  }

  public static void main(String[] args) {
    in = new FastReader();
    out = new PrintWriter(System.out);
    int t = 1;
    while (t-- > 0) {
      solve();
    }
    out.close();
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
  }
}