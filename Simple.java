import java.util.*;

public class Simple {
  static final long INF = Long.MAX_VALUE;
  static Scanner in = new Scanner(System.in);

  public static void main(String[] args) {
    int tc = in.nextInt();
    while (tc-- > 0) {
      int n = in.nextInt();
      int m = in.nextInt();
      int l = in.nextInt();

      long sum = 0, minOdd = Long.MAX_VALUE;
      for (int i = 0; i < l; i++) {
        long x = in.nextLong();
        sum += x;
        if (x % 2 == 1) {
          minOdd = Math.min(minOdd, x);
        }
      }

      long maxEven = 0, maxOdd = 0;
      if (sum % 2 == 1) {
        maxOdd = sum;
        maxEven = sum - minOdd;
      } else {
        maxEven = sum;
        maxOdd = sum - minOdd;
      }

      // Create adjacency list
      List<List<Integer>> adj = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        adj.add(new ArrayList<>());
      }

      for (int i = 0; i < m; i++) {
        int u = in.nextInt() - 1;
        int v = in.nextInt() - 1;
        adj.get(u).add(v);
        adj.get(v).add(u);
      }

      // dist[i][0] = even parity dist, dist[i][1] = odd parity dist
      long[][] dist = new long[n][2];
      for (int i = 0; i < n; i++) {
        Arrays.fill(dist[i], INF);
      }

      Queue<int[]> q = new LinkedList<>();
      q.offer(new int[] { 0, 0 });
      dist[0][0] = 0;

      while (!q.isEmpty()) {
        int[] front = q.poll();
        int node = front[0];
        int parity = front[1];

        for (int neighbor : adj.get(node)) {
          int newParity = 1 - parity;
          if (dist[neighbor][newParity] != INF)
            continue;
          dist[neighbor][newParity] = dist[node][parity] + 1;
          q.offer(new int[] { neighbor, newParity });
        }
      }

      StringBuilder ans = new StringBuilder();
      for (int i = 0; i < n; i++) {
        if (dist[i][0] <= maxEven || dist[i][1] <= maxOdd) {
          ans.append('1');
        } else {
          ans.append('0');
        }
      }

      System.out.println(ans);
    }
  }
}
