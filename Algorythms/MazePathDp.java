
import java.io.*;
import java.util.*;

// cf problem link : https://codeforces.com/contest/2041/problem/D

public class MazePathDp {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FASTIO in = new FASTIO();
  static long INF = Long.MAX_VALUE / 2;
  static int inf = Integer.MAX_VALUE / 2;
  static int mod = (int) 1e9 + 7;
  static final int[] dx = { -1, 0, 1, 0 };
  static final int[] dy = { 0, -1, 0, 1 };

  public static void main(String[] args) {
    int t = 1;
    while (t-- > 0) {
      solve();
    }
    out.flush();
    out.close();
  }

  static void solve() {
    int n = in.nextInt(), m = in.nextInt();
    char[][] arr = new char[n][m];
    for (int i = 0; i < n; i++) {
      arr[i] = in.next().toCharArray();
    }
    int[][][][] dp = new int[n][m][4][4];
    // r, c, dir, count;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        for (int k = 0; k < 4; k++)
          Arrays.fill(dp[i][j][k], inf);
      }
    }

    Queue<int[]> queue = new ArrayDeque<>();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (arr[i][j] == 'S') {
          queue.offer(new int[] { i, j, 0, 0 });
          dp[i][j][0][0] = 0;
          break;
        }
      }
    }

    int ans = inf;
    while (!queue.isEmpty()) {
      int[] curr = queue.poll();
      int x = curr[0], y = curr[1], dir = curr[2], len = curr[3];
      if (arr[x][y] == 'T')
        ans = Math.min(ans, dp[x][y][dir][len]);
      for (int i = 0; i < 4; i++) {
        int nLen = (i == dir ? len : 0) + 1;
        if (nLen >= 4)
          continue;

        int nx = x + dx[i], ny = y + dy[i];
        if (nx >= 0 && ny >= 0 && nx < n && ny < m
            && arr[nx][ny] != '#') {
          if (dp[nx][ny][i][nLen] == inf) {
            dp[nx][ny][i][nLen] = dp[x][y][dir][len] + 1;
            queue.offer(new int[] { nx, ny, i, nLen });
          }
        }
      }
    }
    if (ans == inf)
      ans = -1;
    out.println(ans);
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

    String nextLine() {
      String str = "";
      try {
        st = null;
        str = br.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return str;
    }
  }
}