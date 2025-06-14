import java.io.*;
import java.util.*;

public class Simple {
    static FastReader in = new FastReader();
    static PrintWriter out = new PrintWriter(System.out);
    static int[] to, res;
    static boolean[] visited, onStack;

    public static void main(String[] args) {
        int n = in.nextInt();
        to = new int[n + 1];
        res = new int[n + 1];
        visited = new boolean[n + 1];
        onStack = new boolean[n + 1];
        for (int i = 1; i <= n; i++)
            to[i] = in.nextInt();
        for (int i = 1; i <= n; i++) {
            if (!visited[i])
                dfs(i, new ArrayList<>());
        }
        for (int i = 1; i <= n; i++)
            out.print(res[i] + " ");
        out.println();
        out.flush();
    }

    static void dfs(int u, List<Integer> path) {
        visited[u] = true;
        onStack[u] = true;
        path.add(u);

        int v = to[u];
        if (!visited[v]) {
            dfs(v, path);
        } else if (onStack[v]) {
            int len = 1;
            List<Integer> cycle = new ArrayList<>();
            for (int i = path.size() - 1; path.get(i) != v; i--) {
                cycle.add(path.get(i));
                len++;
            }
            res[v] = len;
            for (int x : cycle)
                res[x] = len;
        }
        if (res[u] == 0)
            res[u] = res[v] + 1;
        onStack[u] = false;
        path.remove(path.size() - 1);
    }

    static class FastReader {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
