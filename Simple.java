import java.io.*;
import java.util.*;

public class Simple {
    public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    static FASTIO in = new FASTIO();
    static int nines[] = { 9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999 };

    public static void main(String[] Hi) throws IOException {
        int cp = in.nextInt();
        while (cp-- > 0) {
            solve();
        }
        out.flush();
        out.close();
    }

    static void solve() {
        long n = in.nextLong();
        int ans = (int) 1e9;
        long temp = n;
        while (temp > 0) {
            if (temp % 10 == 7) {
                out.println(0);
                return;
            }
            temp /= 10;
        }
        for (int i = 0; i < 9; i++) {
            temp = n;
            for (int j = 1; j < 11; j++) {
                temp += nines[i];
                if (check(temp)) {
                    ans = Math.min(ans, j);
                    break;
                }
            }
        }
        out.println(ans);
    }

    private static boolean check(long temp) {
        while (temp > 0) {
            if (temp % 10 == 7) {
                return true;
            }
            temp /= 10;
        }
        return false;
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
    }
}
