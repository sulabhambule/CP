import java.io.*;
import java.util.*;

public class Simple {
    public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    static FASTIO in = new FASTIO();
    static int MOD = (int) 1e9 + 7;
    static List<Integer>[] adj;

    public static void main(String[] args) {
        solve();
        out.flush();
        out.close();
    }

    static void solve() {
        int n = in.nextInt(), m = in.nextInt();
        adj = new ArrayList[n + 1];
        int[] indegree = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int a = in.nextInt(), b = in.nextInt();
            adj[a].add(b);
            indegree[b]++;
        }

        int[] dp = new int[n + 1];
        dp[1] = 1;

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0)
                q.add(i);
        }

        while (!q.isEmpty()) {
            int node = q.poll();

        }

        dfs(1, 0, dp);
        out.println(dp[n]);
    }

    static void dfs(int node, int parent, int[] dp) {
        for (int adjNode : adj[node]) {
            if (adjNode != parent) {
                dp[adjNode] = Math.max(dp[adjNode], 1 + dp[node]);
                dfs(adjNode, node, dp);
            }
        }
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
