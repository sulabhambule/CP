import java.io.*;
import java.util.*;

public class Simple {
    public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    static FASTIO in = new FASTIO();
    static int MOD = (int) 1e9 + 7;

    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-- > 0) {
            solve();
        }
        out.flush();
        out.close();
    }

    static void solve() {
        int n = in.nextInt();
        String s = in.next();
        int ans = -1;
        for (char ch = 'a'; ch <= 'z'; ch++) {
            int count = 0;
            int i = 0, j = n - 1;
            while (i <= j) {
                if (s.charAt(i) == s.charAt(j)) {
                    i++;
                    j--;
                } else if (s.charAt(i) == ch) {
                    count++;
                    i++;
                } else if (s.charAt(j) == ch) {
                    j--;
                    count++;
                } else {
                    count = -1;
                    break;
                }
            }
            if (count != -1) {
                if (ans == -1) {
                    ans = count;
                } else {
                    ans = Math.min(ans, count);
                }
            }
        }

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
    }
}
