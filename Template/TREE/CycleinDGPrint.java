import java.io.*;
import java.util.*;

public class CycleinDGPrint {
  static List<Integer>[] adj;
  static boolean[] visited, onStack;
  static int[] parent;
  static int start = -1, end = -1;
  static PrintWriter out = new PrintWriter(System.out);

  public static void main(String[] args) {
    FastReader in = new FastReader();
    int n = in.nextInt();
    int m = in.nextInt();

    adj = new ArrayList[n + 1];
    visited = new boolean[n + 1];
    onStack = new boolean[n + 1];
    parent = new int[n + 1];

    for (int i = 1; i <= n; i++)
      adj[i] = new ArrayList<>();

    for (int i = 0; i < m; i++) {
      int a = in.nextInt(), b = in.nextInt();
      adj[a].add(b);
    }

    boolean found = false;
    for (int i = 1; i <= n; i++) {
      if (!visited[i]) {
        if (dfs(i)) {
          found = true;
          break;
        }
      }
    }

    if (!found) {
      out.println("IMPOSSIBLE");
    } else {
      List<Integer> cycle = new ArrayList<>();
      cycle.add(end);
      for (int v = start; v != end; v = parent[v]) {
        cycle.add(v);
      }
      cycle.add(end);
      Collections.reverse(cycle);

      out.println(cycle.size());
      for (int city : cycle)
        out.print(city + " ");
      out.println();
    }
    out.flush();
  }

  static boolean dfs(int u) {
    visited[u] = true;
    onStack[u] = true;
    for (int v : adj[u]) {
      if (!visited[v]) {
        parent[v] = u;
        if (dfs(v))
          return true;
      } else if (onStack[v]) {
        start = u;
        end = v;
        return true;
      }
    }
    onStack[u] = false;
    return false;
  }

  static class FastReader {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    String next() {
      while (st == null || !st.hasMoreTokens()) {
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
      return st.nextToken();
    }

    int nextInt() {
      return Integer.parseInt(next());
    }
  }
}
