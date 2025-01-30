import java.util.*;

public class Simple {
    public static void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int s = in.nextInt();

        List<Integer>[] edges = new ArrayList[n];
        List<Integer>[] thisType = new ArrayList[k];

        for (int i = 0; i < n; i++)
            edges[i] = new ArrayList<>();
        for (int i = 0; i < k; i++)
            thisType[i] = new ArrayList<>();

        int[] type = new int[n];
        for (int i = 0; i < n; i++) {
            type[i] = in.nextInt() - 1;
            thisType[type[i]].add(i);
        }

        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1, b = in.nextInt() - 1;
            edges[a].add(b);
            edges[b].add(a);
        }

        // BFS for each good type
        List<Integer>[] ans = new ArrayList[n];
        for (int i = 0; i < n; i++)
            ans[i] = new ArrayList<>();

        for (int good = 0; good < k; good++) {
            Queue<Integer> queue = new LinkedList<>();
            int[] level = new int[n];
            boolean[] visited = new boolean[n];
            Arrays.fill(level, Integer.MAX_VALUE);

            for (int town : thisType[good]) {
                queue.add(town);
                level[town] = 0;
                visited[town] = true;
            }

            while (!queue.isEmpty()) {
                int top = queue.poll();
                for (int neighbor : edges[top]) {
                    if (!visited[neighbor]) {
                        level[neighbor] = level[top] + 1;
                        queue.add(neighbor);
                        visited[neighbor] = true;
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                ans[i].add(level[i]);
            }
        }

        // Compute final answer
        for (int i = 0; i < n; i++) {
            Collections.sort(ans[i]);
            long totalCost = 0;
            for (int j = 0; j < s; j++) {
                totalCost += ans[i].get(j);
            }
            System.out.print(totalCost + " ");
        }
        System.out.println();
        in.close();
    }

    public static void main(String[] args) {
        solve();
    }
}
