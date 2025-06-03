
import java.io.*;
import java.util.*;

public class GCDOnPath {
  static FastReader in = new FastReader();
  static PrintWriter out = new PrintWriter(System.out);
  static final int MAX_LOG = 20;
  static final int N = (int) 2e5 + 1;

  static int[][] parent = new int[N][MAX_LOG];
  static int[][] gcdVal = new int[N][MAX_LOG];
  static int[] depth = new int[N];
  static int[] arr = new int[N];
  static List<List<Integer>> adj;

  public static void main(String[] args) {
    int t = 1;
    while (t-- > 0) {
      solve();
    }
    out.close();
  }

  private static void solve() {
    int n = in.nextInt();
    int q = in.nextInt();

    adj = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      adj.add(new ArrayList<>());
    }

    for (int i = 1; i <= n; i++) {
      arr[i] = in.nextInt();
    }

    for (int i = 2; i <= n; i++) {
      int p = in.nextInt();
      adj.get(p).add(i);
    }

    dfs(1, 0);

    for (int i = 0; i < q; i++) {
      int a = in.nextInt();
      int b = in.nextInt();
      out.println(getGCDOnPath(a, b));
    }
  }

  private static void dfs(int node, int par) {
    depth[node] = depth[par] + 1;
    parent[node][0] = par;
    gcdVal[node][0] = gcd(arr[node], arr[par]);

    for (int j = 1; j < MAX_LOG; j++) {
      int midParent = parent[node][j - 1];
      parent[node][j] = parent[midParent][j - 1];
      gcdVal[node][j] = gcd(gcdVal[node][j - 1], gcdVal[midParent][j - 1]);
    }

    for (int child : adj.get(node)) {
      if (child != par) {
        dfs(child, node);
      }
    }
  }

  private static int getGCDOnPath(int a, int b) {
    int g = gcd(arr[a], arr[b]);

    if (depth[a] < depth[b]) {
      int temp = a;
      a = b;
      b = temp;
    }

    int diff = depth[a] - depth[b];
    for (int j = MAX_LOG - 1; j >= 0; j--) {
      if (((1 << j) & diff) != 0) {
        g = gcd(g, gcdVal[a][j]);
        a = parent[a][j];
      }
    }

    if (a == b)
      return g;

    for (int j = MAX_LOG - 1; j >= 0; j--) {
      if (parent[a][j] != parent[b][j]) {
        g = gcd(g, gcd(gcdVal[a][j], gcdVal[b][j]));
        a = parent[a][j];
        b = parent[b][j];
      }
    }

    g = gcd(g, gcd(gcdVal[a][0], gcdVal[b][0]));
    return g;
  }

  private static int gcd(int a, int b) {
    return b == 0 ? a : gcd(b, a % b);
  }

  static class FastReader {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    String next() {
      while (st == null || !st.hasMoreTokens()) {
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
  }
}
