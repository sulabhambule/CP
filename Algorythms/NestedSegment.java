
import java.io.*;
import java.util.*;

// cf link : https://codeforces.com/contest/976/problem/C
public class NestedSegment {

    public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    static FASTIO in = new FASTIO();
    static final int MOD = 998244353;
    static final int inf = (int) 1e9;
    static final long INF = (long) 1e18;

    public static void main(String[] args) {
        int t = 1;
        while (t-- > 0) {
            solve();
        }
        out.close();
    }

    static void solve() {
        int n = in.nextInt();
        int[][] arr = new int[n][3];
        for (int i = 0; i < n; i++) {
            arr[i][0] = in.nextInt();
            arr[i][1] = in.nextInt();
            arr[i][2] = i + 1;
        }

        Arrays.sort(arr, (x, y) -> {
            if (x[0] == y[0]) {
                // if l value are same then greater will come first
                return Integer.compare(y[1], x[1]);
            }
            return x[0] - y[0];
        });

        for (int i = 1; i < n; i++) {
            if (arr[i][1] <= arr[i - 1][1]) {
                out.println(arr[i][2] + " " + arr[i - 1][2]);
                return;
            }
        }

        out.println("-1 -1");
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
