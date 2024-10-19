import java.io.*;
import java.util.*;

// Author : Sulabh Ambule
public class tcs1 {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static long MOD = (long) (1e9 + 7);
  // static long MOD = 998244353;
  static FastReader in = new FastReader();

  public static void main(String[] args) throws Exception {
    // int cf = in.nextInt();
    // while (cf-- > 0) {
    Accepted();
    // }
    out.flush();
    out.close();
  }

  /*
   * 
   * || जय श्री राम ||
   * 
   */

  static class Node {
    int x, y, steps;

    Node(int x, int y, int steps) {
      this.x = x;
      this.y = y;
      this.steps = steps;
    }
  }

  private static void Accepted() {
    int m = in.nextInt(); // r
    int n = in.nextInt();// c
    int[][] grid = new int[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        grid[i][j] = in.nextInt();
      }
    }

    int sx = in.nextInt(), sy = in.nextInt();
    int dx = in.nextInt(), dy = in.nextInt();
    int mx = in.nextInt(), my = in.nextInt();

    int[] src = { sx, sy };
    int[] mr = { mx, my };
    int[] des = { dx, dy };
    int ans = bfsMinSteps(grid, m, n, src, des, mr);
    System.out.println(ans);
  }

  public static int bfsMinSteps(int[][] grid, int M, int N, int[] src, int[] dest, int[] moveRule) {

    int[][] dire = {
        { moveRule[0], moveRule[1] },
        { moveRule[1], -moveRule[0] },
        { -moveRule[1], moveRule[0] },
        { -moveRule[0], -moveRule[1] }
    };

    Queue<Node> queue = new LinkedList<>();
    queue.add(new Node(src[0], src[1], 0));

    boolean[][] visited = new boolean[M][N];
    visited[src[0]][src[1]] = true;

    while (!queue.isEmpty()) {
      Node current = queue.poll();

      if (current.x == dest[0] && current.y == dest[1]) {
        return current.steps;
      }

      for (int[] dir : dire) {
        int newX = current.x + dir[0];
        int newY = current.y + dir[1];

        if (isValid(grid, M, N, newX, newY, visited)) {
          visited[newX][newY] = true;
          queue.add(new Node(newX, newY, current.steps + 1));
        }
      }
    }

    return -1;
  }

  // Helper function to check if the move is valid
  public static boolean isValid(int[][] grid, int M, int N, int x, int y, boolean[][] visited) {
    return x >= 0 && x < M && y >= 0 && y < N && !visited[x][y] && grid[x][y] == 0;
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

    String nextLine() {
      String str = "";
      try {
        str = br.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return str;
    }
  }

  // static class Pair {
  // long first;
  // long second
  // Pair(long f, long s) {
  // this.first = f;
  // this.second = s;
  // }
  // }

  // static class Pair implements Comparable<Pair> {
  // long first;
  // long second;

  // Pair(long first, long x) {
  // this.first = first;
  // this.second = x;
  // }

  // @Override
  // public int compareTo(Pair other) {
  // return Long.compare(this.second, other.second);
  // }
  // }

}
