
import java.io.*;
import java.util.*;

public class Main {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FastReader in = new FastReader();
  static int mod = (int) 1e9 + 7;
  static final int inf = (int) 1e9;
  static final long INF = (long) 1e18;

  public static void main(String[] args) {
    int t = in.nextInt();
    while (t-- > 0) {
      solve();
    }
    out.flush();
  }

  static void solve() {
    int n = in.nextInt();
    long[] a = new long[n];
    for (int i = 0; i < n; i++)
      a[i] = in.nextLong();
    long[] b = new long[n];
    for (int i = 0; i < n; i++)
      b[i] = in.nextLong();

    List<List<Integer>> adj = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      adj.add(new ArrayList<>());
    }
    Integer[] idxA = new Integer[n];
    for (int i = 0; i < n; i++)
      idxA[i] = i;
    Arrays.sort(idxA, (i, j) -> Long.compare(a[j], a[i]));
    for (int i = 0; i < n - 1; i++) {
      int u = idxA[i + 1];
      int v = idxA[i];
      adj.get(u).add(v);
    }
    Integer[] idxB = new Integer[n];
    for (int i = 0; i < n; i++)
      idxB[i] = i;
    Arrays.sort(idxB, (i, j) -> Long.compare(b[j], b[i]));
    for (int i = 0; i < n - 1; i++) {
      int u = idxB[i + 1];
      int v = idxB[i];
      adj.get(u).add(v);
    }
    boolean[] vis = new boolean[n + 1];
    int maxIndex = 0;
    for (int i = 1; i < n; i++) {
      if (a[i] > a[maxIndex]) {
        maxIndex = i;
      }
    }

    String ans = "";
    dfs(maxIndex, adj, vis);

    for (int i = 0; i < n; i++) {
      if (vis[i]) {
        ans += '1';
      } else {
        ans += '0';
      }
    }

    out.println(ans);
  }

  static void dfs(int index, List<List<Integer>> adj, boolean[] vis) {
    vis[index] = true;
    for (int adjNode : adj.get(index)) {
      if (!vis[adjNode]) {
        dfs(adjNode, adj, vis);
      }
    }
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
