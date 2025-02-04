import java.util.*;

public class Simple {
    static final long INF = (long) 1e12;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        List<List<long[]>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            long a = in.nextLong();
            long b = in.nextLong();
            long c = in.nextLong();
            adj.get((int) a).add(new long[] { b, c });
            adj.get((int) b).add(new long[] { a, c });
        }

        // we will dijkstra from node 1 to n and node n to 1

        long[] dist1 = new long[n + 1];
        long[] dist2 = new long[n + 1];

        dijkstra(1, dist1, adj);
        dijkstra(n, dist2, adj);

        long ans = (long) 1e12;
        for (int i = 1; i <= n; i++) {
            for (long[] adJ : adj.get(i)) {
                int node = (int) adJ[0];
                long wght = adJ[1];
                ans = Math.min(ans, dist1[i] + (wght / 2) + dist2[node]);
                ans = Math.min(ans, dist1[node] + (wght / 2) + dist2[i]);
            }
        }
        System.out.println(ans);
    }

    private static void dijkstra(int start, long[] dist, List<List<long[]>> adj) {
        Arrays.fill(dist, (long) 1e12);
        dist[start] = 0;

        PriorityQueue<long[]> pq = new PriorityQueue<>((x, y) -> Long.compare(x[0], y[0]));

        pq.add(new long[] { 0, start });

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            long distance = curr[0];
            int node = (int) curr[1];

            if (distance > dist[node]) {
                continue;
            }

            for (long[] adJ : adj.get(node)) {
                int adjNode = (int) adJ[0];
                long edgeWeight = adJ[1];

                if (dist[node] + edgeWeight < dist[adjNode]) {
                    dist[adjNode] = dist[node] + edgeWeight;
                    pq.add(new long[] { dist[adjNode], adjNode });
                }
            }
        }
    }
}
