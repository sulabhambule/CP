import java.io.*;
import java.lang.*;
import java.util.*;

class Codechef {
    public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    static FastReader in = new FastReader();

    // static long MOD = (long)1e9 + 7;
    public static void main(String[] args) throws java.lang.Exception {
        int cf = in.nextInt();
        while (cf-- > 0) {
            Accepted();
        }
        // out.flush();
        out.close();
    }

    private static void Accepted() {
        int n = in.nextInt();
        long k = in.nextLong();
        long[] ans = new long[n];
        for (int i = 0; i < n; i++) {
            if (k >= (n - 1 - i)) {
                ans[i] = 3;
                k -= (n - 1 - i);
            } else {

            }
        }
    }

    public static ArrayList<Long> generatePrimes(long limit) {
        boolean[] isPrime = new boolean[(int) (limit + 1)];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        for (long i = 2; i * i <= limit; i++) {
            if (isPrime[(int) i]) {
                for (long j = i * i; j <= limit; j += i) {
                    isPrime[(int) j] = false;
                }
            }
        }

        ArrayList<Long> primes = new ArrayList<>();
        for (long i = 2; i <= limit; i++) {
            if (isPrime[(int) i]) {
                primes.add(i);
            }
        }
        return primes;
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
}
