
import java.io.*;
import java.util.*;

// problem link : https://codeforces.com/problemset/problem/685/B
// so if all child have size <= size[node] then node itself is the centroid of the subtree[node] else . there is one heavy node in the subtree of the node , in which centroid lies
// let the centorid of heavy[node] (subtree) is cen then the whole centroid of subtre(node) lies in betw heavy -> node to the cen(node)
// we simple looke eahc node from cen to above it

public class subTree_centroid {
  static final long INF = Long.MAX_VALUE / 2;
  static FastReader in = new FastReader();
  static PrintWriter out = new PrintWriter(System.out);
  static int mod = (int) 1e9 + 7;

  public static void main(String[] args) throws IOException {
    int t = 1;
    while (t-- > 0) {
      solve();
    }
    out.close();
  }

  static List<List<Integer>> adj;
  static int[] size, heavy, centroid, par;

  // size stores the size of each substree
  // heavy store the heavy childs subtree size
  // cetroid store the centroid of the each subtree
  // par store the parent of the each node.

  static void solve() {
    adj = new ArrayList<>();
    int n = in.nextInt(), q = in.nextInt();
    for (int i = 0; i <= n; i++) {
      adj.add(new ArrayList<>());
    }
    size = new int[n + 1];
    heavy = new int[n + 1];
    centroid = new int[n + 1];
    par = new int[n + 1];
    for (int i = 2; i <= n; i++) {
      int p = in.nextInt();
      adj.get(p).add(i);
      par[i] = p;
    }

    dfs(1, 0);
    while (q-- > 0) {
      int node = in.nextInt();
      out.println(centroid[node]);
    }
  }

  static void dfs(int node, int parent) {
    size[node] = 1;
    for (int adjNode : adj.get(node)) {
      dfs(adjNode, node);
      size[node] += size[adjNode];
      heavy[node] = Math.max(heavy[node], size[adjNode]);
    }

    int req = size[node] / 2;
    if (heavy[node] <= req) {
      centroid[node] = node;
      return;
    }

    for (int adjNode : adj.get(node)) {
      if (size[adjNode] == heavy[node]) {
        // this is the heavy child
        int cen = centroid[adjNode];

        do {
          int above = size[node] - size[cen];
          int below = heavy[cen];
          if (Math.max(above, below) <= req)
            break;
          cen = par[cen];
        } while (true);
        centroid[node] = cen;
        break;
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

    long nextLong() {
      return Long.parseLong(next());
    }

    double nextDouble() {
      return Double.parseDouble(next());
    }
  }
}
