import java.io.*;
import java.util.*;

public class Main {
    public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    static FASTIO in = new FASTIO();
    static final int MOD = 998244353;
    static int n, m, sccCount = 0;
    static List<Integer>[] adj, revAdj, dag;
    static long[] coins, sccCoins, dp;
    static int[] sccId;
    static boolean[] visited;
    static List<Integer> order;
    static List<List<Integer>> sccs;

    public static void main(String[] Hi) throws IOException {
        int cp = 1;
        while (cp-- > 0) {
            solve();
        }
        out.close();
    }

    static void solve() {
        n = in.nextInt();
        m = in.nextInt();
        coins = new long[n];
        for (int i = 0; i < n; i++) {
            coins[i] = in.nextLong();
        }
        adj = new ArrayList[n];
        revAdj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
            revAdj[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            adj[u].add(v);
        }
        findSCCs();
        dp = new long[sccCount];
        long maxCoins = 0;
        for (int i = sccCount - 1; i >= 0; i--) {
            dp[i] = sccCoins[i];
            for (int adjNode : dag[i]) {
                dp[i] = Math.max(dp[i], dp[adjNode] + sccCoins[i]);
            }
            maxCoins = Math.max(maxCoins, dp[i]);
        }

        out.println(maxCoins);
    }

    static void findSCCs() {
        visited = new boolean[n];
        order = new ArrayList<>();

        // Step 1: Topo order by finishing time
        for (int i = 0; i < n; i++) {
            if (!visited[i])
                dfs1(i);
        }

        // Step 2: Reverse the graph
        for (int i = 0; i < n; i++) {
            for (int v : adj[i]) {
                revAdj[v].add(i);
            }
        }

        // Step 3: Process nodes in reverse order
        Arrays.fill(visited, false);
        Collections.reverse(order);
        sccs = new ArrayList<>();
        sccId = new int[n];

        sccCoins = new long[n];
        for (int node : order) {
            if (!visited[node]) {
                List<Integer> comp = new ArrayList<>();
                dfs2(node, comp, sccCount++);
                sccs.add(comp);
            }
        }

        // Step 4: Build Condensation Graph
        buildCondensationGraph(sccCount);
    }

    static void dfs1(int v) {
        visited[v] = true;
        for (int u : adj[v]) {
            if (!visited[u])
                dfs1(u);
        }
        order.add(v);
    }

    static void dfs2(int node, List<Integer> comp, int id) {
        visited[node] = true;
        sccId[node] = id;
        comp.add(node);
        sccCoins[id] += coins[node]; // SUm the coins in the SCC
        for (int adjNode : revAdj[node]) {
            if (!visited[adjNode]) {
                dfs2(adjNode, comp, id);
            }
        }
    }

    static void buildCondensationGraph(int sccCount) {
        dag = new ArrayList[sccCount];
        for (int i = 0; i < sccCount; i++) {
            dag[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            for (int v : adj[i]) {
                if (sccId[i] != sccId[v]) {
                    dag[sccId[i]].add(sccId[v]);
                }
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

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
