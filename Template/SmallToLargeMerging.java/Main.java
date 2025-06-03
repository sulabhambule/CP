import java.util.*;

public class Main {
  static final long INF = (long) 1e18;
  static Scanner in = new Scanner(System.in);
  static List<Integer>[] adj;
  static Set<Long>[] color;

  public static void main(String[] args) {
    int t = 1;
    while (t-- > 0) {
      solve();
    }
    in.close();
  }

  static void solve() {
    int n = in.nextInt();
    color = new HashSet[n + 1];
    adj = new ArrayList[n + 1];
    int[] ans = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      color[i] = new HashSet<>();
      adj[i] = new ArrayList<>();
    }
    for (int i = 1; i <= n; i++) {
      color[i].add(in.nextLong());
    }
    for (int i = 1; i < n; i++) {
      int a = in.nextInt(), b = in.nextInt();
      adj[a].add(b);
      adj[b].add(a);
    }
    processColors(1, 0, ans);
    for (int i = 1; i <= n; i++) {
      System.out.print(ans[i] + " ");
    }
    System.out.println();
  }

  static void processColors(int node, int parent, int[] ans) {
    for (int adjNode : adj[node]) {
      if (adjNode == parent)
        continue;
      processColors(adjNode, node, ans);
      // small to large merging :- merge small set to the larger one
      if (color[node].size() < color[adjNode].size()) {
        Set<Long> temp = color[node];
        color[node] = color[adjNode];
        color[adjNode] = temp;
      }
      color[node].addAll(color[adjNode]);
    }
    ans[node] = color[node].size();
  }
}
