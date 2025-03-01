import java.io.*;
import java.util.*;

public class Simple {
    public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    static FASTIO in = new FASTIO();
    static final int MOD = 998244353;

    public static void main(String[] Hi) throws IOException {
        int cp = 1;
        while (cp-- > 0) {
            solve();
        }
        out.close();
    }

    static void solve() {
        int n = in.nextInt();
        long[][] arr = new long[n][2];
        for (int i = 0; i < n; i++) {
            long u = in.nextLong();
            long v = in.nextLong();
            arr[i][0] = u;
            arr[i][1] = v;
        }

        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            long[] curr = arr[i];
            for (int j = i + 1; j < n; j++) {
                long[] next = arr[j];
                long dist = Math.min(Math.abs(curr[0] - next[0]), Math.abs(curr[1] - next[1]));
                edges.add(new Edge(i, j, dist));
            }
        }

        Collections.sort(edges);
        DSU dsu = new DSU(n);
        int mstWeight = 0;

        for (Edge e : edges) {
            if (dsu.union((int) e.u, (int) e.v)) {
                mstWeight += e.w;
            }
        }
        out.println(mstWeight);
    }

    static class Edge implements Comparable<Edge> {
        long u, v, w;

        Edge(long u, long v, long w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        public int compareTo(Edge o) {
            return Long.compare(this.w, o.w);
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

class DSU {
    private int[] parent, rank;

    public DSU(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public boolean union(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);
        if (rootU == rootV)
            return false;

        if (rank[rootU] > rank[rootV]) {
            parent[rootV] = rootU;
        } else if (rank[rootU] < rank[rootV]) {
            parent[rootU] = rootV;
        } else {
            parent[rootV] = rootU;
            rank[rootU]++;
        }
        return true;
    }
}
