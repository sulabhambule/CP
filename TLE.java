import java.io.*;
import java.util.*;

public class TLE {
    public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    static FastReader in = new FastReader();
    private static final int MOD = (int) 1e9 + 7;
    // private static final int MOD = 998244353;

    public static void main(String[] args) {
        int T = in.nextInt();
        while (T-- > 0) {
            solve();
        }
        out.close();
    }

    private static void solve() {

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

    // static class Pair {
    // int first, second;

    // Pair(int first, int second) {
    // this.first = first;
    // this.second = second;
    // }

    // @Override
    // public boolean equals(Object obj) {
    // if (obj == this)
    // return true;
    // if (!(obj instanceof Pair))
    // return false;
    // Pair pair = (Pair) obj;
    // return pair.first == this.first && pair.second == this.second;
    // }

    // @Override
    // public int hashCode() {
    // return Objects.hash(first, second);
    // }
    // }
}
