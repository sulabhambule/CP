import java.io.*;
import java.util.*;

public class Main {
    public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    static FASTIO in = new FASTIO();

    public static void main(String[] Hi) throws IOException {
        int cp = in.nextInt();
        while (cp-- > 0) {
            solve();
        }
        out.close();
    }

    static void solve() {
        long xa = in.nextLong();
        long ya = in.nextLong();
        long xb = in.nextLong();
        long yb = in.nextLong();
        long xc = in.nextLong();
        long yc = in.nextLong();
        long distAB = dist(xa, ya, xb, yb);
        long distAC = dist(xa, ya, xc, yc);
        long distBC = dist(xb, yc, xc, yc);
        out.println((distAB + distAC - distBC) / 2 + 1);
    }

    public static long dist(long x1, long y1, long x2, long y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
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
