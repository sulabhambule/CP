import java.io.*;
import java.util.*;

// CF link : https://codeforces.com/contest/1725/problem/M

public class MovingHand {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FASTIO in = new FASTIO();
  static final int MOD = 998244353;
  static final int mod = (int) 1e9 + 7;
  static final int inf = (int) 1e9;
  static final long INF = (long) 1e18;

  public static void main(String[] args) {
    int t = 1;
    while (t-- > 0)
      solve();
    out.close();
  }

  static List<List<int[]>> adj, reverseAdj;

  static void solve() {
    int n = in.nextInt(), m = in.nextInt();
    adj = new ArrayList<>();
    reverseAdj = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      adj.add(new ArrayList<>());
      reverseAdj.add(new ArrayList<>());
    }
    for (int i = 0; i < m; i++) {
      int u = in.nextInt(), v = in.nextInt(), w = in.nextInt();
      adj.get(u).add(new int[] { v, w });
      reverseAdj.get(v).add(new int[] { u, w });
    }

    // now we are going to run the bfs and we push the state as {node, isReversed}
    // and is the isReversed is true then we are going to use the reverseAdj to
    // further exploration of the depth

    long[][] dist = new long[2][n + 1];
    for (int i = 0; i < 2; i++)
      Arrays.fill(dist[i], INF);
    dist[0][1] = 0; // start at node 1 in normal mode

    PriorityQueue<long[]> pq = new PriorityQueue<>(
        (x, y) -> Long.compare(x[0], y[0]));

    pq.offer(new long[] { 0, 1, 0 }); // dist, node, mode
    dist[0][1] = 0; // from the node 1 in normal mode

    while (!pq.isEmpty()) {
      long[] curr = pq.poll();
      long cost = curr[0];
      int node = (int) curr[1];
      int mode = (int) curr[2];

      if (cost > dist[mode][node])
        continue;

      // If reversed, only use reverse edges
      if (mode == 1) {
        for (int[] adjN : reverseAdj.get(node)) {
          int adjNode = adjN[0], edgWght = adjN[1];
          long currCost = cost + edgWght;
          if (currCost < dist[1][adjNode]) {
            dist[1][adjNode] = currCost;
            pq.offer(new long[] { currCost, adjNode, 1 });
          }
        }
      } else {
        // Normal edges
        for (int[] adjN : adj.get(node)) {
          int adjNode = adjN[0], edgWght = adjN[1];
          long currCost = cost + edgWght;
          if (currCost < dist[0][adjNode]) {
            dist[0][adjNode] = currCost;
            pq.offer(new long[] { currCost, adjNode, 0 });
          }
        }

        if (cost < dist[1][node]) {
          dist[1][node] = cost;
          pq.offer(new long[] { cost, node, 1 });
        }
      }
    }

    for (int p = 2; p <= n; p++) {
      if (dist[0][p] == INF && dist[1][p] == INF)
        out.print(-1 + " ");
      else
        out.print(Math.min(dist[0][p], dist[1][p]) + " ");
    }
    out.println();
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

    String nextLine() {
      String str = "";
      try {
        st = null;
        str = br.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return str;
    }
  }
}