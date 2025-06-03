import java.io.*;
import java.util.*;

/*
 * Find the min value on Path. from node u to v
 * Find the max value on the Path.
 * Find the sum on the path
 * Find the gcd over the path.
 * In the below code just replace the min with max, gcd etc
 */

/*
 1) Find the min value on Path : min(a, b) -> min(min(a, lca), min(b. lca));
 - in our binary lifting table we gona be store the min of every powers of two
 - par[node][i] = {2ith par, min from node to th ith parent}
 */
public class minValPath {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FastReader in = new FastReader();
  private static final int MOD = (int) 1e9 + 7;
  // private static final int MOD = 998244353;
  private static final int MAX_LOG = 20;
  private static final int N = (int) 2e5 + 1;
  static Pair[][] par = new Pair[N][MAX_LOG];
  static List<List<Integer>> adj;
  static int[] depth = new int[N];
  static int[] arr = new int[N];

  public static void main(String[] args) {
    int T = 1;
    while (T-- > 0) {
      solve();
    }
    out.close();
  }

  private static void solve() {
    adj = new ArrayList<>();
    int n = in.nextInt();
    int q = in.nextInt();
    for (int i = 1; i <= n; i++) {
      arr[i] = in.nextInt();
    }
    for (int i = 0; i <= n; i++) {
      adj.add(new ArrayList<>());
    }
    for (int i = 2; i <= n; i++) {
      int parnt = in.nextInt();
      adj.get(parnt).add(i);
    }
    dfs(1, 1);

    for (int i = 0; i < q; i++) {
      int a = in.nextInt();
      int b = in.nextInt();
      System.out.println(MinDistance(a, b));
    }
  }

  private static int MinDistance(int nodeA, int nodeB) {
    if (nodeA == nodeB)
      return nodeA;

    if (depth[nodeA] < depth[nodeB]) {
      int tempNode = nodeA;
      nodeA = nodeB;
      nodeB = tempNode;
    }

    int min = Math.min(arr[nodeA], arr[nodeB]), nodeDiff = depth[nodeA] - depth[nodeB];
    for (int j = MAX_LOG - 1; j >= 0; j--) {
      if (((1 << j) & nodeDiff) != 0) {
        nodeA = par[nodeA][j].first;
        min = Math.min(min, par[nodeA][j].second);
      }
    }

    for (int j = MAX_LOG - 1; j >= 0; j--) {
      if (par[nodeA][j].first != par[nodeB][j].first) {
        min = Math.min(par[nodeA][j - 1].second, par[nodeB][j - 1].second);
        nodeA = par[nodeA][j].first;
        nodeB = par[nodeB][j].first;
      }
    }

    return (nodeA != nodeB ? Math.min(par[nodeA][0].second, min) : min);
  }

  private static void dfs(int node, int parent) {
    for (int i = 0; i < MAX_LOG; i++)
      par[node][i] = new Pair(0, Integer.MAX_VALUE);
    depth[node] = 1 + depth[parent];

    par[node][0] = new Pair(parent, Math.min(arr[node], arr[parent]));
    for (int j = 1; j < MAX_LOG; j++) {
      par[node][j] = new Pair(par[par[node][j - 1].first][j - 1].first,
          Math.min(par[par[node][j - 1].first][j - 1].second,
              par[node][j - 1].second));
    }
    for (int adjNode : adj.get(node)) {
      if (adjNode != parent) {
        dfs(adjNode, node);
      }
    }
  }

  // static int Kthparent(int node, int k) {
  // for (int i = MAX_LOG - 1; i >= 0; i--) {
  // if (((1 << i) & k) != 0) {
  // node = par[node][i];
  // if (node == 0)
  // return 0;
  // }
  // }
  // return node;
  // }

  static class Pair {
    int first, second;

    public Pair(int f, int s) {
      first = f;
      second = s;
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

    double nextDouble() {
      return Double.parseDouble(next());
    }
  }
}

/*
 * import java.io.*;
 * import java.util.*;
 * 
 * public class MinValueOnPath {
 * static FastReader in = new FastReader();
 * static PrintWriter out = new PrintWriter(System.out);
 * static final int MAX_LOG = 20;
 * static final int N = (int) 2e5 + 1;
 * 
 * static int[][] parent = new int[N][MAX_LOG];
 * static int[][] minVal = new int[N][MAX_LOG];
 * static int[] depth = new int[N];
 * static int[] arr = new int[N];
 * static List<List<Integer>> adj;
 * 
 * public static void main(String[] args) {
 * int t = 1;
 * while (t-- > 0) {
 * solve();
 * }
 * out.close();
 * }
 * 
 * private static void solve() {
 * int n = in.nextInt();
 * int q = in.nextInt();
 * 
 * adj = new ArrayList<>();
 * for (int i = 0; i <= n; i++) {
 * adj.add(new ArrayList<>());
 * }
 * 
 * for (int i = 1; i <= n; i++) {
 * arr[i] = in.nextInt();
 * }
 * 
 * for (int i = 2; i <= n; i++) {
 * int p = in.nextInt();
 * adj.get(p).add(i);
 * }
 * 
 * dfs(1, 0);
 * 
 * for (int i = 0; i < q; i++) {
 * int a = in.nextInt();
 * int b = in.nextInt();
 * out.println(getMinOnPath(a, b));
 * }
 * }
 * 
 * private static void dfs(int node, int par) {
 * depth[node] = depth[par] + 1;
 * parent[node][0] = par;
 * minVal[node][0] = Math.min(arr[node], arr[par]);
 * 
 * for (int j = 1; j < MAX_LOG; j++) {
 * int midParent = parent[node][j - 1];
 * parent[node][j] = parent[midParent][j - 1];
 * minVal[node][j] = Math.min(minVal[node][j - 1], minVal[midParent][j - 1]);
 * }
 * 
 * for (int child : adj.get(node)) {
 * if (child != par) {
 * dfs(child, node);
 * }
 * }
 * }
 * 
 * private static int getMinOnPath(int a, int b) {
 * int minValue = Math.min(arr[a], arr[b]);
 * 
 * if (depth[a] < depth[b]) {
 * int temp = a;
 * a = b;
 * b = temp;
 * }
 * 
 * int diff = depth[a] - depth[b];
 * for (int j = MAX_LOG - 1; j >= 0; j--) {
 * if (((1 << j) & diff) != 0) {
 * minValue = Math.min(minValue, minVal[a][j]);
 * a = parent[a][j];
 * }
 * }
 * 
 * if (a == b) return minValue;
 * 
 * for (int j = MAX_LOG - 1; j >= 0; j--) {
 * if (parent[a][j] != parent[b][j]) {
 * minValue = Math.min(minValue, Math.min(minVal[a][j], minVal[b][j]));
 * a = parent[a][j];
 * b = parent[b][j];
 * }
 * }
 * 
 * minValue = Math.min(minValue, Math.min(minVal[a][0], minVal[b][0]));
 * return minValue;
 * }
 * 
 * static class FastReader {
 * BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 * StringTokenizer st;
 * 
 * String next() {
 * while (st == null || !st.hasMoreTokens()) {
 * try {
 * st = new StringTokenizer(br.readLine());
 * } catch (IOException e) {
 * e.printStackTrace();
 * }
 * }
 * return st.nextToken();
 * }
 * 
 * int nextInt() {
 * return Integer.parseInt(next());
 * }
 * }
 * }
 * 
 */