import java.io.*;
import java.util.*;

public class TLE {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FastReader in = new FastReader();

  static int[] dx = { -1, 0, +1, 0 };
  static int[] dy = { 0, +1, 0, -1 };

  public static void main(String[] args) {
    int T = in.nextInt();
    while (T-- > 0) {
      solve();
    }
    out.close();
  }

  private static void solve() {
    int n = in.nextInt();
    int m = in.nextInt();
    char[][] arr = new char[n][m];
    Queue<int[]> queue = new LinkedList<>();

    for (int i = 0; i < n; i++) {
      String s = in.next();
      for (int j = 0; j < m; j++) {
        arr[i][j] = s.charAt(j);
        // Add boundary elements to the queue
        if ((i == 0 && arr[i][j] == 'U') ||
            (i == n - 1 && arr[i][j] == 'D') ||
            (j == 0 && arr[i][j] == 'L') ||
            (j == m - 1 && arr[i][j] == 'R')) {
          queue.offer(new int[] { i, j });
        }
      }
    }

    // BFS to remove connected components
    while (!queue.isEmpty()) {
      int[] curr = queue.poll();
      int r = curr[0], c = curr[1];
      arr[r][c] = '0'; // Mark as visited

      for (int i = 0; i < 4; i++) {
        int nRow = r + dx[i];
        int nCol = c + dy[i];
        if (nRow >= 0 && nRow < n && nCol >= 0 && nCol < m) {
          if (dx[i] == -1 && arr[nRow][nCol] == 'D') { // Down moves up
            queue.offer(new int[] { nRow, nCol });
          } else if (dx[i] == 1 && arr[nRow][nCol] == 'U') { // Up moves down
            queue.offer(new int[] { nRow, nCol });
          } else if (dy[i] == -1 && arr[nRow][nCol] == 'R') { // Right moves left
            queue.offer(new int[] { nRow, nCol });
          } else if (dy[i] == 1 && arr[nRow][nCol] == 'L') { // Left moves right
            queue.offer(new int[] { nRow, nCol });
          }
        }
      }
    }

    // Calculate remaining cells
    int remaining = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (arr[i][j] == 'U' || arr[i][j] == 'R' || arr[i][j] == 'L' || arr[i][j] == 'D') {
          remaining++;
        } else if (arr[i][j] == '?') {
          boolean found = false;
          for (int k = 0; k < 4; k++) {
            int nRow = i + dx[k];
            int nCol = j + dy[k];
            if (nRow >= 0 && nRow < n && nCol >= 0 && nCol < m) {
              if (arr[nRow][nCol] == 'U' || arr[nRow][nCol] == 'R' ||
                  arr[nRow][nCol] == 'L' || arr[nRow][nCol] == 'D' || arr[nRow][nCol] == '?') {
                found = true;
                break;
              }
            }
          }
          if (found) {
            remaining++;
          }
        }
      }
    }

    out.println(remaining);
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

    double nextDouble() {
      return Double.parseDouble(next());
    }
  }
}
