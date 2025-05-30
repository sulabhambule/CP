
import java.io.*;
import java.util.*;

public class negCycleDetectDG {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FASTIO in = new FASTIO();
  static int INF = (int) 1e9;

  public static void main(String[] args) throws IOException {
    int t = 1;
    while (t-- > 0) {
      solve();
    }
    out.close();
  }

  static void solve() {
    int n = in.nextInt();
    int m = in.nextInt();
    List<long[]> edges = new ArrayList<>();
    for (int i = 0; i < m; i++) {
      long a = in.nextLong(), b = in.nextLong();
      long c = in.nextLong();
      edges.add(new long[] { a, b, c });
    }

    int[] parent = new int[n + 1];
    long[] dist = new long[n + 1];
    Arrays.fill(dist, INF);
    Arrays.fill(parent, -1);
    dist[1] = 0;

    int startNode = -1;
    // Run Bellman-Ford n times
    for (int i = 0; i < n; i++) {
      startNode = -1;
      for (long[] e : edges) {
        int u = (int) e[0], v = (int) e[1];
        long w = e[2];
        if (dist[u] + w < dist[v]) {
          dist[v] = dist[u] + w;
          parent[v] = u;
          startNode = v;
        }
      }
    }

    if (startNode == -1) {
      out.println("NO");
      return;
    }

    // To ensure we are inside the cycle
    for (int i = 0; i < n; i++) {
      startNode = parent[startNode];
    }

    List<Integer> cycle = new ArrayList<>();
    int v = startNode;
    do {
      cycle.add(v);
      v = parent[v];
    } while (v != startNode);
    cycle.add(startNode);
    Collections.reverse(cycle);

    out.println("YES");
    for (int node : cycle) {
      out.print(node + " ");
    }
    out.println();
  }

  static class FASTIO {
    BufferedReader br;
    StringTokenizer st;

    FASTIO() {
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
