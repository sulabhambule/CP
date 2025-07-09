import java.io.*;
import java.util.*;

public class CycleUndirectGPrint {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FASTIO in = new FASTIO();
  static int INF = (int) 1e9;
  static List<Integer>[] adj;
  static boolean[] visited;
  static boolean[] onStack;
  static int[] parent;
  static int start, end;

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
    start = -1;
    end = -1;
    adj = new ArrayList[n + 1];
    parent = new int[n + 1];
    onStack = new boolean[n + 1];
    visited = new boolean[n + 1];
    Arrays.fill(parent, -1);
    for (int i = 0; i <= n; i++) {
      adj[i] = new ArrayList<>();
    }
    for (int i = 0; i < m; i++) {
      int a = in.nextInt(), b = in.nextInt();
      adj[a].add(b);
      adj[b].add(a);
    }
    boolean cycleFound = false;
    for (int node = 1; node <= n; node++) {
      if (!visited[node]) {
        if (dfs(node, -1)) {
          cycleFound = true;
          break;
        }
      }
    }
    if (!cycleFound) {
      out.println("IMPOSSIBLE");
      return;
    }
    List<Integer> cycle = new ArrayList<>();
    cycle.add(start);
    for (int v = end; v != start; v = parent[v]) {
      cycle.add(v);
    }
    cycle.add(start);
    Collections.reverse(cycle);
    out.println(cycle.size());
    for (int node : cycle) {
      out.print(node + " ");
    }
    out.println();
  }

  static boolean dfs(int node, int par) {
    visited[node] = true;
    onStack[node] = true;

    for (int adjNode : adj[node]) {
      if (adjNode == par)
        continue;

      if (!visited[adjNode]) {
        parent[adjNode] = node;
        if (dfs(adjNode, node))
          return true;
      } else if (onStack[adjNode]) {
        // cycle found;
        start = adjNode;
        end = node;
        return true;
      }
    }
    onStack[node] = false;
    return false;
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
