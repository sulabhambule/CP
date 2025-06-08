import java.io.*;
import java.util.*;

public class KshortestPath {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FASTIO in = new FASTIO();
  static List<long[]>[] adj;

  public static void main(String[] args) {
    solve();
    out.flush();
    out.close();
  }

  static void solve() {
    int n = in.nextInt(), m = in.nextInt();
    int k = in.nextInt();
    adj = new ArrayList[n + 1];
    for (int i = 0; i <= n; i++) {s
      adj[i] = new ArrayList<>();
    }
    for (int i = 0; i < m; i++) {
      int a = in.nextInt(), b = in.nextInt();
      long c = in.nextLong();
      adj[a].add(new long[] { b, c });
    }

    PriorityQueue<Long>[] dist = new PriorityQueue[n + 1];
    for (int i = 0; i <= n; i++) {
      dist[i] = new PriorityQueue<>(k, Collections.reverseOrder());
      // max-heap to keep top k smallest distances
    }

    PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
    pq.offer(new long[] { 0, 1 }); // {cost, node}
    dist[1].offer(0L);

    while (!pq.isEmpty()) {
      long[] cur = pq.poll();
      long cost = cur[0];
      int node = (int) cur[1];

      if (node == n && dist[n].size() == k) {
        // Once we found k shortest paths to destination, we can stop if you want early
        // break
        // but not necessary to break here, can continue to collect all k shortest
        continue;
      }

      for (long[] edge : adj[node]) {
        int nextNode = (int) edge[0];
        long nextCost = cost + edge[1];

        // If we have less than k paths to nextNode OR found a better (smaller) cost
        // than the largest in dist[nextNode]
        if (dist[nextNode].size() < k) {
          dist[nextNode].offer(nextCost);
          pq.offer(new long[] { nextCost, nextNode });
        } else if (dist[nextNode].peek() > nextCost) {
          dist[nextNode].poll();
          dist[nextNode].offer(nextCost);
          pq.offer(new long[] { nextCost, nextNode });
        }
      }
    }

    // dist[n] contains up to k shortest distances but in reverse order (max heap)
    // So collect them and sort ascending before printing
    List<Long> ans = new ArrayList<>(dist[n]);
    Collections.sort(ans);
    for (long x : ans) {
      out.print(x + " ");
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
  }
}
