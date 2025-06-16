import java.io.*;
import java.util.*;

public class EulerCircuirt {
  static FastReader in = new FastReader();
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static List<Edge>[] adj;
  static boolean[] visited;
  static List<Integer> path;

  public static void main(String[] args) {
    int n = in.nextInt(), m = in.nextInt();
    path = new ArrayList<>();
    visited = new boolean[m];
    adj = new ArrayList[n + 1];
    for (int i = 0; i <= n; i++)
      adj[i] = new ArrayList<>();

    int id = 0;
    for (int i = 0; i < m; i++) {
      int a = in.nextInt(), b = in.nextInt();
      adj[a].add(new Edge(b, id));
      adj[b].add(new Edge(a, id));
      id++;
    }

    for (int i = 1; i <= n; i++) {
      if (adj[i].size() % 2 == 1) {
        out.println("IMPOSSIBLE");
        out.flush();
        return;
      }
    }

    dfs(1);

    if (path.size() != m + 1) {
      out.println("IMPOSSIBLE");
    } else {
      Collections.reverse(path);
      for (int i : path)
        out.print(i + " ");
      out.println();
    }
    out.flush();
  }

  static void dfs(int node) {
    while (!adj[node].isEmpty()) {
      Edge e = adj[node].remove(adj[node].size() - 1);
      if (!visited[e.id]) {
        visited[e.id] = true;
        dfs(e.to);
      }
    }
    path.add(node);
  }

  static class Edge {
    int to, id;

    public Edge(int to, int id) {
      this.to = to;
      this.id = id;
    }
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
