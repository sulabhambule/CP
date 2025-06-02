import java.io.*;
import java.util.*;

public class FindCentroid {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FASTIO in = new FASTIO();
  public static int[] subSize;
  public static List<Integer>[] adj;
  public static int N;

  public static void main(String[] args) {
    N = in.nextInt();
    adj = new List[N];
    for (int i = 0; i < N; i++) {
      adj[i] = new ArrayList<>();
    }
    subSize = new int[N];

    for (int i = 0; i < N - 1; i++) {
      int a = in.nextInt() - 1;
      int b = in.nextInt() - 1;
      adj[a].add(b);
      adj[b].add(a);
    }

    subtreeSize(0, -1);
    out.println(getCentroid(0, -1) + 1);
    out.close();
  }

  // Find the size of the subtree under this node.
  public static int subtreeSize(int node, int par) {
    int res = 1;
    for (int next : adj[node]) {
      if (next == par) {
        continue;
      }
      res += subtreeSize(next, node);
    }
    return (subSize[node] = res);
  }

  // Find the centroid of the tree (the subtree with <= N/2 nodes)
  public static int getCentroid(int node, int par) {
    for (int next : adj[node]) {
      if (next == par) {
        continue;
      }
      // Keep searching for the centroid if there are subtrees with more
      // than N/2 nodes.
      if (subSize[next] * 2 > N) {
        return getCentroid(next, node);
      }
    }
    return node;
  }

  static class FASTIO {
    BufferedReader br;
    StringTokenizer st;

    public FASTIO() {
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