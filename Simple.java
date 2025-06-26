import java.io.*;
import java.util.*;

public class Simple {
    static FastReader in = new FastReader();
    static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    static int MOD = (int) 1e9 + 7;

    public static void main(String[] args) throws IOException {
        int t = in.nextInt();
        while (t-- > 0) {
            
        }
        out.close();
    }

    static class FastReader {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
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
