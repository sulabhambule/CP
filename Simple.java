
import java.io.*;
import java.util.*;

public class Simple {
    public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    static FASTIO in = new FASTIO();
    static final int MOD = (int) 1e9 + 7;
    static boolean[] isPrime;
    static ArrayList<Integer> primes;

    public static void main(String[] Hi) throws IOException {
        int cp = in.nextInt();
        while (cp-- > 0) {
            solve();
        }
        out.close();
    }

    public static void sieve(int n) {
        isPrime = new boolean[n + 1];
        primes = new ArrayList<>();
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }
    }

    static void solve() {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        long ans = 0;

    }

    /*
     * lcm(p, q) -> p and q are the prime number
     * (p, p.q) -> p.q
     * (p.q, p.q) -> p.q
     * (1 , p.q) -> p.q(p,
     * (p, q) -> p.q
     * (1 , p.p) -> p.p
     * (p, p.p) -> p.p
     * (p.p, p.p) -> p.p
     */

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