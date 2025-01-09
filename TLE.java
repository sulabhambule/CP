import java.io.*;
import java.util.*;

public class TLE {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FastReader in = new FastReader();
  // private static final int MOD = (int) 1e9 + 7;
  private static final int MOD = 998244353;

  public static void main(String[] args) {
    int T = 1;
    while (T-- > 0) {
      solve();
    }
    out.close();
  }

  private static void solve() {
    int n = in.nextInt();
    List<List<Integer>> edges = new ArrayList<>();
    for (int i = 0; i < n + 1; i++) {
      edges.add(new ArrayList<>());
    }
    for (int i = 2; i < n + 1; i++) {
      int u = in.nextInt();
      edges.get(u).add(i);
      edges.get(i).add(u);
    }
    int[] subtreeSize = new int[n + 1];
    dfs(1, edges, -1, subtreeSize);
    System.out.print(n - 1 + " ");
    for (int i = 2; i <= n; i++) {
      System.out.print(subtreeSize[i] - 1 + " ");
    }
    System.out.println();
  }

  private static void dfs(int node, List<List<Integer>> edges, int parent, int[] subtreeSize) {
    // subtreeSize[x] = 1 + sum(subtreeSize[child])
    subtreeSize[node] = 1;
    for (int neighbour : edges.get(node)) {
      if (neighbour != parent) {
        dfs(neighbour, edges, node, subtreeSize);
        // subtreeSize of neighbour child is added.
        subtreeSize[node] += subtreeSize[neighbour];
      }
    }
    // once we move out of the dfs call, the subtreeSize of node is correctly
    // populated
  }

  // static class Pair {
  // int first, second;

  // Pair(int first, int second) {
  // this.first = first;
  // this.second = second;
  // }
  // @Override
  // public boolean equals(Object obj) {
  // if (obj == this)
  // return true;
  // if (!(obj instanceof Pair))
  // return false;
  // Pair pair = (Pair) obj;
  // return pair.first == this.first && pair.second == this.second;
  // }
  // @Override
  // public int hashCode() {
  // return Objects.hash(first, second);
  // }
  // }

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