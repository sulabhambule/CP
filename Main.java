
import java.io.*;
import java.util.*;

public class Main {

     static FastReader in;
     static PrintWriter out;
     static int MOD = (int) 1e9 + 7;

     static void solve() {
          int n = in.nextInt();
          List<List<Integer>> adj = new ArrayList<>();
          for (int i = 0; i <= n; i++) {
               adj.add(new ArrayList<>());
          }
          for (int i = 0; i < n - 1; i++) {
               int a = in.nextInt();
               int b = in.nextInt();
               adj.get(a).add(b);
               adj.get(b).add(a);
          }
          boolean[] visited = new boolean[n + 1];
          List<Integer> ans = new ArrayList<>();
          for (List<Integer> ls : adj) {
               Collections.sort(ls);
          }
          dfs(1, adj, visited, ans);
          for (int i : ans) {
               out.print(i + " ");
          }
          out.println();
     }

     private static void dfs(int node, List<List<Integer>> adj, boolean[] visited,
               List<Integer> ans) {
          visited[node] = true;
          ans.add(node);
          for (int adjNode : adj.get(node)) {
               if (!visited[adjNode]) {
                    dfs(adjNode, adj, visited, ans);
                    ans.add(node);
               }
          }
     }

     public static void main(String[] args) {
          in = new FastReader();
          out = new PrintWriter(System.out);
          int t = 1;
          while (t-- > 0) {
               solve();
          }
          out.close();
     }

     static class FastReader {

          BufferedReader br;
          StringTokenizer st;

          public FastReader() {
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
