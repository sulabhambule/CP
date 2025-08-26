import java.io.*;
import java.util.*;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), true);

  static int n;

  static int query(int x, List<Integer> S) throws IOException {
    out.print("?" + " " + x + " " + S.size());
    for (int v : S)
      out.print(" " + v);
    out.println();
    out.flush();
    return Integer.parseInt(br.readLine().trim());
  }

  public static void main(String[] args) throws Exception {
    int t = Integer.parseInt(br.readLine().trim());
    while (t-- > 0) {
      n = Integer.parseInt(br.readLine().trim());

      int[] dp = new int[n + 1];

      List<Integer> all = new ArrayList<>();
      for (int i = 1; i <= n; i++)
        all.add(i);

      for (int i = 1; i <= n; i++) {
        dp[i] = query(i, all);
      }

      int start = 1;
      for (int i = 2; i <= n; i++) {
        if (dp[i] > dp[start])
          start = i;
      }

      List<Integer> path = new ArrayList<>();
      int u = start;
      path.add(u);

      while (dp[u] > 1) {
        int targetLen = dp[u] - 1;
        int next = -1;
        for (int v = 1; v <= n; v++) {
          if (dp[v] == targetLen) {
            int res = query(u, Arrays.asList(u, v));
            if (res == 2) {
              next = v;
              break;
            }
          }
        }
        if (next == -1)
          break;
        path.add(next);
        u = next;
      }

      out.print("!" + " " + path.size());
      for (int v : path)
        out.print(" " + v);
      out.println();
      out.flush();
    }
  }
}
