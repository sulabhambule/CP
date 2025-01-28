import java.util.*;

public class Simple {
    static int[] dx = { -1, 0, +1, 0 };
    static int[] dy = { 0, +1, 0, -1 };

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        char[][] arr = new char[n][m];
        for (int i = 0; i < n; i++) {
            String s = in.next();
            arr[i] = s.toCharArray();
        }
        boolean[][] vis = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 'A') {
                    boolean f = dfs(i, j, vis, arr);
                    break;
                }
            }
        }
        if(f) {
            System.out.println("YES");
        }
        
    }

    static boolean dfs(int r, int c, boolean[][] vis, char[][] arr) {
        vis[r][c] = true;
        if (arr[r][c] == 'B')
            return true;
        for (int i = 0; i < 4; i++) {
            int nR = r + dx[i];
            int nC = c + dy[i];
            if (nR >= 0 && nC >= 0 && nR < arr.length && nC < arr[0].length &&
                    arr[nR][nC] == '.' && !vis[nR][nC]) {
                if (!dfs(nR, nC, vis, arr)) {
                    return false;
                }
            }
        }

        return true;
    }
}
